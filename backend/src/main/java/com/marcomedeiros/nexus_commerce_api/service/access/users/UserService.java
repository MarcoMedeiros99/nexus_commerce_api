package com.marcomedeiros.nexus_commerce_api.service.access.users;

import com.marcomedeiros.nexus_commerce_api.util.AccessCodeUtils;
import com.marcomedeiros.nexus_commerce_api.validation.DocumentValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcomedeiros.nexus_commerce_api.controller.Exceptions.DatabaseException;
import com.marcomedeiros.nexus_commerce_api.controller.Exceptions.ResourceNotFoundException;
import com.marcomedeiros.nexus_commerce_api.dto.access.users.UserRequestDTO;
import com.marcomedeiros.nexus_commerce_api.dto.access.users.UserResponseDTO;
import com.marcomedeiros.nexus_commerce_api.model.access.Role;
import com.marcomedeiros.nexus_commerce_api.model.access.enums.TypePerson;
import com.marcomedeiros.nexus_commerce_api.model.access.users.User;
import com.marcomedeiros.nexus_commerce_api.repository.access.RoleRepository;
import com.marcomedeiros.nexus_commerce_api.repository.access.users.UserRepository;
import com.marcomedeiros.nexus_commerce_api.service.access.PasswordService;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordService passwordService;

    // Metodos GET

    // Metodo interno: busca um user pelo id (uso interno / Spring Security)
    @Transactional(readOnly = true)
    public User findUserEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    // Metodo para buscar um user pelo email
    @Transactional(readOnly = true)
    public UserResponseDTO findByEmail(String email) {
        User user = repository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
        return new UserResponseDTO(user);
    }

    // Metodo para buscar um user pelo access code (identificador público)
    @Transactional(readOnly = true)
    public UserResponseDTO findByAccessCode(String accessCode) {
        String formattedCode = AccessCodeUtils.formatUserAccessCode(accessCode);
        User user = repository.findByAccessCode(formattedCode)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with access code: " + formattedCode));
        return new UserResponseDTO(user);
    }

    // Metodo para buscar um user pelo CPF ou CNPJ (documento)
    @Transactional(readOnly = true)
    public UserResponseDTO findByDocument(String document) {
        if (!DocumentValidator.isValid(document)) {
            throw new ResourceNotFoundException("Invalid document format: " + document);
        }

        String rawDocument = document.replaceAll("\\D", "");
        String formattedDocument = DocumentValidator.format(document);

        // Busca no banco pelo documento com ou sem mascara
        User user = repository.findByDocument(formattedDocument)
                .or(() -> repository.findByDocument(rawDocument))
                .orElseThrow(() -> new ResourceNotFoundException("User not found with document: " + document));
        return new UserResponseDTO(user);
    }

    // Metodos POST

    // Metodo para inserir um user
    @Transactional
    public UserResponseDTO insert(UserRequestDTO dto) {
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

        Role clientRole = roleRepository.findByNameRoleIgnoreCase("CLIENT")
                .orElseThrow(() -> new ResourceNotFoundException("Role CLIENT not found."));

        TypePerson typePerson = TypePerson.fromDocument(rawDocument);

        // Valida e criptografa a senha (sem histórico ainda — usuário novo)
        String hashedPassword = passwordService.validateAndEncodeForInsert(dto.password(), dto.name());

        User user = User.builder()
                .name(dto.name())
                .document(formattedDocument)
                .phone(dto.phone())
                .email(dto.email())
                .password(hashedPassword)
                .typePerson(typePerson)
                .role(clientRole)
                .build();

        User savedUser = repository.save(user);

        // Salva a senha inicial no histórico
        passwordService.saveToHistory(hashedPassword, savedUser);

        return new UserResponseDTO(savedUser);
    }

    // Metodos PUT

    // Metodo para atualizar o nome
    @Transactional
    public UserResponseDTO updateName(String accessCode, String name) {
        if (name == null || name.isBlank()) {
            throw new DatabaseException("Name cannot be empty.");
        }
        String formattedCode = AccessCodeUtils.formatUserAccessCode(accessCode);
        User user = repository.findByAccessCode(formattedCode)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with access code: " + formattedCode));

        user.setName(cleanString(name));
        User savedUser = repository.save(user);
        return new UserResponseDTO(savedUser);
    }

    // Metodo para atualizar o email
    @Transactional
    public UserResponseDTO updateEmail(String accessCode, String email) {
        if (email == null || email.isBlank()) {
            throw new DatabaseException("Email cannot be empty.");
        }
        String formattedCode = AccessCodeUtils.formatUserAccessCode(accessCode);
        String cleanedEmail = cleanString(email);
        User user = repository.findByAccessCode(formattedCode)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with access code: " + formattedCode));

        if (!user.getEmail().equalsIgnoreCase(cleanedEmail) && repository.existsByEmail(cleanedEmail)) {
            throw new DatabaseException("Email already registered: " + cleanedEmail);
        }

        user.setEmail(cleanedEmail);
        User savedUser = repository.save(user);
        return new UserResponseDTO(savedUser);
    }

    // Metodo para atualizar o telefone
    @Transactional
    public UserResponseDTO updatePhone(String accessCode, String phone) {
        if (phone == null || phone.isBlank()) {
            throw new DatabaseException("Phone cannot be empty.");
        }
        String formattedCode = AccessCodeUtils.formatUserAccessCode(accessCode);
        User user = repository.findByAccessCode(formattedCode)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with access code: " + formattedCode));

        user.setPhone(cleanString(phone));
        User savedUser = repository.save(user);
        return new UserResponseDTO(savedUser);
    }

    // Metodo para atualizar a senha
    @Transactional
    public UserResponseDTO updatePassword(String accessCode, String password) {
        if (password == null || password.isBlank()) {
            throw new DatabaseException("Password cannot be empty.");
        }
        String formattedCode = AccessCodeUtils.formatUserAccessCode(accessCode);
        String cleanedPassword = cleanString(password);
        User user = repository.findByAccessCode(formattedCode)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with access code: " + formattedCode));

        String hashedPassword = passwordService.validateAndEncodeForUpdate(
                cleanedPassword, user.getName(), user.getPassword(), user.getIdUser());
        user.setPassword(hashedPassword);

        User savedUser = repository.save(user);

        passwordService.saveToHistory(hashedPassword, savedUser);

        return new UserResponseDTO(savedUser);
    }

    // Metodo DELETE

    // Metodo para deletar um user
    @Transactional
    public void deleteUser(String accessCode) {
        String formattedCode = AccessCodeUtils.formatUserAccessCode(accessCode);

        try {
            User user = repository.findByAccessCode(formattedCode)
                    .orElseThrow(
                            () -> new ResourceNotFoundException("User not found with access code: " + formattedCode));
            repository.delete(user);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(
                    "Cannot delete user with access code: " + formattedCode + ". Integrity violation.");
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