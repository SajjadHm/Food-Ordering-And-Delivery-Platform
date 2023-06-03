package controllers;

import model.Memory;
import model.accounts.Manager;
import model.enums.ResturantFoodType;
import model.resturant.Food;
import model.resturant.FoodMenu;
import model.resturant.Resturant;
import view.enums.managermenu.ManagerMenuMessages;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

public class ManagerMenuController {
    private static int idCount;

    static {
        idCount = 0;
    }

    private static String getID(String seed, int start, int end) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(seed.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashText = no.toString(16);
            while (hashText.length() < 8) {
                hashText = "0" + hashText;
            }
            return hashText.substring(start, end);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


    public static ManagerMenuMessages checkAddRestaurant(String name, String location, ResturantFoodType[] foodTypes) {
        Manager manager = (Manager) Memory.getCurrentAccount();
        Resturant resturant = manager.getRestaurant(name);
        if (resturant != null) return ManagerMenuMessages.RESTAURANT_EXISTS;
        if (foodTypes == null) return ManagerMenuMessages.INVALID_FOOD_TYPE;
        while (manager.getRestaurantById(getID(String.valueOf(idCount), 0, 8)) != null) idCount++;
        resturant = new Resturant(name, foodTypes, location, getID(String.valueOf(idCount), 0, 8));
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

    public static ManagerMenuMessages checkEditFoodType(ResturantFoodType[] resturantFoodTypes) {
        Manager manager = (Manager) Memory.getCurrentAccount();
        Resturant resturant = Memory.getCurrentResturant();
        if (resturant == null) return ManagerMenuMessages.NO_RESTAURANT_SELECTED;
        if (resturantFoodTypes == null) return ManagerMenuMessages.INVALID_FOOD_TYPE;
        return ManagerMenuMessages.ARE_YOU_SURE_FOODTYPE;
    }

    public static ManagerMenuMessages editFoodType(ResturantFoodType[] resturantFoodTypes) {
        Resturant resturant = Memory.getCurrentResturant();
        resturant.setFoodTypes(resturantFoodTypes);
        resturant.getMenu().removeAll(resturant.getMenu());
        return ManagerMenuMessages.RESTAURANT_FOOD_TYPE_CHANGED;
    }

    public static ManagerMenuMessages checkAddFood(String name, int price) {
        Resturant resturant = Memory.getCurrentResturant();
        if (resturant == null) return ManagerMenuMessages.NO_RESTAURANT_SELECTED;
        FoodMenu menu = resturant.getMenu();
        Food food = menu.getByName(name);
        if (food != null) return ManagerMenuMessages.FOOD_EXISTS;
        menu.addFood(name, price);
        return ManagerMenuMessages.FOOD_ADDED;

    }

    public static ManagerMenuMessages checkRemoveFood(String id) {
        Resturant resturant = Memory.getCurrentResturant();
        if (resturant == null) return ManagerMenuMessages.NO_RESTAURANT_SELECTED;
        FoodMenu menu = resturant.getMenu();
        if (menu.size() == 0) return ManagerMenuMessages.EMPTY_MENU;
        Food food = menu.get(id);
        if (food == null) return ManagerMenuMessages.FOOD_NOT_FOUND;
        menu.remove(food);
        return ManagerMenuMessages.FOOD_REMOVED;
    }

    public static int getIdCount() {
        return idCount;
    }

    public static ManagerMenuMessages checkDeactiveFood(String foodID) {
        // TODO: handle orders
        Resturant resturant = Memory.getCurrentResturant();
        if (resturant == null) return ManagerMenuMessages.NO_RESTAURANT_SELECTED;
        if (resturant.getMenu().size() == 0) return ManagerMenuMessages.EMPTY_MENU;
        Food food = resturant.getMenu().get(foodID);
        if (food == null) return ManagerMenuMessages.FOOD_NOT_FOUND;
        food.setUnlisted(true);
        return ManagerMenuMessages.FOOD_DEACTIVATED;
    }

    public static ManagerMenuMessages checkActiveFood(String foodID) {
        Resturant resturant = Memory.getCurrentResturant();
        if (resturant == null) return ManagerMenuMessages.NO_RESTAURANT_SELECTED;
        if (resturant.getMenu().size() == 0) return ManagerMenuMessages.EMPTY_MENU;
        Food food = resturant.getMenu().get(foodID);
        if (food == null) return ManagerMenuMessages.FOOD_NOT_FOUND;
        food.setUnlisted(false);
        return ManagerMenuMessages.FOOD_DEACTIVATED;
    }
}
