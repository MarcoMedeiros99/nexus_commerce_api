package com.marcomedeiros.nexus_commerce_api.service.access.support;

import com.marcomedeiros.nexus_commerce_api.util.AccessCodeUtils;
import com.marcomedeiros.nexus_commerce_api.validation.DocumentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcomedeiros.nexus_commerce_api.controller.Exceptions.ResourceNotFoundException;
import com.marcomedeiros.nexus_commerce_api.dto.access.support.UserSupportResponseDTO;
import com.marcomedeiros.nexus_commerce_api.model.access.User;
import com.marcomedeiros.nexus_commerce_api.repository.access.UserRepository;

@Service
public class UserSupportService {

    @Autowired
    private UserRepository repository;

    // Metodos GET

    // Metodo para o suporte buscar um user pelo id
    public UserSupportResponseDTO findSupportById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return new UserSupportResponseDTO(user);
    }

    // Metodo para o suporte buscar um user pelo email
    public UserSupportResponseDTO findSupportByEmail(String email) {
        User user = repository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
        return new UserSupportResponseDTO(user);
    }

    // Metodo para o suporte buscar um user pelo access code
    public UserSupportResponseDTO findSupportByAccessCode(String accessCode) {
        String formattedCode = AccessCodeUtils.formatUserAccessCode(accessCode);
        User user = repository.findByAccessCode(formattedCode)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with access code: " + formattedCode));
        return new UserSupportResponseDTO(user);
    }

    // Metodo para o suporte buscar um user pelo CPF ou CNPJ (documento)
    public UserSupportResponseDTO findSupportByDocument(String document) {
        if (!DocumentValidator.isValid(document)) {
            throw new ResourceNotFoundException("Invalid document format: " + document);
        }

        String rawDocument = document.replaceAll("\\D", "");
        String formattedDocument = DocumentValidator.format(document);

        // Busca no banco pelo documento com ou sem mascara
        User user = repository.findByDocument(formattedDocument)
                .or(() -> repository.findByDocument(rawDocument))
                .orElseThrow(() -> new ResourceNotFoundException("User not found with document: " + document));

        return new UserSupportResponseDTO(user);
    }
}
