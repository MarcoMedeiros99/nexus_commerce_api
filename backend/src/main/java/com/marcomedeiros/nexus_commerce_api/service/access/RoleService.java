package com.marcomedeiros.nexus_commerce_api.service.access;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcomedeiros.nexus_commerce_api.dto.access.RoleRequestDTO;
import com.marcomedeiros.nexus_commerce_api.dto.access.RoleResponseDTO;
import com.marcomedeiros.nexus_commerce_api.model.access.Role;
import com.marcomedeiros.nexus_commerce_api.model.access.User;
import com.marcomedeiros.nexus_commerce_api.repository.access.RoleRepository;
import com.marcomedeiros.nexus_commerce_api.repository.access.UserRepository;
import com.marcomedeiros.nexus_commerce_api.controller.Exceptions.DatabaseException;
import com.marcomedeiros.nexus_commerce_api.controller.Exceptions.ResourceNotFoundException;

@Service
public class RoleService {

    @Autowired
    private RoleRepository repository;

    @Autowired
    private UserRepository userRepository;

    // Metodos GET

    public List<RoleResponseDTO> findAllRole() {
        return repository.findAll()
                .stream()
                .map(RoleResponseDTO::new)
                .toList();
    }

    public RoleResponseDTO findRoleByCode(String accessCode) {
        String formattedCode = accessCode.startsWith("#") ? accessCode : "#" + accessCode;
        Role role = repository.findByAccessCode(formattedCode)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with accessCode: " + formattedCode));
        return new RoleResponseDTO(role);
    }

    // Metodos POST

    public RoleResponseDTO insertRole(Long idUser, RoleRequestDTO dto) {

        if (repository.existsByNameRole(dto.nameRole())) {
            throw new DatabaseException("A role with the name " + dto.nameRole() + " already exists.");
        }

        Role role = new Role();
        role.setNameRole(dto.nameRole());
        Role savedRole = repository.save(role);
        return new RoleResponseDTO(savedRole);

    }

}
