package com.marcomedeiros.nexus_commerce_api.repository.access;

import com.marcomedeiros.nexus_commerce_api.model.access.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Essencial para o Login
    Optional<User> findByEmail(String email);

    // Essencial para validar se o CPF ou CNPJ já está cadastrado
    Optional<User> findByCpfCnpj(String cpfCnpj);

    // Essencial para buscar o usuário pelo código externo (#USR-XXXXXX)
    Optional<User> findByAccessCode(String accessCode);

    // Útil para verificar existência rapidamente sem carregar o objeto todo
    boolean existsByEmail(String email);
    boolean existsByCpfCnpj(String cpfCnpj);
}
