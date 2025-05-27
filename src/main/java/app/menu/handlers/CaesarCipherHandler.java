package main.java.app.menu.handlers;

import main.java.app.cipher.CaesarCipher;
import main.java.app.io.FileUtils;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class CaesarCipherHandler extends BaseHandler{

    public CaesarCipherHandler(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void handle() {
        System.out.println("\nCaesar Cipher:");
        System.out.println("1. Encrypt");
        System.out.println("2. Decrypt with known shift");
        System.out.println("3. Brute-force decrypt (unknown shift)");
        System.out.print("> ");

        int operation;
        try {
            operation = Integer.parseInt(scanner.nextLine());
            if (operation < 1 || operation > 3) {
                System.out.println("Error: Choose 1-3");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Input must be a number");
            return;
        }

        System.out.println("Input method:");
        System.out.println("1. From console");
        System.out.println("2. From file");
        System.out.print("> ");

        int inputType;
        try {
            inputType = Integer.parseInt(scanner.nextLine());
            if (inputType != 1 && inputType != 2) {
                System.out.println("Error: Choose 1 or 2");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Input must be a number");
            return;
        }

        String text;
        if (inputType == 1) {
            System.out.print("Enter text: ");
            text = scanner.nextLine();
        } else {
            System.out.print("Enter file path: ");
            String filePath = scanner.nextLine();
            try {
                text = FileUtils.readFromFile(filePath);
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
                return;
            }
        }

        String result;
        if (operation == 3) {
            // Brute-force decrypt
            List<String> attempts = CaesarCipher.bruteForceDecrypt(text);
            result = "Brute-force decryption attempts:\n" +
                    String.join("\n", attempts);
            System.out.println(result);
        } else {
            // Handle shift input for encrypt/decrypt
            System.out.print("Enter shift value: ");
            int shift;
            try {
                shift = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Shift must be a number");
                return;
            }

            if (operation == 1) {
                result = CaesarCipher.encrypt(text, shift);
                System.out.println("Encrypted text: " + result);
            } else {
                result = CaesarCipher.decrypt(text, shift);
                System.out.println("Decrypted text: " + result);
            }
        }


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
