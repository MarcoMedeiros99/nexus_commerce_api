package com.marcomedeiros.nexus_commerce_api.util;

public class AccessCodeUtils {

    // Construtor privado para evitar instanciação
    private AccessCodeUtils() {
    }

    // Metodo para formatar o access code de User (aceita com/sem # e com/sem hífen)
    public static String formatUserAccessCode(String accessCode) {
        return formatAccessCode(accessCode, "USR");
    }

    // Metodo genérico para formatar qualquer access code pelo prefixo
    public static String formatAccessCode(String accessCode, String prefix) {
        if (accessCode == null || accessCode.isBlank()) {
            return accessCode;
        }

        String code = accessCode.trim().toUpperCase();

        // Garante o prefixo '#'
        if (!code.startsWith("#")) {
            code = "#" + code;
        }

        // Se após o '#' começar com o prefixo mas sem o hífen, adiciona o hífen
        String prefixWithHash = "#" + prefix.toUpperCase();
        if (code.startsWith(prefixWithHash) && !code.startsWith(prefixWithHash + "-")) {
            code = prefixWithHash + "-" + code.substring(prefixWithHash.length());
        }

        return code;
    }
}
