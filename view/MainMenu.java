package view;

import view.enums.loginmenu.LoginMenuResults;
import view.enums.usermenu.UserMenuResults;

import java.util.Scanner;

public class MainMenu
{
    private static boolean isRunning;
    private static Scanner scanner;

    static
    {
        isRunning = true;
        scanner = new Scanner(System.in);
    }

    public static void run()
    {
        LoginMenuResults loginMenuResult;
        UserMenuResults userMenuResults;

        while (isRunning)
        {
            loginMenuResult = LoginMenu.run(scanner);

            if (loginMenuResult == LoginMenuResults.END)
            {
                isRunning = false;
            }
            else if(loginMenuResult== LoginMenuResults.USER_LOGIN)
            {
                userMenuResults = UserMenu.run(scanner);
                System.out.println(userMenuResults);

            }
            else if(loginMenuResult == LoginMenuResults.ADMIN_LOGIN)
            {
                //adminMenuResults = Admin.run(scanner)
            }
            else System.out.println(loginMenuResult.toString());
        }
    }
}
