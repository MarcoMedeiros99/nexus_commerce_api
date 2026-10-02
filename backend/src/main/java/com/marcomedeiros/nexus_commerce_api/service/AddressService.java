package com.marcomedeiros.nexus_commerce_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.marcomedeiros.nexus_commerce_api.controller.Exceptions.DatabaseException;
import com.marcomedeiros.nexus_commerce_api.controller.Exceptions.ResourceNotFoundException;
import com.marcomedeiros.nexus_commerce_api.model.access.Address;
import com.marcomedeiros.nexus_commerce_api.model.access.User;
import com.marcomedeiros.nexus_commerce_api.repository.access.AddressRepository;
import com.marcomedeiros.nexus_commerce_api.repository.access.UserRepository;

@Service
public class AddressService {

    @Autowired
    private AddressRepository repository;

    @Autowired
    private UserRepository userRepository;

    // Metodos GET

    public List<Address> findAllAddress() {
        return repository.findAll();
    }

    public Optional<Address> findAddressByZipCode(String zipCode) {
        return repository.findByZipCode(zipCode);
    }

    public List<Address> findAddressByIdUser(Long idUser) {
        userRepository.findById(idUser)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + idUser));
        return repository.findByUserIdUser(idUser);
    }

    // Metodo POST

    public Address insertAddress(Long idUser, Address address) {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + idUser));

        boolean addressExists = repository.existsByUserIdUserAndZipCodeAndNumber(
                idUser, address.getZipCode(), address.getNumber());
        if (addressExists) {
            throw new DatabaseException("Address with zipCode: " + address.getZipCode()
                    + " and number: " + address.getNumber() + " already exists for this user.");
        }

        address.setUser(user);
        return repository.save(address);
    }

    // Metodo PUT

    public Address updateAddress(Long id, Address address) {
        Address existingAddress = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id: " + id));
        existingAddress.setZipCode(address.getZipCode());
        existingAddress.setStreet(address.getStreet());
        existingAddress.setNumber(address.getNumber());
        existingAddress.setComplement(address.getComplement());
        existingAddress.setNeighborhood(address.getNeighborhood());
        existingAddress.setCity(address.getCity());
        existingAddress.setState(address.getState());
        return repository.save(existingAddress);
    }

    // Metodo DELETE

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
