package controllers;

import model.Memory;
import model.accounts.Account;
import model.accounts.Manager;
import model.enums.ResturantFoodType;
import model.resturant.Resturant;
import view.enums.managermenu.ManagerMenuCommands;
import view.enums.managermenu.ManagerMenuMessages;
import view.enums.managermenu.ManagerMenuResults;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ManagerMenuController {
    private static int idCount;

    static {
        idCount = 0;
    }

    private static String getID(String seed) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(seed.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashText = no.toString(16);
            while (hashText.length() < 8) {
                hashText = "0" + hashText;
            }
            return hashText.substring(0, 8);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }


    public static ManagerMenuMessages checkAddRestaurant(String name, String location, ResturantFoodType[] foodTypes) {
        Manager manager = (Manager) Memory.getCurrentAccount();
        Resturant resturant = manager.getRestaurant(name);
        if (resturant != null) return ManagerMenuMessages.RESTAURANT_EXISTS;
        if (foodTypes == null) return ManagerMenuMessages.INVALID_FOOD_TYPE;
        while (manager.getRestaurantById(getID(String.valueOf(idCount))) != null) idCount++;
        resturant = new Resturant(name, foodTypes, location, getID(String.valueOf(idCount)));
        manager.getResturants().put(resturant.getId(), resturant);
        idCount++;
        return ManagerMenuMessages.RESTAURANT_ADDED;
    }

    public static ManagerMenuMessages checkLogout() {
        Memory.setCurrentAccount(null);
        Memory.setCurrentResturant(null);
        return ManagerMenuMessages.LOGGED_OUT;
    }

    public static ManagerMenuMessages checkRemoveRestaurant(String id) {
        Manager manager = (Manager) Memory.getCurrentAccount();
        Resturant resturant = manager.getRestaurantById(id);
        if (resturant == null) return ManagerMenuMessages.RESTAURANT_NOT_FOUND;
        manager.getResturants().remove(resturant);
        return ManagerMenuMessages.RESTAURANT_REMOVED;
    }

    public static ManagerMenuMessages checkSelect(String id) {
        Manager manager = (Manager) Memory.getCurrentAccount();
        Resturant resturant = manager.getRestaurantById(id);
        if (resturant == null) return ManagerMenuMessages.RESTAURANT_NOT_FOUND;
        Memory.setCurrentResturant(resturant);
        return ManagerMenuMessages.RESTAURANT_OPENED;
    }

    public static int getIdCount() {
        return idCount;
    }
}
