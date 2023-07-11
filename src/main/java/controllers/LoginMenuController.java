package controllers;

import model.Memory;
import model.accounts.Manager;
import model.accounts.User;
import view.enums.loginmenu.LoginMenuMessages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginMenuController
{

    private static LoginMenuMessages checkUsernamePassword(String userName, String passWord)
    {
        Matcher matcher = Pattern.compile("\\w+").matcher(userName);
        if (!matcher.matches()) return LoginMenuMessages.INCORRECT_USERNAME;
        matcher = Pattern.compile("\\S+").matcher(passWord);
        if (!matcher.matches()) return LoginMenuMessages.INCORRECT_PASSWORD;
        return null;
    }

    public static LoginMenuMessages checkAddAdmin(String userName, String passWord)
    {
        if (checkUsernamePassword(userName, passWord) != null) return checkUsernamePassword(userName, passWord);
        Manager manager = Memory.getAdmin(userName);
        if (manager != null) return LoginMenuMessages.ADMIN_EXISTS;
        Matcher matcher = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}").matcher(passWord);
        if (!matcher.matches()) return LoginMenuMessages.WEAK_PASSWORD;
        manager = new Manager(userName, passWord, "", ""); // TODO: fix it!
        Memory.getAdmins().add(manager);
        return LoginMenuMessages.ADMIN_ACCOUNT_CREATED;
    }

    public static LoginMenuMessages checkAddUser(String userName, String passWord)
    {
        if (checkUsernamePassword(userName, passWord) != null) return checkUsernamePassword(userName, passWord);
        User user = Memory.getUser(userName);
        if (user != null) return LoginMenuMessages.USER_EXISTS;
        Matcher matcher = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}").matcher(passWord);
        if (!matcher.matches()) return LoginMenuMessages.WEAK_PASSWORD;
        user = new User(userName, passWord, "", ""); // TODO: fix it!
        Memory.getUsers().add(user);
        return LoginMenuMessages.USER_ACCOUNT_CREATED;
    }

    public static LoginMenuMessages checkLoginAdmin(String userName, String passWord)
    {
        Manager manager = Memory.getAdmin(userName);
        if (manager == null) return LoginMenuMessages.INVALID_ADMIN_USERNAME;
        if (!manager.checkPassword(passWord)) return LoginMenuMessages.INVALID_PASSWORD;
        Memory.setCurrentAccount(manager);
        return LoginMenuMessages.ADMIN_LOGIN_SUCCESSFUL;
    }

    public static LoginMenuMessages checkLoginUser(String userName, String passWord)
    {
        User user = Memory.getUser(userName);
        if (user == null) return LoginMenuMessages.INVALID_USER_USERNAME;
        if (!user.checkPassword(passWord)) return LoginMenuMessages.INVALID_PASSWORD;
        Memory.setCurrentAccount(user);
        Memory.setCurrentUser(user);
        user.setLoginStatus(true);
        return LoginMenuMessages.USER_LOGIN_SUCCESSFUL;
    }

}
