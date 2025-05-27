package main.java.app.cipher;

import static main.java.app.cipher.Alphabet.*;
import java.util.ArrayList;
import java.util.List;


public class CaesarCipher {

    // Encrypt with known shift
    public static String encrypt(String text, int shift) {
        return process(text, shift);
    }


    // Decrypt with known shift
    public static String decrypt(String text, int shift) {
        return process(text, -shift);
    }

    // Decrypt without shift (brute-force)
    public static List<String> bruteForceDecrypt(String text) {
        List<String> results = new ArrayList<>();
        // 33 points in russian alphabet > english alphabet
        for (int shift = 1; shift <= RUSSIAN_LENGTH; shift++) {
            results.add(String.format("Shift %2d: %s", shift, decrypt(text, shift)));
        }
        return results;
    }

    private static String process(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (char c : text.toCharArray()) {
            if (ENGLISH_LOWER.indexOf(c) != -1) {
                result.append(shiftChar(c, shift, ENGLISH_LOWER));
            } else if (ENGLISH_UPPER.indexOf(c) != -1) {
                result.append(shiftChar(c, shift, ENGLISH_UPPER));
            } else if (RUSSIAN_LOWER.indexOf(c) != -1) {
                result.append(shiftChar(c, shift, RUSSIAN_LOWER));
            } else if (RUSSIAN_UPPER.indexOf(c) != -1) {
                result.append(shiftChar(c, shift, RUSSIAN_UPPER));
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }

    private static char shiftChar(char c, int shift, String alphabet) {
        int index = alphabet.indexOf(c);
        int newIndex = (index + shift) % alphabet.length();
        if (newIndex < 0) newIndex += alphabet.length();
        return alphabet.charAt(newIndex);
    }
}
