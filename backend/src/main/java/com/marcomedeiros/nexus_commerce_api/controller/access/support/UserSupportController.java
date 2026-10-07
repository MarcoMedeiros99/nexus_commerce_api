package com.marcomedeiros.nexus_commerce_api.controller.access.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcomedeiros.nexus_commerce_api.dto.access.support.UserSupportResponseDTO;
import com.marcomedeiros.nexus_commerce_api.service.access.support.UserSupportService;

@RestController
@RequestMapping(value = "/user/support")
public class UserSupportController {

    @Autowired
    private UserSupportService service;

    // Metodos GET

    // Metodo para o suporte buscar um user pelo id
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserSupportResponseDTO> findById(@PathVariable Long id) {
        UserSupportResponseDTO response = service.findSupportById(id);
        return ResponseEntity.ok().body(response);
    }

    // Metodo para o suporte buscar um user pelo email
    @GetMapping(value = "/email/{email}")
    public ResponseEntity<UserSupportResponseDTO> findByEmail(@PathVariable String email) {
        UserSupportResponseDTO response = service.findSupportByEmail(email);
        return ResponseEntity.ok().body(response);
    }

    // Metodo para o suporte buscar um user pelo access code
    @GetMapping(value = "/access-code/{accessCode}")
    public ResponseEntity<UserSupportResponseDTO> findByAccessCode(@PathVariable String accessCode) {
        UserSupportResponseDTO response = service.findSupportByAccessCode(accessCode);
        return ResponseEntity.ok().body(response);
    }

    // Metodo para o suporte buscar um user pelo CPF ou CNPJ (documento)
    @GetMapping(value = "/document/{document}")
    public ResponseEntity<UserSupportResponseDTO> findByDocument(@PathVariable String document) {
        UserSupportResponseDTO response = service.findSupportByDocument(document);
        return ResponseEntity.ok().body(response);
    }
}
