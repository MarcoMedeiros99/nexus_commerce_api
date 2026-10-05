package com.marcomedeiros.nexus_commerce_api.service.access;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.marcomedeiros.nexus_commerce_api.dto.access.RoleRequestDTO;
import com.marcomedeiros.nexus_commerce_api.dto.access.RoleResponseDTO;
import com.marcomedeiros.nexus_commerce_api.model.access.Role;
import com.marcomedeiros.nexus_commerce_api.repository.access.RoleRepository;

import jakarta.validation.Valid;

import com.marcomedeiros.nexus_commerce_api.controller.Exceptions.DatabaseException;
import com.marcomedeiros.nexus_commerce_api.controller.Exceptions.ResourceNotFoundException;

@Service
public class RoleService {

    @Autowired
    private RoleRepository repository;

    // Metodos GET

    // Metodo para buscar uma role pelo id
    public RoleResponseDTO findRoleById(Long id) {
        Role role = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + id));
        return new RoleResponseDTO(role);
    }

    // Metodo para buscar todas as roles
    public List<RoleResponseDTO> findAllRole() {
        return repository.findAll()
                .stream()
                .map(RoleResponseDTO::new)
                .toList();
    }

    // Metodo para buscar uma role pelo access code

    public RoleResponseDTO findRoleByCode(String accessCode) {
        String formattedCode = accessCode.startsWith("#") ? accessCode : "#" + accessCode;
        Role role = repository.findByAccessCode(formattedCode)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with accessCode: " + formattedCode));
        return new RoleResponseDTO(role);
    }

    // Metodos POST

    // Metodo para inserir uma role
    public RoleResponseDTO insertRole(Long idUser, RoleRequestDTO dto) {

        if (repository.existsByNameRole(dto.nameRole())) {
            throw new DatabaseException("A role with the name " + dto.nameRole() + " already exists.");
        }

        Role role = new Role();
        role.setNameRole(dto.nameRole());
        Role savedRole = repository.save(role);
        return new RoleResponseDTO(savedRole);

    }

    // Metodos PUT

    // Metodo para atualizar uma role pelo id
    public RoleResponseDTO updateRole(Long id, @Valid RoleRequestDTO dto) {
        Role existingRole = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + id));
        existingRole.setNameRole(dto.nameRole());

        Role savedRole = repository.save(existingRole);
        return new RoleResponseDTO(savedRole);
    }

    // Metodo DELETE

    // Metodo para deletar uma role pelo id
    public void deleteRole(Long id) {

        try {
            if (!repository.existsById(id)) {
                throw new ResourceNotFoundException("Role not found with id: " + id);
            }
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Cannot delete role with id: " + id + ". Integrity violation.");
        }
    }

}
