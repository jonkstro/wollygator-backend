package br.com.wollygator.main.services.utils;

import java.util.ArrayList;
import java.util.List;

public class UserServiceUtil {
    public static boolean isPasswordValid(String password) {
        List<String> erros = new ArrayList<>();
        // Verifica se a senha tem mais de 8 caracteres e menos de 30
        if (password.length() < 8 || password.length() > 30) {
            erros.add("A senha deve ter no mínimo 8 e no máximo 30 caracteres.");
        }

        // Verifica se a senha contém letras maiúsculas
        if (!password.matches(".*[A-Z].*")) {
            erros.add("A senha deve conter pelo menos uma letra maiúscula.");
        }

        // Verifica se a senha contém letras minúsculas
        if (!password.matches(".*[a-z].*")) {
            erros.add("A senha deve conter pelo menos uma letra minúscula.");
        }

        // Verifica se a senha contém caracteres especiais
        erros.add(getCaracteresEspeciais(password));

        // Verifica se a senha contém números
        if (!password.matches(".*\\d.*")) {
            erros.add("A senha deve conter pelo menos um número.");
        }

        // Menor que 1 pq tá vindo um negocio vazio aí
        if (erros.size() <= 1) {
            return true; // A senha atende a todos os critérios
        } else {
            throw new IllegalArgumentException(erros.toString());
        }
    }

    private static String getCaracteresEspeciais(String password) {
        String caracteresEspeciais = "!@#$%^&*()-_+[]{}|:;,.<>?/";

        for (char c : password.toCharArray()) {
            if (caracteresEspeciais.indexOf(c) != -1) {
                return "";
            }
        }
        return "A senha deve conter pelo menos um caractere especial.";
    }
}
