package main.java.app.menu.main_menu;

import main.java.app.menu.handlers.ArithmeticHandler;
import main.java.app.menu.handlers.BaseHandler;
import main.java.app.menu.handlers.CaesarCipherHandler;
import java.util.Scanner;

public class MenuController {

    public static void run(Scanner scanner) {
        BaseHandler cipher = new CaesarCipherHandler(scanner);
        BaseHandler arithmetic = new ArithmeticHandler(scanner);
        System.out.println("--- Java Console Application ---");

        while (true) {
            showOperations();

            Integer choice = getChoice(scanner);
            if (choice == null) continue;

            if (chooseAction(choice, cipher, arithmetic)) return;
        }
    }

    private static void showOperations() {
        System.out.println("\nSelect operation:");
        System.out.println("1. Caesar Cipher");
        System.out.println("2. Arithmetic Calculator");
        System.out.println("3. Exit");
        System.out.print("> ");
    }

    private static Integer getChoice(Scanner scanner) {
        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a number between 1-3");
            return null;
        }
        return choice;
    }

    private static boolean chooseAction(Integer choice, BaseHandler cipher, BaseHandler arithmetic) {
        switch (choice) {
            case 1:
                cipher.handle();
                break;
            case 2:
                arithmetic.handle();
                break;
            case 3:
                System.out.println("Exiting program...");
                return true;
            default:
                System.out.println("Error: Invalid choice");
        }
        return false;
    }
}
