package com.marcomedeiros.nexus_commerce_api.service.access;

import com.marcomedeiros.nexus_commerce_api.controller.Exceptions.DatabaseException;
import com.marcomedeiros.nexus_commerce_api.model.access.PasswordHistory;
import com.marcomedeiros.nexus_commerce_api.model.access.users.User;
import com.marcomedeiros.nexus_commerce_api.repository.access.PasswordHistoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class PasswordService {

    // Regex para validar: mínimo 10 chars, 1 maiúscula, 1 minúscula, 1 número, 1
    // especial
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
            "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[^A-Za-z\\d]).{10,}$");

    // Detecta mais de 2 dígitos iguais consecutivos (ex: 111, 222)
    private static final Pattern REPEATED_DIGITS_PATTERN = Pattern.compile(
            "(\\d)\\1{2,}");

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private PasswordHistoryRepository passwordHistoryRepository;

    /**
     * Valida e codifica a senha para um novo usuário (insert).
     * Não verifica histórico nem senha atual (não existe ainda).
     */
    public String validateAndEncodeForInsert(String rawPassword, String userName) {
        validate(rawPassword, userName, null, List.of());
        return passwordEncoder.encode(rawPassword);
    }

    /**
     * Valida e codifica a senha para atualização de um usuário existente.
     * Verifica se é diferente da atual e se não está no histórico.
     */
    public String validateAndEncodeForUpdate(String rawPassword, String userName,
            String currentHashedPassword, Long userId) {
        List<PasswordHistory> history = passwordHistoryRepository.findLast3ByUserId(userId);
        validate(rawPassword, userName, currentHashedPassword, history);
        return passwordEncoder.encode(rawPassword);
    }

    /**
     * Salva a senha atual no histórico e remove registros antigos (mantém apenas as
     * últimas 3).
     */
    public void saveToHistory(String hashedPassword, User user) {
        PasswordHistory entry = PasswordHistory.builder()
                .hashedPassword(hashedPassword)
                .user(user)
                .build();
        passwordHistoryRepository.save(entry);

        // Remove registros mais antigos que ultrapassem o limite de 3
        List<PasswordHistory> allHistory = passwordHistoryRepository.findLast3ByUserId(user.getIdUser());
        long total = passwordHistoryRepository.countByUserIdUser(user.getIdUser());
        if (total > 3) {
            // Os excedentes serão naturalmente ignorados pelo findLast3, mas limpamos para
            // manter o banco organizado
            passwordHistoryRepository.findAll().stream()
                    .filter(ph -> ph.getUser().getIdUser().equals(user.getIdUser()))
                    .filter(ph -> !allHistory.contains(ph))
                    .forEach(passwordHistoryRepository::delete);
        }
    }

    // ─── Validações internas ───────────────────────────────────────────────────

    private void validate(String rawPassword, String userName,
            String currentHashedPassword, List<PasswordHistory> history) {

        // 1. Tamanho mínimo
        if (rawPassword == null || rawPassword.length() < 10) {
            throw new DatabaseException("Password must be at least 10 characters long.");
        }

        // 2. Deve conter maiúsculas, minúsculas, números e caracteres especiais
        if (!PASSWORD_PATTERN.matcher(rawPassword).matches()) {
            throw new DatabaseException(
                    "Password must contain uppercase letters, lowercase letters, numbers and special characters.");
        }

        // 3. Não pode conter mais de 2 dígitos iguais consecutivos
        if (REPEATED_DIGITS_PATTERN.matcher(rawPassword).find()) {
            throw new DatabaseException(
                    "Password cannot contain more than 2 consecutive identical digits (e.g. 111, 222).");
        }

        // 4. Não pode conter o nome do usuário (case-insensitive)
        if (userName != null && !userName.isBlank()) {
            String[] nameParts = userName.trim().toLowerCase().split("\\s+");
            String lowerPassword = rawPassword.toLowerCase();
            for (String part : nameParts) {
                if (part.length() >= 3 && lowerPassword.contains(part)) {
                    throw new DatabaseException("Password cannot contain your name.");
                }
            }
        }

        // 5. Nova senha deve ser diferente da senha atual
        if (currentHashedPassword != null && passwordEncoder.matches(rawPassword, currentHashedPassword)) {
            throw new DatabaseException("New password must be different from the current password.");
        }

        // 6. Não pode reutilizar nenhuma das últimas 3 senhas
        for (PasswordHistory past : history) {
            if (passwordEncoder.matches(rawPassword, past.getHashedPassword())) {
                throw new DatabaseException("Password cannot be the same as any of the last 3 passwords.");
            }
        }
    }
}
