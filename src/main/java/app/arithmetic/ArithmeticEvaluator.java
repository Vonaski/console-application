package main.java.app.arithmetic;

import java.util.Stack;

public class ArithmeticEvaluator {
    private static final char PLUS = '+';
    private static final char MINUS = '-';
    private static final char MULTIPLICATION = '*';
    private static final char DIVISION = '/';
    private static final char LEFT_PARENTHESIS = '(';
    private static final char RIGHT_PARENTHESIS = ')';

    public static double evaluate(String expression) {
        expression = expression.replaceAll("\\s+", "");
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        if (expression.startsWith("-")) {
            numbers.push(0.0);
        }

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (c == LEFT_PARENTHESIS) {
                operators.push(c);
            } else if (c == RIGHT_PARENTHESIS) {
                handleRightParenthesis(operators, numbers);
            } else if (isOperator(c)) {
                handleValidOperator(expression, c, i, numbers, operators);
            } else {
                handleNegativeInsidePrecedence(expression, c, i, numbers);
            }
        }

        while (!operators.empty()) {
            numbers.push(applyOp(operators.pop(), numbers.pop(), numbers.pop()));
        }

        return numbers.pop();
    }

    private static void handleRightParenthesis(Stack<Character> operators, Stack<Double> numbers) {
        while (operators.peek() != LEFT_PARENTHESIS) {
            numbers.push(applyOp(operators.pop(), numbers.pop(), numbers.pop()));
        }
        operators.pop();
    }

    private static void handleValidOperator(String expression, char c, int i, Stack<Double> numbers, Stack<Character> operators) {
        if (c == MINUS && (i == 0 || isOperator(expression.charAt(i - 1)) || expression.charAt(i - 1) == LEFT_PARENTHESIS)) {
            numbers.push(0.0);
        }
        while (!operators.empty() && hasParenthesis(c, operators.peek())) {
            numbers.push(applyOp(operators.pop(), numbers.pop(), numbers.pop()));
        }
        operators.push(c);
    }

    private static void handleNegativeInsidePrecedence(String expression, char c, int i, Stack<Double> numbers) {
        StringBuilder sb = new StringBuilder();
        if (c == MINUS && (i == 0 || isOperator(expression.charAt(i - 1)) || expression.charAt(i - 1) == LEFT_PARENTHESIS)) {
            sb.append(c);
            i++;
        }
        while (i < expression.length() &&
                (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.' ||
                        (expression.charAt(i) == MINUS && (i == 0 || isOperator(expression.charAt(i -1)))))) {
            sb.append(expression.charAt(i++));
        }
        numbers.push(Double.parseDouble(sb.toString()));
    }

    private static boolean isOperator(char c) {
        return c == PLUS || c == MINUS || c == MULTIPLICATION || c == DIVISION;
    }

    private static boolean hasParenthesis(char op1, char op2) {
        if (op2 == LEFT_PARENTHESIS || op2 == RIGHT_PARENTHESIS) return false;
        return (op1 != MULTIPLICATION && op1 != DIVISION) || (op2 != PLUS && op2 != MINUS);
    }

    private static double applyOp(char op, double b, double a) {
        return switch (op) {
            case PLUS -> a + b;
            case MINUS -> a - b;
            case MULTIPLICATION -> a * b;
            case DIVISION -> {
                if (b == 0) throw new ArithmeticException("Division by zero");
                yield a / b;
            }
            default -> 0;
        };
    }
}
