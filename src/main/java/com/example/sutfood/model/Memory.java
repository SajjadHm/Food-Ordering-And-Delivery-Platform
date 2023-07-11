package com.example.sutfood.model;

import com.example.sutfood.model.resturant.Food;
import com.example.sutfood.model.accounts.Account;
import com.example.sutfood.model.accounts.Manager;
import com.example.sutfood.model.accounts.User;
import com.example.sutfood.model.resturant.Restaurant;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.HashMap;

public class Memory {
    private final static ArrayList<Manager> managers;
    private final static ArrayList<User> users;
    private final static HashMap<String, Restaurant> restaurantsList;
    private static Account currentAccount;
    private static User currentUser;
    public static final DateTimeFormatter dateTimeFormatter;
    private static int foodIdCount;

    static {
        managers = new ArrayList<>();
        users = new ArrayList<>();
        currentAccount = null;
        restaurantsList = new HashMap<>();
        dateTimeFormatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy").appendLiteral("/")
                .appendPattern("MM").appendLiteral("/")
                .appendPattern("dd").appendLiteral("  ")
                .appendPattern("HH").appendLiteral(":")
                .appendPattern("mm").appendLiteral(":")
                .appendPattern("ss")
                .toFormatter();
        foodIdCount = 0;
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

    public static Restaurant getRestaurant(String id)
    {
        return restaurantsList.get(id);
    }

    public static ArrayList<Restaurant> getRestaurants(String name)
    {
        if (restaurantsList.size() == 0) return null;
        ArrayList<Restaurant> sameNameRestaurants = new ArrayList<>();
        for (Restaurant restaurant : restaurantsList.values())
        {
            if (restaurant.getName().contains(name))
            {
                sameNameRestaurants.add(restaurant);
            }
        }
        if(sameNameRestaurants.size()==0) return null;
        return sameNameRestaurants;
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

    public static HashMap<String, Restaurant> getRestaurantsList() {
        return restaurantsList;
    }

    public static User getCurrentUser() {
        return currentUser;
    }
    public static void setCurrentUser(User currentUser) {
        Memory.currentUser = currentUser;
    }


    public static Food getFood(String id)
    {
        Food food = null;
        for(HashMap.Entry<String, Restaurant> resturantEntry :Memory.getRestaurantsList().entrySet())
        {
            for(Food foodLoop:resturantEntry.getValue().getMenu())
            {
                if(foodLoop.getId().equals(id))
                {
                    food = foodLoop;
                    break;
                }

            }
        }

       return food;
    }

    public static int getFoodIdCount() {
        return foodIdCount;
    }

    public static void setFoodIdCount(int foodIdCount) {
        Memory.foodIdCount = foodIdCount;
    }

}
