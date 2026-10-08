package com.marcomedeiros.nexus_commerce_api.service.access;

import com.marcomedeiros.nexus_commerce_api.util.AccessCodeUtils;
import com.marcomedeiros.nexus_commerce_api.validation.DocumentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcomedeiros.nexus_commerce_api.controller.Exceptions.DatabaseException;
import com.marcomedeiros.nexus_commerce_api.controller.Exceptions.ResourceNotFoundException;
import com.marcomedeiros.nexus_commerce_api.dto.access.UserRequestDTO;
import com.marcomedeiros.nexus_commerce_api.dto.access.UserResponseDTO;
import com.marcomedeiros.nexus_commerce_api.model.access.Role;
import com.marcomedeiros.nexus_commerce_api.model.access.User;
import com.marcomedeiros.nexus_commerce_api.model.access.enums.TypePerson;
import com.marcomedeiros.nexus_commerce_api.repository.access.RoleRepository;
import com.marcomedeiros.nexus_commerce_api.repository.access.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

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

        User user = User.builder()
                .name(dto.name())
                .document(formattedDocument)
                .phone(dto.phone())
                .email(dto.email())
                .password(dto.password())
                .typePerson(typePerson)
                .role(clientRole)
                .build();

        User savedUser = repository.save(user);
        return new UserResponseDTO(savedUser);
    }
}