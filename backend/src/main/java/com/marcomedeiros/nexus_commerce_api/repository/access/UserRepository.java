package com.marcomedeiros.nexus_commerce_api.repository.access;

import com.marcomedeiros.nexus_commerce_api.model.access.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByDocument(String document);
    Optional<User> findByAccessCode(String accessCode);
    boolean existsByEmail(String email);
    boolean existsByDocument(String document);
}
