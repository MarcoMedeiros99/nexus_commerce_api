package com.marcomedeiros.nexus_commerce_api.repository.access;

import com.marcomedeiros.nexus_commerce_api.model.access.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByUserAccessCode(String accessCode);

    Optional<Address> findByZipCode(String zipCode);

    boolean existsByUserAccessCodeAndZipCodeAndNumber(String accessCode, String zipCode, String number);
}
