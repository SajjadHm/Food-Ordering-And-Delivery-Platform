package model.accounts;

import model.resturant.FoodMenu;
import model.resturant.Resturant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Manager extends Account {
    private final HashMap<String, Resturant> resturants;
    private Resturant currentRestaurant;

    public Manager(String userName, String passWord, String firstName, String lastName) {
        super(userName, passWord, firstName, lastName);
        resturants = new HashMap<>();
        currentRestaurant = null;
    }

    public Resturant getRestaurant(String name) {
        if (resturants.size() == 0) return null;
        for (Resturant resturant : resturants.values()) {
            if (name.equals(resturant.getName())) return resturant;
        }
        return null;
    }

    public Resturant getRestaurantById(String id) {
        return resturants.get(id);
    }

    public HashMap<String, Resturant> getResturants() {
        return resturants;
    }

    public Resturant getCurrentRestaurant() {
        return currentRestaurant;
    }

    public void setCurrentRestaurant(Resturant currentRestaurant) {
        this.currentRestaurant = currentRestaurant;
    }
}
