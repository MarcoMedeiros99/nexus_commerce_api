package com.marcomedeiros.nexus_commerce_api.controller.access;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcomedeiros.nexus_commerce_api.dto.access.UserRequestDTO;
import com.marcomedeiros.nexus_commerce_api.dto.access.UserResponseDTO;
import com.marcomedeiros.nexus_commerce_api.service.access.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService service;

    // Metodos GET

    // Metodo para buscar um user pelo id
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) {
        UserResponseDTO response = service.findById(id);
        return ResponseEntity.ok().body(response);
    }

    // Metodo para buscar um user pelo email
    @GetMapping(value = "/email/{email}")
    public ResponseEntity<UserResponseDTO> findByEmail(@PathVariable String email) {
        UserResponseDTO response = service.findByEmail(email);
        return ResponseEntity.ok().body(response);
    }

    // Metodo para buscar um user pelo access code
    @GetMapping(value = "/access-code/{accessCode}")
    public ResponseEntity<UserResponseDTO> findByAccessCode(@PathVariable String accessCode) {
        UserResponseDTO response = service.findByAccessCode(accessCode);
        return ResponseEntity.ok().body(response);
    }

    // Metodo para buscar um user pelo CPF ou CNPJ (documento)
    @GetMapping(value = "/document/{document}")
    public ResponseEntity<UserResponseDTO> findByDocument(@PathVariable String document) {
        UserResponseDTO response = service.findByDocument(document);
        return ResponseEntity.ok().body(response);
    }

    // Metodo POST

    // Endpoint para inserir um user
    @PostMapping(value = "/insert")
    public ResponseEntity<UserResponseDTO> insert(@RequestBody @Valid UserRequestDTO dto) {
        UserResponseDTO response = service.insert(dto);
        return ResponseEntity.ok().body(response);
    }

    // Metodo PUT

    // Endpoint para atualizar um user
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long id, @RequestBody @Valid UserRequestDTO dto) {
        UserResponseDTO response = service.update(id, dto);
        return ResponseEntity.ok().body(response);
    }
}
