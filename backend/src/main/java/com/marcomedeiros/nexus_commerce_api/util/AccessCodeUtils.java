package com.marcomedeiros.nexus_commerce_api.util;

public class AccessCodeUtils {

    // Construtor privado para evitar instanciação
    private AccessCodeUtils() {
    }

    // Formatação dos access codes por entidade

    public static String formatUserAccessCode(String accessCode) {
        return formatAccessCode(accessCode, "USR");
    }

    public static String formatRoleAccessCode(String accessCode) {
        return formatAccessCode(accessCode, "RLN");
    }

    public static String formatCategoryAccessCode(String accessCode) {
        return formatAccessCode(accessCode, "CAT");
    }

    public static String formatProductAccessCode(String accessCode) {
        return formatAccessCode(accessCode, "PROD");
    }

    public static String formatOrderAccessCode(String accessCode) {
        return formatAccessCode(accessCode, "ORD");
    }

    public static String formatPaymentAccessCode(String accessCode) {
        return formatAccessCode(accessCode, "PAY");
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
