package com.marcomedeiros.nexus_commerce_api.validation;

public final class DocumentValidator {

    private DocumentValidator() {}

    public static boolean isValidCpf(String cpf) {
        if (cpf == null) return false;
        String clean = cpf.replaceAll("\\D", "");
        if (clean.length() != 11) return false;

        // Elimina CPFs invalidos com todos os digitos iguais
        if (clean.chars().distinct().count() == 1) return false;

        try {
            int sum1 = 0;
            for (int i = 0; i < 9; i++) {
                sum1 += (clean.charAt(i) - '0') * (10 - i);
            }
            int remainder1 = 11 - (sum1 % 11);
            int digit1 = (remainder1 >= 10) ? 0 : remainder1;

            if (digit1 != (clean.charAt(9) - '0')) {
                return false;
            }

            int sum2 = 0;
            for (int i = 0; i < 10; i++) {
                sum2 += (clean.charAt(i) - '0') * (11 - i);
            }
            int remainder2 = 11 - (sum2 % 11);
            int digit2 = (remainder2 >= 10) ? 0 : remainder2;

            return digit2 == (clean.charAt(10) - '0');
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isValidCnpj(String cnpj) {
        if (cnpj == null) return false;
        String clean = cnpj.replaceAll("\\D", "");
        if (clean.length() != 14) return false;

        // Elimina CNPJs invalidos com todos os digitos iguais
        if (clean.chars().distinct().count() == 1) return false;

        try {
            int[] weights1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            int sum1 = 0;
            for (int i = 0; i < 12; i++) {
                sum1 += (clean.charAt(i) - '0') * weights1[i];
            }
            int remainder1 = sum1 % 11;
            int digit1 = (remainder1 < 2) ? 0 : 11 - remainder1;

            if (digit1 != (clean.charAt(12) - '0')) {
                return false;
            }

            int[] weights2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            int sum2 = 0;
            for (int i = 0; i < 13; i++) {
                sum2 += (clean.charAt(i) - '0') * weights2[i];
            }
            int remainder2 = sum2 % 11;
            int digit2 = (remainder2 < 2) ? 0 : 11 - remainder2;

            return digit2 == (clean.charAt(13) - '0');
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isValid(String document) {
        if (document == null) return false;
        String clean = document.replaceAll("\\D", "");
        if (clean.length() == 11) {
            return isValidCpf(clean);
        } else if (clean.length() == 14) {
            return isValidCnpj(clean);
        }
        return false;
    }

    public static String format(String document) {
        if (document == null) return null;
        String clean = document.replaceAll("\\D", "");
        if (clean.length() == 11) {
            return clean.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
        } else if (clean.length() == 14) {
            return clean.replaceFirst("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
        }
        return document;
    }
}
