package main.java.app.menu.handlers;

import main.java.app.cipher.CaesarCipher;
import main.java.app.io.FileUtils;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class CaesarCipherHandler extends BaseHandler {

    public CaesarCipherHandler(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void handle() {
        showCipherTypeDialog();

        Integer operation = getCipherType();
        if (operation == null) return;

        showInputMethodDialog();

        Integer inputType = getInputType();
        if (inputType == null) return;

        String text;
        if (inputType == 1) {
            text = getTextFromConsole();
        } else {
            text = getTextFromFile();
            if (text == null) return;
        }

        String result = handleOperation(operation, text);
        if (result == null) return;

        showConfirmationDialog(result);
    }

    private static void showCipherTypeDialog() {
        System.out.println("\nCaesar Cipher:");
        System.out.println("1. Encrypt");
        System.out.println("2. Decrypt with known shift");
        System.out.println("3. Brute-force decrypt (unknown shift)");
        System.out.print("> ");
    }

    private Integer getCipherType() {
        int operation;
        try {
            operation = Integer.parseInt(scanner.nextLine());
            if (operation < 1 || operation > 3) {
                System.out.println("Error: Choose 1-3");
                return null;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Input must be a number");
            return null;
        }
        return operation;
    }

    private static void showInputMethodDialog() {
        System.out.println("Input method:");
        System.out.println("1. From console");
        System.out.println("2. From file");
        System.out.print("> ");
    }

    private Integer getInputType() {
        int inputType;
        try {
            inputType = Integer.parseInt(scanner.nextLine());
            if (inputType != 1 && inputType != 2) {
                System.out.println("Error: Choose 1 or 2");
                return null;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Input must be a number");
            return null;
        }
        return inputType;
    }

    private String getTextFromConsole() {
        String text;
        System.out.print("Enter text: ");
        text = scanner.nextLine();
        return text;
    }

    private String getTextFromFile() {
        String text;
        System.out.print("Enter file path: ");
        String filePath = scanner.nextLine();
        try {
            text = FileUtils.readFromFile(filePath);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return null;
        }
        return text;
    }

    private String handleOperation(Integer operation, String text) {
        String result;
        if (operation == 3) {
            // Brute-force decrypt
            List<String> attempts = CaesarCipher.bruteForceDecrypt(text);
            result = "Brute-force decryption attempts:\n" + String.join("\n", attempts);
            System.out.println(result);
        } else {
            // Handle shift input for encrypt/decrypt
            System.out.print("Enter shift value: ");
            int shift;
            try {
                shift = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Shift must be a number");
                return null;
            }

            if (operation == 1) {
                result = CaesarCipher.encrypt(text, shift);
                System.out.println("Encrypted text: " + result);
            } else {
                result = CaesarCipher.decrypt(text, shift);
                System.out.println("Decrypted text: " + result);
            }
        }
        return result;
    }

    private void showConfirmationDialog(String result) {
        System.out.print("Save result to file? (y/n): ");
        String saveChoice = scanner.nextLine();
        if (saveChoice.equalsIgnoreCase("y")) {
            System.out.print("Enter save path: ");
            String savePath = scanner.nextLine();
            try {
                FileUtils.writeToFile(savePath, result);
                System.out.println("File saved successfully");
            } catch (IOException e) {
                System.out.println("Error saving file: " + e.getMessage());
            }
        }
    }
}
