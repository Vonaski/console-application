package main.java.app;

import java.util.Scanner;
import static main.java.app.menu.main_menu.MenuController.run;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        run(scanner);
    }
}
