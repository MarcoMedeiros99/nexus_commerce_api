package com.marcomedeiros.nexus_commerce_api.repository.access;

import com.marcomedeiros.nexus_commerce_api.model.access.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    // Útil para listar todos os endereços de um cliente específico
    List<Address> findByUserIdUser(Long idUser);
}
