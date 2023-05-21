package view;

import view.enums.LoginMenuCommands;
import view.enums.LoginMenuMessages;
import view.enums.LoginMenuResults;

import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenu {
    private static boolean isRunning;
    private static Matcher matcher;

    public static void printer(LoginMenuMessages message) {
        if (message != null) System.out.println(message.getMessage());
    }

    public static LoginMenuResults run(Scanner scanner) {
        isRunning = true;
        String input;
        LoginMenuMessages messages;
        while (isRunning) {
            messages = LoginMenuMessages.INVALID_COMMAND;
            input = scanner.nextLine().trim();
            if ((matcher = LoginMenuCommands.getMatcher(input, LoginMenuCommands.END)) != null)
                return LoginMenuResults.END;
            else if ((matcher = LoginMenuCommands.getMatcher(input, LoginMenuCommands.BACK)) != null)
                return LoginMenuResults.BACK;
            else if ((matcher = LoginMenuCommands.getMatcher(input, LoginMenuCommands.ADD_ADMIN)) != null)
                messages = checkAddAdmin();
            else if ((matcher = LoginMenuCommands.getMatcher(input, LoginMenuCommands.ADD_USER)) != null)
                messages = checkAddUser();
            else if ((matcher = LoginMenuCommands.getMatcher(input, LoginMenuCommands.LOGIN_ADMIN)) != null) {
                printer(checkLoginAdmin());
                return LoginMenuResults.ADMIN_LOGIN;
            }
            else if ((matcher = LoginMenuCommands.getMatcher(input, LoginMenuCommands.LOGIN_USER)) != null) {
                printer(checkLoginUser());
                return LoginMenuResults.USER_LOGIN;
            }
            printer(messages);
        }
        return null;
    }

    public static LoginMenuMessages checkAddAdmin() {

        return LoginMenuMessages.ADMIN_ACCOUNT_CREATED;
    }

    public static LoginMenuMessages checkAddUser() {

        return LoginMenuMessages.USER_ACCOUNT_CREATED;
    }

    public static LoginMenuMessages checkLoginAdmin() {

        return LoginMenuMessages.ADMIN_LOGIN_SUCCESSFUL;
    }

    public static LoginMenuMessages checkLoginUser() {

        return LoginMenuMessages.USER_LOGIN_SUCCESSFUL;
    }

}
