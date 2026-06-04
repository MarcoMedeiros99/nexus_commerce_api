package com.marcomedeiros.nexus_commerce_api.dto.access;

import com.marcomedeiros.nexus_commerce_api.model.access.Address;

public record AddressResponseDTO(

        Long idAddress,
        String streetAddress,
        String number,
        String city,
        String state,
        String neighborhood,
        String complement,
        String zipCode) {

    public AddressResponseDTO(Address address){
        this(
                address.getIdAddress(),
                address.getStreetAddress(),
                address.getNumber(),
                address.getCity(),
                address.getState(),
                address.getNeighborhood(),
                address.getComplement(),
                address.getZipCode()
        );
    }
}
