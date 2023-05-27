package view;

import view.enums.loginmenu.LoginMenuResults;
import view.enums.managermenu.ManagerMenu;
import view.enums.managermenu.ManagerMenuResults;

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
        ManagerMenuResults managerMenuResult;
        while (isRunning) {
            loginMenuResult = LoginMenu.run(scanner);
            if (loginMenuResult == LoginMenuResults.END) isRunning = false;
            else if (loginMenuResult == LoginMenuResults.ADMIN_LOGIN) {
                managerMenuResult = ManagerMenu.run(scanner);
                if (managerMenuResult == ManagerMenuResults.END) isRunning = false;
            }
            else System.out.println(loginMenuResult.toString());
        }
    }
}
