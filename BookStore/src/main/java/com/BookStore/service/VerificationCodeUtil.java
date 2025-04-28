package com.BookStore.service;

import java.security.SecureRandom;

public class VerificationCodeUtil {

    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String DIGITS = "0123456789";
    private static final String ALL_CHAR = CHAR_LOWER + CHAR_UPPER + DIGITS;

    public static String generateCaptcha(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            String charSet = ALL_CHAR;
            switch (i % 3) {
                case 0 -> charSet = CHAR_LOWER;
                case 1 -> charSet = CHAR_UPPER;
                case 2 -> charSet = DIGITS;
                default -> charSet = ALL_CHAR;
            }
            sb.append(charSet.charAt(random.nextInt(charSet.length())));
        }
        return sb.toString();
    }
}