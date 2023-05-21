package model;

import model.accounts.Account;
import model.accounts.Admin;
import model.accounts.User;

import java.util.ArrayList;

public class Memory {
    private final static ArrayList<Admin> admins;
    private final static ArrayList<User> users;
    private static Account currentAccount;

    static {
        admins = new ArrayList<>();
        users = new ArrayList<>();
        currentAccount = null;
    }

    public static Admin getAdmin(String userName) {
        if (admins.size() == 0) return null;
        for (Admin admin : admins) {
            if (userName.equals(admin.getUserName())) return admin;
        }
        return null;
    }

    public static User getUser(String userName) {
        if (users.size() == 0) return null;
        for (User user : users) {
            if (userName.equals(user.getUserName())) return user;
        }
        return null;
    }

    public static ArrayList<Admin> getAdmins() {
        return admins;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static Account getCurrentAccount() {
        return currentAccount;
    }

    public static void setCurrentAccount(Account currentAccount) {
        Memory.currentAccount = currentAccount;
    }
}
