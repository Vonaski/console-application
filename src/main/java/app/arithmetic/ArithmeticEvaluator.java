package main.java.app.arithmetic;

import java.util.Stack;

public class ArithmeticEvaluator {
    public static double evaluate(String expression) {
        expression = expression.replaceAll("\\s+", "");
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        if (expression.startsWith("-")) {
            numbers.push(0.0);
        }

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (c == '(') {
                operators.push(c);
            } else if (c == ')') {
                while (operators.peek() != '(') {
                    numbers.push(applyOp(operators.pop(), numbers.pop(), numbers.pop()));
                }
                operators.pop();
            } else if (isOperator(c)) {
                if (c == '-' && (i == 0 || isOperator(expression.charAt(i - 1)) || expression.charAt(i - 1) == '(')) {
                    numbers.push(0.0);
                }
                while (!operators.empty() && hasPrecedence(c, operators.peek())) {
                    numbers.push(applyOp(operators.pop(), numbers.pop(), numbers.pop()));
                }
                operators.push(c);
            } else {
                StringBuilder sb = new StringBuilder();
                if (c == '-' && (i == 0 || isOperator(expression.charAt(i - 1)) || expression.charAt(i - 1) == '(')) {
                    sb.append(c);
                    i++;
                }
                while (i < expression.length() &&
                        (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.' ||
                                (expression.charAt(i) == '-' && (i == 0 || isOperator(expression.charAt(i-1)))))) {
                    sb.append(expression.charAt(i++));
                }
                i--;
                numbers.push(Double.parseDouble(sb.toString()));
            }
        }

        while (!operators.empty()) {
            numbers.push(applyOp(operators.pop(), numbers.pop(), numbers.pop()));
        }

        return numbers.pop();
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') return false;
        return (op1 != '*' && op1 != '/') || (op2 != '+' && op2 != '-');
    }

    private static double applyOp(char op, double b, double a) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/':
                if (b == 0) throw new ArithmeticException("Division by zero");
                return a / b;
        }
        return 0;
    }
}
