package com.marcomedeiros.nexus_commerce_api.repository.access.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcomedeiros.nexus_commerce_api.model.access.users.ProfessionalUser;

import java.util.Optional;

@Repository
public interface ProfessionalUserRepository extends JpaRepository<ProfessionalUser, Long> {
    Optional<ProfessionalUser> findByEmail(String email);

    Optional<ProfessionalUser> findByDocument(String document);

    Optional<ProfessionalUser> findByAccessCode(String accessCode);

    boolean existsByEmail(String email);

    boolean existsByDocument(String document);
}
