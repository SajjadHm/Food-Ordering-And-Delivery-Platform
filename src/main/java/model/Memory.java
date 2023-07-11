package model;

import model.accounts.Account;
import model.accounts.Manager;
import model.accounts.User;
import model.resturant.Food;
import model.resturant.Restaurant;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.HashMap;

public class Memory {
    private final static ArrayList<Manager> managers;
    private final static ArrayList<User> users;
    private final static HashMap<String, Restaurant> resturantsList;
    private static Account currentAccount;
    private static User currentUser;
    private static Restaurant currentRestaurant;
    public static final DateTimeFormatter dateTimeFormatter;

    static {
        managers = new ArrayList<>();
        users = new ArrayList<>();
        currentAccount = null;
        resturantsList = new HashMap<>();
        dateTimeFormatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy").appendLiteral("/")
                .appendPattern("MM").appendLiteral("/")
                .appendPattern("dd").appendLiteral("  ")
                .appendPattern("HH").appendLiteral(":")
                .appendPattern("mm").appendLiteral(":")
                .appendPattern("ss")
                .toFormatter();
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
        return resturantsList.get(id);
    }

    public static ArrayList<Restaurant> getRestaurants(String name)
    {
        if (resturantsList.size() == 0) return null;
        ArrayList<Restaurant> sameNameRestaurants = new ArrayList<>();
        for (Restaurant restaurant : resturantsList.values())
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

    public static HashMap<String, Restaurant> getResturantsList() {
        return resturantsList;
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
        for(HashMap.Entry<String, Restaurant> resturantEntry :Memory.getResturantsList().entrySet())
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
}
