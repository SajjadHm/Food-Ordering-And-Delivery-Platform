package view;

import controllers.LoginMenuController;
import view.enums.LoginMenuCommands;
import view.enums.LoginMenuMessages;
import view.enums.LoginMenuResults;
import view.others.Colors;

import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenu {
    private static boolean isRunning;
    private static Matcher matcher;

    public static void printer(LoginMenuMessages message) {
        if (message != null) System.out.println(message.getMessage() + Colors.RESET);
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
            else if ((matcher = LoginMenuCommands.getMatcher(input, LoginMenuCommands.LOGIN_ADMIN)) != null)
                messages = checkLoginAdmin();
            else if ((matcher = LoginMenuCommands.getMatcher(input, LoginMenuCommands.LOGIN_USER)) != null)
                messages = checkLoginUser();

            printer(messages);

            if (messages == LoginMenuMessages.ADMIN_LOGIN_SUCCESSFUL)
                return LoginMenuResults.ADMIN_LOGIN;
            else if (messages == LoginMenuMessages.USER_LOGIN_SUCCESSFUL)
                return LoginMenuResults.USER_LOGIN;
        }
        return null;
    }

    public static LoginMenuMessages checkAddAdmin() {
        String userName = matcher.group("userName");
        String passWord = matcher.group("passWord");
        return LoginMenuController.checkAddAdmin(userName, passWord);
    }

    public static LoginMenuMessages checkAddUser() {
        String userName = matcher.group("userName");
        String passWord = matcher.group("passWord");
        return LoginMenuController.checkAddUser(userName, passWord);
    }

    public static LoginMenuMessages checkLoginAdmin() {
        String userName = matcher.group("userName");
        String passWord = matcher.group("passWord");
        return LoginMenuController.checkLoginAdmin(userName, passWord);
    }

    public static LoginMenuMessages checkLoginUser() {
        String userName = matcher.group("userName");
        String passWord = matcher.group("passWord");
        return LoginMenuController.checkLoginUser(userName, passWord);
    }

}
