package model;

import model.accounts.Account;
import model.accounts.Manager;
import model.accounts.User;

import java.util.ArrayList;

public class Memory {
    private final static ArrayList<Manager> managers;
    private final static ArrayList<User> users;
    private static Account currentAccount;
    // private static Resturant currentResturant;

    static {
        managers = new ArrayList<>();
        users = new ArrayList<>();
        currentAccount = null;
    }

    public static Manager getAdmin(String userName) {
        if (managers.size() == 0) return null;
        for (Manager manager : managers) {
            if (userName.equals(manager.getUserName())) return manager;
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

    public static ArrayList<Manager> getAdmins() {
        return managers;
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

    /*
    public static Resturant getCurrentResturant() {
        return currentResturant;
    }

    public static void setCurrentResturant(Resturant currentResturant) {
        Memory.currentResturant = currentResturant;
    }
     */
}
