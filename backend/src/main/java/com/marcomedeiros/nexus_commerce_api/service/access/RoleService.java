package com.marcomedeiros.nexus_commerce_api.service.access;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.marcomedeiros.nexus_commerce_api.dto.access.RoleRequestDTO;
import com.marcomedeiros.nexus_commerce_api.dto.access.RoleResponseDTO;
import com.marcomedeiros.nexus_commerce_api.model.access.Role;
import com.marcomedeiros.nexus_commerce_api.repository.access.RoleRepository;
import com.marcomedeiros.nexus_commerce_api.util.AccessCodeUtils;

import jakarta.validation.Valid;

import com.marcomedeiros.nexus_commerce_api.controller.Exceptions.DatabaseException;
import com.marcomedeiros.nexus_commerce_api.controller.Exceptions.ResourceNotFoundException;

@Service
public class RoleService {

    @Autowired
    private RoleRepository repository;

    // Metodos GET

    // Metodo interno: busca uma role pelo id (uso interno / integrações JPA)
    public Role findRoleEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + id));
    }

    // Metodo para buscar todas as roles
    public List<RoleResponseDTO> findAllRole() {
        return repository.findAll()
                .stream()
                .map(RoleResponseDTO::new)
                .toList();
    }

    // Metodo para buscar uma role
    public RoleResponseDTO findRoleByCode(String accessCode) {
        String formattedCode = AccessCodeUtils.formatRoleAccessCode(accessCode);
        Role role = repository.findByAccessCode(formattedCode)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with access code: " + formattedCode));
        return new RoleResponseDTO(role);
    }

    // Metodos POST

    // Metodo para inserir uma role
    public RoleResponseDTO insertRole(@Valid RoleRequestDTO dto) {

        if (repository.existsByNameRole(dto.nameRole())) {
            throw new DatabaseException("A role with the name " + dto.nameRole() + " already exists.");
        }

        Role role = new Role();
        role.setNameRole(dto.nameRole());
        Role savedRole = repository.save(role);
        return new RoleResponseDTO(savedRole);
    }

    // Metodos PUT

    // Metodo para atualizar uma role
    public RoleResponseDTO updateRole(String accessCode, @Valid RoleRequestDTO dto) {
        String formattedCode = AccessCodeUtils.formatRoleAccessCode(accessCode);
        Role existingRole = repository.findByAccessCode(formattedCode)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with access code: " + formattedCode));
        existingRole.setNameRole(dto.nameRole());

        Role savedRole = repository.save(existingRole);
        return new RoleResponseDTO(savedRole);
    }

    // Metodo DELETE

    // Metodo para deletar uma role
    public void deleteRole(String accessCode) {
        String formattedCode = AccessCodeUtils.formatRoleAccessCode(accessCode);

        try {
            Role role = repository.findByAccessCode(formattedCode)
                    .orElseThrow(
                            () -> new ResourceNotFoundException("Role not found with access code: " + formattedCode));
            repository.deleteById(role.getIdRole());
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(
                    "Cannot delete role with access code: " + formattedCode + ". Integrity violation.");
        }
    }

}
