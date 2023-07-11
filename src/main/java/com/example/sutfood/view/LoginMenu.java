package com.example.sutfood.view;

import com.example.sutfood.controllers.LoginMenuController;
import com.example.sutfood.view.enums.loginmenu.LoginMenuCommands;
import com.example.sutfood.view.enums.loginmenu.LoginMenuMessages;
import com.example.sutfood.view.enums.loginmenu.LoginMenuResults;
import com.example.sutfood.view.others.Colors;

import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenu {
    private static boolean isRunning;
    private static Matcher matcher;

    public static void printer(LoginMenuMessages message)
    {
        if (message != null)
            System.out.println(message.getMessage() + Colors.RESET);
    }

    public static LoginMenuResults run(Scanner scanner)
    {
        isRunning = true;
        String input;
        LoginMenuMessages messages;
        while (isRunning)
        {
            messages = LoginMenuMessages.INVALID_COMMAND;
            input = scanner.nextLine().trim();
            if ((matcher = LoginMenuCommands.getMatcher(input, LoginMenuCommands.END)) != null)
                return LoginMenuResults.END;
            else if ((matcher = LoginMenuCommands.getMatcher(input, LoginMenuCommands.BACK)) != null)
                return LoginMenuResults.BACK;
            else if ((matcher = LoginMenuCommands.getMatcher(input, LoginMenuCommands.ADD_ADMIN)) != null)
                messages = checkAddAdmin(scanner);
            else if ((matcher = LoginMenuCommands.getMatcher(input, LoginMenuCommands.ADD_USER)) != null)
                messages = checkAddUser(scanner);
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

    public static LoginMenuMessages checkAddAdmin(Scanner scanner)
    {
        String userName = matcher.group("userName");
        String passWord = matcher.group("passWord");
        LoginMenuMessages result = LoginMenuController.checkAddAdmin(userName, passWord);
        if (result != LoginMenuMessages.ADMIN_ACCOUNT_CREATED) return result;
        System.out.println("Enter your firstName:");
        String firstName = scanner.nextLine().trim();
        System.out.println("Enter your lastName:");
        String lastName = scanner.nextLine().trim();
        LoginMenuController.addAdmin(userName, passWord, firstName, lastName);
        return result;
    }

    public static LoginMenuMessages checkAddUser(Scanner scanner)
    {
        String userName = matcher.group("userName");
        String passWord = matcher.group("passWord");
        LoginMenuMessages result = LoginMenuController.checkAddUser(userName, passWord);
        if (result != LoginMenuMessages.USER_ACCOUNT_CREATED) return result;
        System.out.println("Enter your firstName:");
        String firstName = scanner.nextLine().trim();
        System.out.println("Enter your lastName:");
        String lastName = scanner.nextLine().trim();
        System.out.println("Enter your location:");
        String location = scanner.nextLine().trim();
        LoginMenuController.addUser(userName, passWord, firstName, lastName, location);
        return result;
    }

    public static LoginMenuMessages checkLoginAdmin()
    {
        String userName = matcher.group("userName");
        String passWord = matcher.group("passWord");
        return LoginMenuController.checkLoginAdmin(userName, passWord);
    }

    public static LoginMenuMessages checkLoginUser()
    {
        String userName = matcher.group("userName");
        String passWord = matcher.group("passWord");
        return LoginMenuController.checkLoginUser(userName, passWord);
    }

}
