package controllers;

import model.Memory;
import model.accounts.Manager;
import model.enums.ResturantFoodType;
import model.resturant.Food;
import model.resturant.FoodMenu;
import model.resturant.Restaurant;
import view.enums.managermenu.ManagerMenuMessages;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

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


    public static ManagerMenuMessages checkAddRestaurant(String name, String location, ResturantFoodType[] foodTypesArr) {
        ArrayList<ResturantFoodType> foodTypes = new ArrayList<>(Arrays.asList(foodTypesArr));
        Manager manager = (Manager) Memory.getCurrentAccount();
        Restaurant restaurant = manager.getRestaurant(name);
        if (restaurant != null) return ManagerMenuMessages.RESTAURANT_EXISTS;
        if (foodTypes == null) return ManagerMenuMessages.INVALID_FOOD_TYPE;
        while (manager.getRestaurantById(getID(String.valueOf(idCount), 0, 8)) != null) idCount++;
        restaurant = new Restaurant(name, foodTypes, location, getID(String.valueOf(idCount), 0, 8));
        Memory.getResturantsList().put(restaurant.getId(), restaurant);
        manager.getRestaurantsID().add(restaurant.getId());
        idCount++;
        return ManagerMenuMessages.RESTAURANT_ADDED;
    }

    public static ManagerMenuMessages checkLogout() {
        Memory.setCurrentAccount(null);
        return ManagerMenuMessages.LOGGED_OUT;
    }

    public static ManagerMenuMessages checkRemoveRestaurant(String id) {
        Manager manager = (Manager) Memory.getCurrentAccount();
        Restaurant restaurant = manager.getRestaurantById(id);
        if (restaurant == null) return ManagerMenuMessages.RESTAURANT_NOT_FOUND;
        manager.getResturants().remove(restaurant);
        return ManagerMenuMessages.RESTAURANT_REMOVED;
    }

    public static ManagerMenuMessages checkSelect(String id) {
        Manager manager = (Manager) Memory.getCurrentAccount();
        Restaurant restaurant = manager.getRestaurantById(id);
        if (restaurant == null) return ManagerMenuMessages.RESTAURANT_NOT_FOUND;
        manager.setCurrentRestaurant(restaurant);
        return ManagerMenuMessages.RESTAURANT_OPENED;
    }

    public static ManagerMenuMessages checkEditFoodType(ResturantFoodType[] resturantFoodTypes) {
        Manager manager = (Manager) Memory.getCurrentAccount();
        Restaurant restaurant = ((Manager) Memory.getCurrentAccount()).getCurrentRestaurant();
        if (restaurant == null) return ManagerMenuMessages.NO_RESTAURANT_SELECTED;
        if (resturantFoodTypes == null) return ManagerMenuMessages.INVALID_FOOD_TYPE;
        return ManagerMenuMessages.ARE_YOU_SURE_FOODTYPE;
    }

    public static ManagerMenuMessages editFoodType(ResturantFoodType[] resturantFoodTypesArr) {
        ArrayList<ResturantFoodType> resturantFoodTypes = new ArrayList<>(Arrays.asList(resturantFoodTypesArr));
        Restaurant restaurant = ((Manager) Memory.getCurrentAccount()).getCurrentRestaurant();
        if (restaurant == null) return ManagerMenuMessages.NO_RESTAURANT_SELECTED;
        restaurant.setFoodTypes(resturantFoodTypes);
        restaurant.getMenu().removeAll(restaurant.getMenu());
        return ManagerMenuMessages.RESTAURANT_FOOD_TYPE_CHANGED;
    }

    public static ManagerMenuMessages checkAddFood(String name, int price) {
        Restaurant restaurant = ((Manager) Memory.getCurrentAccount()).getCurrentRestaurant();
        if (restaurant == null) return ManagerMenuMessages.NO_RESTAURANT_SELECTED;
        FoodMenu menu = restaurant.getMenu();
        Food food = menu.getByName(name);
        if (food != null) return ManagerMenuMessages.FOOD_EXISTS;
        menu.addFood(name, price);
        return ManagerMenuMessages.FOOD_ADDED;

    }

    public static ManagerMenuMessages checkRemoveFood(String id) {
        Restaurant restaurant = ((Manager) Memory.getCurrentAccount()).getCurrentRestaurant();
        if (restaurant == null) return ManagerMenuMessages.NO_RESTAURANT_SELECTED;
        FoodMenu menu = restaurant.getMenu();
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
        Restaurant restaurant = ((Manager) Memory.getCurrentAccount()).getCurrentRestaurant();
        if (restaurant == null) return ManagerMenuMessages.NO_RESTAURANT_SELECTED;
        if (restaurant.getMenu().size() == 0) return ManagerMenuMessages.EMPTY_MENU;
        Food food = restaurant.getMenu().get(foodID);
        if (food == null) return ManagerMenuMessages.FOOD_NOT_FOUND;
        food.setUnlisted(true);
        return ManagerMenuMessages.FOOD_DEACTIVATED;
    }

    public static ManagerMenuMessages checkActiveFood(String foodID) {
        Restaurant restaurant = ((Manager) Memory.getCurrentAccount()).getCurrentRestaurant();
        if (restaurant == null) return ManagerMenuMessages.NO_RESTAURANT_SELECTED;
        if (restaurant.getMenu().size() == 0) return ManagerMenuMessages.EMPTY_MENU;
        Food food = restaurant.getMenu().get(foodID);
        if (food == null) return ManagerMenuMessages.FOOD_NOT_FOUND;
        food.setUnlisted(false);
        return ManagerMenuMessages.FOOD_DEACTIVATED;
    }

    public static ManagerMenuMessages checkDiscountFood(String foodID, double discountPercent, LocalDateTime timeStamp) {
        Restaurant restaurant = ((Manager) Memory.getCurrentAccount()).getCurrentRestaurant();
        if (restaurant == null) return ManagerMenuMessages.INVALID_COMMAND;
        FoodMenu menu = restaurant.getMenu();
        if (menu.size() == 0) return ManagerMenuMessages.EMPTY_MENU;
        Food food = menu.get(foodID);
        if (food == null) return ManagerMenuMessages.FOOD_NOT_FOUND;
        if (discountPercent <= 0.0 || discountPercent > 100.0) return ManagerMenuMessages.INVALID_DISCOUNT_PERCENT;
        if (LocalDateTime.now().compareTo(timeStamp) > 0) return ManagerMenuMessages.INVALID_TIMESTAMP;
        food.setDiscountPercent(discountPercent);
        food.setDiscountTime(timeStamp);
        return ManagerMenuMessages.FOOD_DISCOUNTED;
    }

    public static ManagerMenuMessages checkSelectFood(String foodID) {
        Restaurant restaurant = ((Manager) Memory.getCurrentAccount()).getCurrentRestaurant();
        if (restaurant == null) return ManagerMenuMessages.INVALID_COMMAND;
        FoodMenu menu = restaurant.getMenu();
        if (menu.size() == 0) return ManagerMenuMessages.EMPTY_MENU;
        Food food = menu.get(foodID);
        if (food == null) return ManagerMenuMessages.FOOD_NOT_FOUND;
        restaurant.setSelectedFood(food);
        return ManagerMenuMessages.FOOD_SELECTED;
    }

    public static ManagerMenuMessages checkDisplayRatings() {
        Restaurant restaurant = ((Manager) Memory.getCurrentAccount()).getCurrentRestaurant();
        if (restaurant == null) return ManagerMenuMessages.INVALID_COMMAND;
        Food food = restaurant.getSelectedFood();
        if (food == null) return ManagerMenuMessages.NO_FOOD_SELECTED;
        return ManagerMenuMessages.DISPLAY_RATINGS;
    }

    public static ManagerMenuMessages checkDisplayComments() {
        Restaurant restaurant = ((Manager) Memory.getCurrentAccount()).getCurrentRestaurant();
        if (restaurant == null) return ManagerMenuMessages.INVALID_COMMAND;
        Food food = restaurant.getSelectedFood();
        if (food == null) return ManagerMenuMessages.NO_FOOD_SELECTED;
        return ManagerMenuMessages.DISPLAY_COMMENTS;
    }

    public static ManagerMenuMessages checkDeselectFood() {
        Restaurant restaurant = ((Manager) Memory.getCurrentAccount()).getCurrentRestaurant();
        if (restaurant == null) return ManagerMenuMessages.INVALID_COMMAND;
        Food food = restaurant.getSelectedFood();
        if (food == null) return ManagerMenuMessages.NO_FOOD_SELECTED;
        restaurant.setSelectedFood(null);
        return ManagerMenuMessages.FOOD_DESELECTED;
    }
}