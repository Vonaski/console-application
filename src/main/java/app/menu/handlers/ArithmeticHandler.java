package main.java.app.menu.handlers;

import main.java.app.arithmetic.ArithmeticEvaluator;
import main.java.app.io.FileUtils;
import java.io.IOException;
import java.util.Scanner;

public class ArithmeticHandler extends BaseHandler {

    public ArithmeticHandler(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void handle() {
        showInputMethodDialog();

        Integer inputType = getInputType();
        if (inputType == null) return;

        String expression;
        if (inputType == 1) {
            expression = getExpressionFromConsole();
        } else {
            expression = getExpressionFromFile();
            if (expression == null) return;
        }

        try {
            double result = evaluateExpression(expression);

            showConfirmationDialog(result);
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: Invalid expression syntax");
        }
    }

    private static void showInputMethodDialog() {
        System.out.println("\nInput method:");
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

    private String getExpressionFromConsole() {
        String expression;
        System.out.print("Enter expression: ");
        expression = scanner.nextLine();
        return expression;
    }

    private String getExpressionFromFile() {
        String expression;
        System.out.print("Enter file path: ");
        String filePath = scanner.nextLine();
        try {
            expression = FileUtils.readFromFile(filePath);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return null;
        }
        return expression;
    }

    private static double evaluateExpression(String expression) {
        double result = ArithmeticEvaluator.evaluate(expression.trim());
        System.out.println("Result: " + result);
        return result;
    }

    private void showConfirmationDialog(double result) {
        System.out.print("Save result to file? (y/n): ");
        String saveChoice = scanner.nextLine();
        if (saveChoice.equalsIgnoreCase("y")) {
            System.out.print("Enter save path: ");
            String savePath = scanner.nextLine();
            try {
                FileUtils.writeToFile(savePath, String.valueOf(result));
                System.out.println("File saved successfully");
            } catch (IOException e) {
                System.out.println("Error saving file: " + e.getMessage());
            }
        }
    }


}
