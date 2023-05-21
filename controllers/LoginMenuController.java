package controllers;

import view.enums.LoginMenuMessages;

public class LoginMenuController {

    public static LoginMenuMessages checkAddAdmin(String userName, String passWord) {

        return LoginMenuMessages.ADMIN_ACCOUNT_CREATED;
    }

    public static LoginMenuMessages checkAddUser(String userName, String passWord) {

        return LoginMenuMessages.USER_ACCOUNT_CREATED;
    }

    public static LoginMenuMessages checkLoginAdmin(String userName, String passWord) {

        return LoginMenuMessages.ADMIN_LOGIN_SUCCESSFUL;
    }

    public static LoginMenuMessages checkLoginUser(String userName, String passWord) {

        return LoginMenuMessages.USER_LOGIN_SUCCESSFUL;
    }
}
