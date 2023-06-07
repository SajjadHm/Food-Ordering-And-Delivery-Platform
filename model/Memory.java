package model;

import model.accounts.Account;
import model.accounts.Manager;
import model.accounts.User;
import model.resturant.Resturant;

import java.util.ArrayList;

public class Memory {
    private final static ArrayList<Manager> MANAGERS;
    private final static ArrayList<User> users;
    private final static ArrayList<Resturant> listOfRestaurants;
    private static Account currentAccount;
    private static User currentUser;
    private static Resturant currentResturant;

    static {
        MANAGERS = new ArrayList<>();
        users = new ArrayList<>();
        listOfRestaurants = new ArrayList<>();
        currentAccount = null;
    }

    public static Manager getAdmin(String userName) {
        if (MANAGERS.size() == 0) return null;
        for (Manager manager : MANAGERS) {
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

    public static Resturant getRestaurant(int id)
    {
        if (listOfRestaurants.size() == 0) return null;
        for (Resturant resturant : listOfRestaurants) {
            if (id == resturant.getId()) return resturant;
        }
        return null;
    }

    public static ArrayList<Resturant> getRestaurants(String name)
    {
        if (listOfRestaurants.size() == 0) return null;
        ArrayList<Resturant> sameNameRestaurants = new ArrayList<>();
        for (Resturant resturant : listOfRestaurants)
        {
            if (name.equals(resturant.getName()))
            {
                sameNameRestaurants.add(resturant);
            }
        }
        if(sameNameRestaurants.size()==0) return null;
        return sameNameRestaurants;
    }

    public static ArrayList<Manager> getAdmins() {
        return MANAGERS;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static ArrayList<Resturant> getListOfRestaurants()
    {
        return listOfRestaurants;
    }

    public static Account getCurrentAccount() {
        return currentAccount;
    }

    public static void setCurrentAccount(Account currentAccount) {
        Memory.currentAccount = currentAccount;
    }

    public static User getCurrentUser() {
        return currentUser;
    }
    public static void setCurrentUser(User currentUser) {
        Memory.currentUser = currentUser;
    }

    public static Resturant getCurrentResturant() {
        return currentResturant;
    }

    public static void setCurrentResturant(Resturant currentResturant) {
        Memory.currentResturant = currentResturant;
    }
}
