package main.java.app.menu.handlers;

import java.util.Scanner;

public abstract class BaseHandler implements Handler {

    protected final Scanner scanner;

    public BaseHandler(Scanner scanner) {
        this.scanner = scanner;
    }
}
