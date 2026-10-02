package com.marcomedeiros.nexus_commerce_api.controller.access;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcomedeiros.nexus_commerce_api.dto.access.RoleResponseDTO;
import com.marcomedeiros.nexus_commerce_api.service.access.RoleService;

@RestController
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private RoleService service;

    // Metodos GET

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

}
