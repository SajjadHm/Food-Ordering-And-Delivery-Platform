package com.example.sutfood.view;

import com.example.sutfood.view.enums.loginmenu.LoginMenuResults;
import com.example.sutfood.view.enums.managermenu.ManagerMenuResults;
import com.example.sutfood.view.enums.usermenu.UserMenuResults;

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
        UserMenuResults userMenuResults;

        while (isRunning)
        {
            ManagerMenuResults managerMenuResult;
            while (isRunning)
            {
                loginMenuResult = LoginMenu.run(scanner);

                if (loginMenuResult == LoginMenuResults.END)
                {
                    isRunning = false;
                }
                else if (loginMenuResult == LoginMenuResults.USER_LOGIN)
                {
                    userMenuResults = UserMenu.run(scanner);
                    if(userMenuResults==UserMenuResults.END)
                        isRunning = false;
                }
                else if (loginMenuResult == LoginMenuResults.ADMIN_LOGIN)
                {
                    managerMenuResult = ManagerMenu.run(scanner);
                    if (managerMenuResult == ManagerMenuResults.END) isRunning = false;
                }
                else System.out.println(loginMenuResult.toString());
            }
        }
    }
}
