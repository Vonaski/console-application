package main.java.app.cipher;

public class Alphabet {
    // English
    public static final String ENGLISH_LOWER = "abcdefghijklmnopqrstuvwxyz";
    public static final String ENGLISH_UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    // Russian
    public static final String RUSSIAN_LOWER = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    public static final String RUSSIAN_UPPER = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    public static final int RUSSIAN_LENGTH = RUSSIAN_LOWER.length(); /* 33 ru length > 26 en length */
}
