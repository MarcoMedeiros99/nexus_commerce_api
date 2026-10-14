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

import com.marcomedeiros.nexus_commerce_api.dto.access.users.UserRequestDTO;
import com.marcomedeiros.nexus_commerce_api.dto.access.users.UserResponseDTO;
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

    // Metodos PUT

    // Endpoint para atualizar o nome de um user
    @PutMapping(value = "/update/name/{id}")
    public ResponseEntity<UserResponseDTO> updateName(@PathVariable Long id, @RequestBody String name) {
        UserResponseDTO response = service.updateName(id, name);
        return ResponseEntity.ok().body(response);
    }

    // Endpoint para atualizar o email de um user
    @PutMapping(value = "/update/email/{id}")
    public ResponseEntity<UserResponseDTO> updateEmail(@PathVariable Long id, @RequestBody String email) {
        UserResponseDTO response = service.updateEmail(id, email);
        return ResponseEntity.ok().body(response);
    }

    // Endpoint para atualizar o telefone de um user
    @PutMapping(value = "/update/phone/{id}")
    public ResponseEntity<UserResponseDTO> updatePhone(@PathVariable Long id, @RequestBody String phone) {
        UserResponseDTO response = service.updatePhone(id, phone);
        return ResponseEntity.ok().body(response);
    }

    // Endpoint para atualizar a senha de um user
    @PutMapping(value = "/update/password/{id}")
    public ResponseEntity<UserResponseDTO> updatePassword(@PathVariable Long id, @RequestBody String password) {
        UserResponseDTO response = service.updatePassword(id, password);
        return ResponseEntity.ok().body(response);
    }
}
