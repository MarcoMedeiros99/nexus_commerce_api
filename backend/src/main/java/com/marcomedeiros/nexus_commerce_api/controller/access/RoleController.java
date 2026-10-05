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

    // Endpoit para buscar uma role pelo id
    @GetMapping(value = "/find/{id}")
    public ResponseEntity<RoleResponseDTO> findRoleById(@PathVariable Long id) {
        RoleResponseDTO role = service.findRoleById(id);
        return ResponseEntity.ok().body(role);
    }

    // Endpoint para buscar todas as roles
    @GetMapping(value = "/find-all")
    public ResponseEntity<List<RoleResponseDTO>> findAllRole() {
        List<RoleResponseDTO> listRole = service.findAllRole();
        return ResponseEntity.ok().body(listRole);
    }

    // Endpoint para buscar uma role pelo código de acesso
    @GetMapping(value = "/code/{accessCode}")
    public ResponseEntity<RoleResponseDTO> findRoleByCode(@PathVariable String accessCode) {
        RoleResponseDTO role = service.findRoleByCode(accessCode);
        return ResponseEntity.ok().body(role);
    }

    // Metodos POST

    // Endpoint para inserir uma role
    @PostMapping(value = "/insert")
    public ResponseEntity<RoleResponseDTO> insertRole(@RequestBody RoleRequestDTO dto) {
        RoleResponseDTO role = service.insertRole(null, dto);
        return ResponseEntity.ok().body(role);
    }

    // Metodo PUT

    // Endpoint para atualizar uma role pelo id
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<RoleResponseDTO> updateRole(@PathVariable Long id,
            @RequestBody @Valid RoleRequestDTO dto) {
        RoleResponseDTO response = service.updateRole(id, dto);
        return ResponseEntity.ok().body(response);
    }

    // Metodo DELETE

    // Endpoint para deletar uma role por id
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        service.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}