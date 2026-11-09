package com.marcomedeiros.nexus_commerce_api.service.access.users;

import com.marcomedeiros.nexus_commerce_api.util.AccessCodeUtils;
import com.marcomedeiros.nexus_commerce_api.validation.DocumentValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcomedeiros.nexus_commerce_api.controller.Exceptions.DatabaseException;
import com.marcomedeiros.nexus_commerce_api.controller.Exceptions.ResourceNotFoundException;
import com.marcomedeiros.nexus_commerce_api.dto.access.users.ProfessionalUserRequestDTO;
import com.marcomedeiros.nexus_commerce_api.dto.access.users.ProfessionalUserResponseDTO;
import com.marcomedeiros.nexus_commerce_api.model.access.Role;
import com.marcomedeiros.nexus_commerce_api.model.access.enums.TypePerson;
import com.marcomedeiros.nexus_commerce_api.model.access.users.ProfessionalUser;
import com.marcomedeiros.nexus_commerce_api.repository.access.RoleRepository;
import com.marcomedeiros.nexus_commerce_api.repository.access.users.ProfessionalUserRepository;
import com.marcomedeiros.nexus_commerce_api.service.access.PasswordService;

@Service
public class ProfessionalUserService {

    @Autowired
    private ProfessionalUserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordService passwordService;

    // Metodos GET

    // Metodo interno: busca um professional user pelo id (uso interno)
    @Transactional(readOnly = true)
    public ProfessionalUser findProfessionalUserEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professional user not found with id: " + id));
    }

    // Metodo para buscar um professional user pelo email
    @Transactional(readOnly = true)
    public ProfessionalUserResponseDTO findByEmail(String email) {
        ProfessionalUser user = repository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Professional user not found with email: " + email));
        return new ProfessionalUserResponseDTO(user);
    }

    // Metodo para buscar um professional user pelo access code (identificador público)
    @Transactional(readOnly = true)
    public ProfessionalUserResponseDTO findByAccessCode(String accessCode) {
        String formattedCode = AccessCodeUtils.formatProfessionalUserAccessCode(accessCode);
        ProfessionalUser user = repository.findByAccessCode(formattedCode)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Professional user not found with access code: " + formattedCode));
        return new ProfessionalUserResponseDTO(user);
    }

    // Metodo para buscar um professional user pelo CPF ou CNPJ (documento)
    @Transactional(readOnly = true)
    public ProfessionalUserResponseDTO findByDocument(String document) {
        if (!DocumentValidator.isValid(document)) {
            throw new ResourceNotFoundException("Invalid document format: " + document);
        }

        String rawDocument = document.replaceAll("\\D", "");
        String formattedDocument = DocumentValidator.format(document);

        // Busca no banco pelo documento com ou sem mascara
        ProfessionalUser user = repository.findByDocument(formattedDocument)
                .or(() -> repository.findByDocument(rawDocument))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Professional user not found with document: " + document));
        return new ProfessionalUserResponseDTO(user);
    }

    // Metodos POST

    // Metodo para inserir um professional user (role é obrigatória)
    @Transactional
    public ProfessionalUserResponseDTO insert(ProfessionalUserRequestDTO dto) {
        if (repository.existsByEmail(dto.email())) {
            throw new DatabaseException("Email already registered: " + dto.email());
        }

        if (!DocumentValidator.isValid(dto.document())) {
            throw new ResourceNotFoundException("Invalid document format: " + dto.document());
        }

        String formattedDocument = DocumentValidator.format(dto.document());
        String rawDocument = dto.document().replaceAll("\\D", "");

        if (repository.existsByDocument(formattedDocument) || repository.existsByDocument(rawDocument)) {
            throw new DatabaseException("Document already registered: " + dto.document());
        }

        // Busca a role pelo nome informado (obrigatório para professional user)
        Role role = roleRepository.findByNameRoleIgnoreCase(dto.roleName().trim())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Role not found with name: " + dto.roleName()));

        TypePerson typePerson = TypePerson.fromDocument(rawDocument);

        // Valida e criptografa a senha (sem histórico ainda — usuário novo)
        String hashedPassword = passwordService.validateAndEncodeForInsert(dto.password(), dto.name());

        ProfessionalUser user = ProfessionalUser.builder()
                .name(dto.name())
                .document(formattedDocument)
                .phone(dto.phone())
                .email(dto.email())
                .password(hashedPassword)
                .typePerson(typePerson)
                .role(role)
                .build();

        ProfessionalUser savedUser = repository.save(user);

        return new ProfessionalUserResponseDTO(savedUser);
    }

    // Metodos PUT

    // Metodo para atualizar o nome
    @Transactional
    public ProfessionalUserResponseDTO updateName(String accessCode, String name) {
        if (name == null || name.isBlank()) {
            throw new DatabaseException("Name cannot be empty.");
        }
        String formattedCode = AccessCodeUtils.formatProfessionalUserAccessCode(accessCode);
        ProfessionalUser user = repository.findByAccessCode(formattedCode)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Professional user not found with access code: " + formattedCode));

        user.setName(cleanString(name));
        ProfessionalUser savedUser = repository.save(user);
        return new ProfessionalUserResponseDTO(savedUser);
    }

    // Metodo para atualizar o email
    @Transactional
    public ProfessionalUserResponseDTO updateEmail(String accessCode, String email) {
        if (email == null || email.isBlank()) {
            throw new DatabaseException("Email cannot be empty.");
        }
        String formattedCode = AccessCodeUtils.formatProfessionalUserAccessCode(accessCode);
        String cleanedEmail = cleanString(email);
        ProfessionalUser user = repository.findByAccessCode(formattedCode)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Professional user not found with access code: " + formattedCode));

        if (!user.getEmail().equalsIgnoreCase(cleanedEmail) && repository.existsByEmail(cleanedEmail)) {
            throw new DatabaseException("Email already registered: " + cleanedEmail);
        }

        user.setEmail(cleanedEmail);
        ProfessionalUser savedUser = repository.save(user);
        return new ProfessionalUserResponseDTO(savedUser);
    }

    // Metodo para atualizar o telefone
    @Transactional
    public ProfessionalUserResponseDTO updatePhone(String accessCode, String phone) {
        if (phone == null || phone.isBlank()) {
            throw new DatabaseException("Phone cannot be empty.");
        }
        String formattedCode = AccessCodeUtils.formatProfessionalUserAccessCode(accessCode);
        ProfessionalUser user = repository.findByAccessCode(formattedCode)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Professional user not found with access code: " + formattedCode));

        user.setPhone(cleanString(phone));
        ProfessionalUser savedUser = repository.save(user);
        return new ProfessionalUserResponseDTO(savedUser);
    }

    // Metodo para atualizar a senha
    @Transactional
    public ProfessionalUserResponseDTO updatePassword(String accessCode, String password) {
        if (password == null || password.isBlank()) {
            throw new DatabaseException("Password cannot be empty.");
        }
        String formattedCode = AccessCodeUtils.formatProfessionalUserAccessCode(accessCode);
        String cleanedPassword = cleanString(password);
        ProfessionalUser user = repository.findByAccessCode(formattedCode)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Professional user not found with access code: " + formattedCode));

        // Valida e criptografa a senha (sem histórico para professional user)
        String hashedPassword = passwordService.validateAndEncodeForInsert(cleanedPassword, user.getName());
        user.setPassword(hashedPassword);

        ProfessionalUser savedUser = repository.save(user);

        return new ProfessionalUserResponseDTO(savedUser);
    }

    // Metodo para atualizar a role
    @Transactional
    public ProfessionalUserResponseDTO updateRole(String accessCode, String roleName) {
        if (roleName == null || roleName.isBlank()) {
            throw new DatabaseException("Role name cannot be empty.");
        }
        String formattedCode = AccessCodeUtils.formatProfessionalUserAccessCode(accessCode);
        ProfessionalUser user = repository.findByAccessCode(formattedCode)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Professional user not found with access code: " + formattedCode));

        Role role = roleRepository.findByNameRoleIgnoreCase(cleanString(roleName))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Role not found with name: " + roleName));

        user.setRole(role);
        ProfessionalUser savedUser = repository.save(user);
        return new ProfessionalUserResponseDTO(savedUser);
    }

    // Metodo DELETE

    // Metodo para deletar um professional user
    @Transactional
    public void deleteProfessionalUser(String accessCode) {
        String formattedCode = AccessCodeUtils.formatProfessionalUserAccessCode(accessCode);

        try {
            ProfessionalUser user = repository.findByAccessCode(formattedCode)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Professional user not found with access code: " + formattedCode));
            repository.delete(user);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(
                    "Cannot delete professional user with access code: " + formattedCode + ". Integrity violation.");
        }
    }

    private String cleanString(String value) {
        if (value == null)
            return null;
        String trimmed = value.trim();
        if (trimmed.startsWith("\"") && trimmed.endsWith("\"") && trimmed.length() >= 2) {
            return trimmed.substring(1, trimmed.length() - 1);
        }
        return trimmed;
    }
}
