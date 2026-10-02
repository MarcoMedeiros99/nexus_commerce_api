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

import com.marcomedeiros.nexus_commerce_api.dto.access.AddressRequestDTO;
import com.marcomedeiros.nexus_commerce_api.dto.access.AddressResponseDTO;
import com.marcomedeiros.nexus_commerce_api.service.AddressService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/address")
public class AddressController {

    @Autowired
    private AddressService service;

    // Metodos GET

    // Endpoint para buscar todos os endereços do banco
    @GetMapping(value = "/find-all")
    public ResponseEntity<List<AddressResponseDTO>> findAllAddress() {
        List<AddressResponseDTO> listAddress = service.findAllAddress();
        return ResponseEntity.ok().body(listAddress);
    }

    // Endpoint para buscar todos os endereços do banco por CEP
    @GetMapping("/zipcode/{zipCode}")
    public ResponseEntity<AddressResponseDTO> findAddressByZipCode(@PathVariable String zipCode) {
        String formattedZipCode = (zipCode != null && zipCode.length() == 8 && !zipCode.contains("-"))
                ? zipCode.substring(0, 5) + "-" + zipCode.substring(5)
                : zipCode;

        AddressResponseDTO address = service.findAddressByZipCode(formattedZipCode);
        return ResponseEntity.ok().body(address);
    }

    // Endpoint para buscar o endereço por id do usuario
    @GetMapping("/user/{idUser}")
    public ResponseEntity<List<AddressResponseDTO>> findAddressByIdUser(@PathVariable Long idUser) {
        List<AddressResponseDTO> addresses = service.findAddressByIdUser(idUser);
        return ResponseEntity.ok().body(addresses);
    }

    // Metodo POST

    // Endpoint para inserir um endereço no banco
    @PostMapping("/user/{idUser}")
    public ResponseEntity<AddressResponseDTO> insertAddress(@PathVariable Long idUser,
            @RequestBody @Valid AddressRequestDTO addressDTO) {
        AddressResponseDTO response = service.insertAddress(idUser, addressDTO);
        return ResponseEntity.ok().body(response);
    }

    // Metodo PUT

    // Endpoint para atualizar um endereço no banco por id do endereço
    @PutMapping("/update/{id}")
    public ResponseEntity<AddressResponseDTO> updateAddress(@PathVariable Long id,
            @RequestBody @Valid AddressRequestDTO addressDTO) {
        AddressResponseDTO response = service.updateAddress(id, addressDTO);
        return ResponseEntity.ok().body(response);
    }

    // Metodo DELETE

    // Endpoint para deletar um endereço no banco por id do endereço
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        service.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }

}
