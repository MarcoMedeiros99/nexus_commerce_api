package com.marcomedeiros.nexus_commerce_api.util;

public class MaskUtils {

    // Metodo para mascarar documentos (CPF e CNPJ) na interface
    public static String maskDocument(String document) {
        if (document == null)
            return null;

        // Se for CPF com pontuação: 123.456.789-01 -> ***.456.789-**
        if (document.length() == 14) {
            return document.replaceAll("^\\d{3}", "***").replaceAll("\\d{2}$", "**");
        }
        // Se for CNPJ com pontuação: 12.345.678/0001-90 -> **.345.678/0001-**
        if (document.length() == 18) {
            return document.replaceAll("^\\d{2}", "**").replaceAll("\\d{2}$", "**");
        }
        // Fallback genérico caso venha sem máscara
        if (document.length() >= 6) {
            return document.substring(0, 2) + "*****" + document.substring(document.length() - 2);
        }
        return document;
    }

    // Metodo para mascarar emails na interface
    public static String maskEmail(String email) {
        if (email == null || !email.contains("@")) {
            return email;
        }
        String[] parts = email.split("@");
        String name = parts[0];
        String domain = parts[1];
        if (name.length() <= 2) {
            return "*@" + domain;
        }
        return name.charAt(0) + "***" + name.charAt(name.length() - 1) + "@" + domain;
    }

    // Metodo para mascarar telefones na interface
    public static String maskPhone(String phone) {
        if (phone == null || phone.length() < 8) {
            return phone;
        }
        // Deixa visíveis os 4 últimos dígitos e mascara o restante
        return phone.replaceAll("\\d(?=\\d{4})", "*");
    }
}
