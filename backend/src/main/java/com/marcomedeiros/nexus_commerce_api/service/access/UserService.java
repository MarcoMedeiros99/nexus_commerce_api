package com.marcomedeiros.nexus_commerce_api.service.access;

import com.marcomedeiros.nexus_commerce_api.util.AccessCodeUtils;
import com.marcomedeiros.nexus_commerce_api.validation.DocumentValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcomedeiros.nexus_commerce_api.controller.Exceptions.DatabaseException;
import com.marcomedeiros.nexus_commerce_api.controller.Exceptions.ResourceNotFoundException;
import com.marcomedeiros.nexus_commerce_api.dto.access.users.UserRequestDTO;
import com.marcomedeiros.nexus_commerce_api.dto.access.users.UserResponseDTO;
import com.marcomedeiros.nexus_commerce_api.model.access.Role;
import com.marcomedeiros.nexus_commerce_api.model.access.enums.TypePerson;
import com.marcomedeiros.nexus_commerce_api.model.access.users.User;
import com.marcomedeiros.nexus_commerce_api.repository.access.RoleRepository;
import com.marcomedeiros.nexus_commerce_api.repository.access.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordService passwordService;

    // Metodos GET

    // Metodo para buscar um user pelo id
    public UserResponseDTO findById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return new UserResponseDTO(user);
    }

    // Metodo para buscar um user pelo email
    public UserResponseDTO findByEmail(String email) {
        User user = repository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
        return new UserResponseDTO(user);
    }

    // Metodo para buscar um user pelo access code
    public UserResponseDTO findByAccessCode(String accessCode) {
        String formattedCode = AccessCodeUtils.formatUserAccessCode(accessCode);
        User user = repository.findByAccessCode(formattedCode)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with access code: " + formattedCode));
        return new UserResponseDTO(user);
    }

    // Metodo para buscar um user pelo CPF ou CNPJ (documento)
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

    // Metodo para atualizar o nome de um user
    public UserResponseDTO updateName(Long id, String name) {
        if (name == null || name.isBlank()) {
            throw new DatabaseException("Name cannot be empty.");
        }
        User user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        user.setName(cleanString(name));
        User savedUser = repository.save(user);
        return new UserResponseDTO(savedUser);
    }

    // Metodo para atualizar o email de um user
    public UserResponseDTO updateEmail(Long id, String email) {
        if (email == null || email.isBlank()) {
            throw new DatabaseException("Email cannot be empty.");
        }
        String cleanedEmail = cleanString(email);
        User user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        if (!user.getEmail().equalsIgnoreCase(cleanedEmail) && repository.existsByEmail(cleanedEmail)) {
            throw new DatabaseException("Email already registered: " + cleanedEmail);
        }

        user.setEmail(cleanedEmail);
        User savedUser = repository.save(user);
        return new UserResponseDTO(savedUser);
    }

    // Metodo para atualizar o telefone de um user
    public UserResponseDTO updatePhone(Long id, String phone) {
        if (phone == null || phone.isBlank()) {
            throw new DatabaseException("Phone cannot be empty.");
        }
        User user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        user.setPhone(cleanString(phone));
        User savedUser = repository.save(user);
        return new UserResponseDTO(savedUser);
    }

    // Metodo para atualizar a senha de um user
    public UserResponseDTO updatePassword(Long id, String password) {
        if (password == null || password.isBlank()) {
            throw new DatabaseException("Password cannot be empty.");
        }
        String cleanedPassword = cleanString(password);
        User user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        String hashedPassword = passwordService.validateAndEncodeForUpdate(
                cleanedPassword, user.getName(), user.getPassword(), user.getIdUser());
        user.setPassword(hashedPassword);

        User savedUser = repository.save(user);

        passwordService.saveToHistory(hashedPassword, savedUser);

        return new UserResponseDTO(savedUser);
    }

    private String cleanString(String value) {
        if (value == null) return null;
        String trimmed = value.trim();
        if (trimmed.startsWith("\"") && trimmed.endsWith("\"") && trimmed.length() >= 2) {
            return trimmed.substring(1, trimmed.length() - 1);
        }
        return trimmed;
    }
}