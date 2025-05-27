package main.java.app.cipher;

public class Alphabet {
    // English
    public static final String ENGLISH_LOWER = "abcdefghijklmnopqrstuvwxyz";
    public static final String ENGLISH_UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final int ENGLISH_LENGTH = ENGLISH_LOWER.length(); // 26

    // Russian
    public static final String RUSSIAN_LOWER = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    public static final String RUSSIAN_UPPER = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    public static final int RUSSIAN_LENGTH = RUSSIAN_LOWER.length(); // 33

    private Alphabet() {} // Prevent instantiation
}
