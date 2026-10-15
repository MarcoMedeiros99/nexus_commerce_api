package com.marcomedeiros.nexus_commerce_api.service.access;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.marcomedeiros.nexus_commerce_api.util.AccessCodeUtils;
import com.marcomedeiros.nexus_commerce_api.controller.Exceptions.DatabaseException;
import com.marcomedeiros.nexus_commerce_api.controller.Exceptions.ResourceNotFoundException;
import com.marcomedeiros.nexus_commerce_api.dto.access.AddressRequestDTO;
import com.marcomedeiros.nexus_commerce_api.dto.access.AddressResponseDTO;
import com.marcomedeiros.nexus_commerce_api.model.access.Address;
import com.marcomedeiros.nexus_commerce_api.model.access.users.User;
import com.marcomedeiros.nexus_commerce_api.repository.access.AddressRepository;
import com.marcomedeiros.nexus_commerce_api.repository.access.UserRepository;

@Service
public class AddressService {

    @Autowired
    private AddressRepository repository;

    @Autowired
    private UserRepository userRepository;

    // Metodos GET

    // Metodo para buscar todos os endereços
    public List<AddressResponseDTO> findAllAddress() {
        return repository.findAll()
                .stream()
                .map(AddressResponseDTO::new)
                .toList();
    }

    // Metodo para buscar um endereço pelo zipCode
    public AddressResponseDTO findAddressByZipCode(String zipCode) {
        Address address = repository.findByZipCode(zipCode)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with zipCode: " + zipCode));
        return new AddressResponseDTO(address);
    }

    // Metodo para buscar os endereços pelo accessCode do usuario
    public List<AddressResponseDTO> findAddressByUserAccessCode(String accessCode) {
        String formattedCode = AccessCodeUtils.formatUserAccessCode(accessCode);
        userRepository.findByAccessCode(formattedCode)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with access code: " + formattedCode));
        return repository.findByUserAccessCode(formattedCode)
                .stream()
                .map(AddressResponseDTO::new)
                .toList();
    }

    // Metodo POST

    // Metodo para inserir um endereço pelo accessCode do usuario
    public AddressResponseDTO insertAddress(String accessCode, AddressRequestDTO dto) {
        String formattedCode = AccessCodeUtils.formatUserAccessCode(accessCode);
        User user = userRepository.findByAccessCode(formattedCode)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with access code: " + formattedCode));

        boolean addressExists = repository.existsByUserAccessCodeAndZipCodeAndNumber(
                formattedCode, dto.zipCode(), dto.number());
        if (addressExists) {
            throw new DatabaseException("Address with zipCode: " + dto.zipCode()
                    + " and number: " + dto.number() + " already exists for this user.");
        }

        Address address = new Address();
        address.setStreet(dto.street());
        address.setNumber(dto.number());
        address.setCity(dto.city());
        address.setState(dto.state());
        address.setNeighborhood(dto.neighborhood());
        address.setComplement(dto.complement());
        address.setZipCode(dto.zipCode());
        address.setUser(user);

        Address saved = repository.save(address);
        return new AddressResponseDTO(saved);
    }

    // Metodo PUT

    // Metodo para atualizar um endereço
    public AddressResponseDTO updateAddress(Long id, AddressRequestDTO dto) {
        Address existingAddress = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id: " + id));
        existingAddress.setZipCode(dto.zipCode());
        existingAddress.setStreet(dto.street());
        existingAddress.setNumber(dto.number());
        existingAddress.setComplement(dto.complement());
        existingAddress.setNeighborhood(dto.neighborhood());
        existingAddress.setCity(dto.city());
        existingAddress.setState(dto.state());

        Address saved = repository.save(existingAddress);
        return new AddressResponseDTO(saved);
    }

    // Metodo DELETE

    // Metodo para deletar um endereço
    public void deleteAddress(Long id) {
        try {
            if (!repository.existsById(id)) {
                throw new ResourceNotFoundException("Address not found with id: " + id);
            }
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Cannot delete address with id: " + id + ". Integrity violation.");
        }
    }

}
