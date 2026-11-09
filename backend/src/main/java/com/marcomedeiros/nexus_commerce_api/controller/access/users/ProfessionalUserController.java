package com.marcomedeiros.nexus_commerce_api.controller.access.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcomedeiros.nexus_commerce_api.dto.access.users.ProfessionalUserRequestDTO;
import com.marcomedeiros.nexus_commerce_api.dto.access.users.ProfessionalUserResponseDTO;
import com.marcomedeiros.nexus_commerce_api.service.access.users.ProfessionalUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/professional-user")
public class ProfessionalUserController {

    @Autowired
    private ProfessionalUserService service;

    // Metodos GET

    // Metodo para buscar um professional user pelo access code
    @GetMapping(value = "/{accessCode}")
    public ResponseEntity<ProfessionalUserResponseDTO> findByAccessCode(@PathVariable String accessCode) {
        ProfessionalUserResponseDTO response = service.findByAccessCode(accessCode);
        return ResponseEntity.ok().body(response);
    }

    // Metodo para buscar um professional user pelo email
    @GetMapping(value = "/email/{email}")
    public ResponseEntity<ProfessionalUserResponseDTO> findByEmail(@PathVariable String email) {
        ProfessionalUserResponseDTO response = service.findByEmail(email);
        return ResponseEntity.ok().body(response);
    }

    // Metodo para buscar um professional user pelo CPF ou CNPJ via path variable (suporta barras no CNPJ)
    @GetMapping(value = "/document/{*document}")
    public ResponseEntity<ProfessionalUserResponseDTO> findByDocument(@PathVariable String document) {
        if (document != null && document.startsWith("/")) {
            document = document.substring(1);
        }
        ProfessionalUserResponseDTO response = service.findByDocument(document);
        return ResponseEntity.ok().body(response);
    }

    // Metodo POST

    // Endpoint para inserir um professional user (role é obrigatória no DTO)
    @PostMapping(value = "/insert")
    public ResponseEntity<ProfessionalUserResponseDTO> insert(@RequestBody @Valid ProfessionalUserRequestDTO dto) {
        ProfessionalUserResponseDTO response = service.insert(dto);
        return ResponseEntity.ok().body(response);
    }

    // Metodos PUT

    // Endpoint para atualizar o nome de um professional user
    @PutMapping(value = "/update/name/{accessCode}")
    public ResponseEntity<ProfessionalUserResponseDTO> updateName(@PathVariable String accessCode,
            @RequestBody String name) {
        ProfessionalUserResponseDTO response = service.updateName(accessCode, name);
        return ResponseEntity.ok().body(response);
    }

    // Endpoint para atualizar o email de um professional user
    @PutMapping(value = "/update/email/{accessCode}")
    public ResponseEntity<ProfessionalUserResponseDTO> updateEmail(@PathVariable String accessCode,
            @RequestBody String email) {
        ProfessionalUserResponseDTO response = service.updateEmail(accessCode, email);
        return ResponseEntity.ok().body(response);
    }

    // Endpoint para atualizar o telefone de um professional user
    @PutMapping(value = "/update/phone/{accessCode}")
    public ResponseEntity<ProfessionalUserResponseDTO> updatePhone(@PathVariable String accessCode,
            @RequestBody String phone) {
        ProfessionalUserResponseDTO response = service.updatePhone(accessCode, phone);
        return ResponseEntity.ok().body(response);
    }

    // Endpoint para atualizar a senha de um professional user
    @PutMapping(value = "/update/password/{accessCode}")
    public ResponseEntity<ProfessionalUserResponseDTO> updatePassword(@PathVariable String accessCode,
            @RequestBody String password) {
        ProfessionalUserResponseDTO response = service.updatePassword(accessCode, password);
        return ResponseEntity.ok().body(response);
    }

    // Endpoint para atualizar a role de um professional user
    @PutMapping(value = "/update/role/{accessCode}")
    public ResponseEntity<ProfessionalUserResponseDTO> updateRole(@PathVariable String accessCode,
            @RequestBody String roleName) {
        ProfessionalUserResponseDTO response = service.updateRole(accessCode, roleName);
        return ResponseEntity.ok().body(response);
    }

    // Metodo DELETE

    // Endpoint para deletar um professional user
    @DeleteMapping(value = "/delete/{accessCode}")
    public ResponseEntity<Void> deleteProfessionalUser(@PathVariable String accessCode) {
        service.deleteProfessionalUser(accessCode);
        return ResponseEntity.noContent().build();
    }
}
