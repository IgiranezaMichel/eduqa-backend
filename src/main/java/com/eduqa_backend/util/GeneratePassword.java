package com.eduqa_backend.util;

import java.util.Random;

public class GeneratePassword {
    public static String generatePassword() {
        var allAlphaNumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        var password = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            password.append(allAlphaNumeric.charAt(random.nextInt(allAlphaNumeric.length())));
        }
        return password.toString();
    }
}
