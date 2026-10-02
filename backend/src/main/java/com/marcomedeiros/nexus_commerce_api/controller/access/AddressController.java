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

import com.marcomedeiros.nexus_commerce_api.controller.Exceptions.ResourceNotFoundException;
import com.marcomedeiros.nexus_commerce_api.model.access.Address;
import com.marcomedeiros.nexus_commerce_api.service.AddressService;

@RestController
@RequestMapping(value = "/address")
public class AddressController {

    @Autowired
    private AddressService service;

    // Metodos GET

    // Endpoint para buscar todos os endereços do banco
    @GetMapping(value = "/find-all")
    public ResponseEntity<List<Address>> findAllAddress() {
        List<Address> listAddress = service.findAllAddress();
        return ResponseEntity.ok().body(listAddress);
    }

    // Endpoint para buscar todos os endereços do banco por CEP
    @GetMapping("/zipcode/{zipCode}")
    public ResponseEntity<Address> findAddressByZipCode(@PathVariable String zipCode) {
        String formattedZipCode = (zipCode != null && zipCode.length() == 8 && !zipCode.contains("-"))
                ? zipCode.substring(0, 5) + "-" + zipCode.substring(5)
                : zipCode;

        Address address = service.findAddressByZipCode(formattedZipCode)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Address not found with zipCode: " + formattedZipCode));
        return ResponseEntity.ok().body(address);
    }

    // Endpoints para buscar o endereço por id do usuario
    @GetMapping("/user/{idUser}")
    public ResponseEntity<List<Address>> findAddressByIdUser(@PathVariable Long idUser) {
        List<Address> addresses = service.findAddressByIdUser(idUser);
        return ResponseEntity.ok().body(addresses);
    }

    // Metodo POST

    // Endpoint para inserir um endereço no banco
    @PostMapping("/user/{idUser}")
    public ResponseEntity<Address> insertAddress(@PathVariable Long idUser, @RequestBody Address address) {
        address = service.insertAddress(idUser, address);
        return ResponseEntity.ok().body(address);
    }

    // Metodo PUT

    // Endpoint para atualizar um endereço no banco por id do endereço
    @PutMapping("/update/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long id, @RequestBody Address address) {
        address = service.updateAddress(id, address);
        return ResponseEntity.ok().body(address);
    }

    // Metodo DELETE

    // Endpoint para deletar um endereço no banco por id do endereço
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        service.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }

}
