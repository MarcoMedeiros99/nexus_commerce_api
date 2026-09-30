package com.marcomedeiros.nexus_commerce_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return repository.findByUserIdUser(idUser);
    }

    // Metodo POST

    public Address insertAddress(Long idUser, Address address) {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new RuntimeException("User not found"));
        address.setUser(user);
        return repository.save(address);
    }

}
