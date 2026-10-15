package com.marcomedeiros.nexus_commerce_api.controller.access;

import java.util.List;

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

import com.marcomedeiros.nexus_commerce_api.dto.access.RoleRequestDTO;
import com.marcomedeiros.nexus_commerce_api.dto.access.RoleResponseDTO;
import com.marcomedeiros.nexus_commerce_api.service.access.RoleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private RoleService service;

    // Metodos GET

    // Endpoint para buscar uma role pelo access code (identificador público)
    @GetMapping(value = "/{accessCode}")
    public ResponseEntity<RoleResponseDTO> findRoleByCode(@PathVariable String accessCode) {
        RoleResponseDTO role = service.findRoleByCode(accessCode);
        return ResponseEntity.ok().body(role);
    }

    // Endpoint para buscar todas as roles
    @GetMapping(value = "/find-all")
    public ResponseEntity<List<RoleResponseDTO>> findAllRole() {
        List<RoleResponseDTO> listRole = service.findAllRole();
        return ResponseEntity.ok().body(listRole);
    }

    // Metodos POST

    // Endpoint para inserir uma role
    @PostMapping(value = "/insert")
    public ResponseEntity<RoleResponseDTO> insertRole(@RequestBody RoleRequestDTO dto) {
        RoleResponseDTO role = service.insertRole(dto);
        return ResponseEntity.ok().body(role);
    }

    // Metodo PUT

    // Endpoint para atualizar uma role
    @PutMapping(value = "/update/{accessCode}")
    public ResponseEntity<RoleResponseDTO> updateRole(@PathVariable String accessCode,
            @RequestBody @Valid RoleRequestDTO dto) {
        RoleResponseDTO response = service.updateRole(accessCode, dto);
        return ResponseEntity.ok().body(response);
    }

    // Metodo DELETE

    // Endpoint para deletar uma role
    @DeleteMapping(value = "/delete/{accessCode}")
    public ResponseEntity<Void> deleteRole(@PathVariable String accessCode) {
        service.deleteRole(accessCode);
        return ResponseEntity.noContent().build();
    }
}