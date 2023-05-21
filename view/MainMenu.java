package view;

import view.enums.LoginMenuResults;

import java.util.Scanner;

public class MainMenu {
    private static boolean isRunning;
    private static Scanner scanner;

    static {
        isRunning = true;
        scanner = new Scanner(System.in);
    }

    public static void run() {
        LoginMenuResults loginMenuResult;
        while (isRunning) {
            loginMenuResult = LoginMenu.run(scanner);
            if (loginMenuResult == LoginMenuResults.END) isRunning = false;
            else System.out.println(loginMenuResult.toString());
        }
    }
}
