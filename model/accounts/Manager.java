package model.accounts;

import model.resturant.FoodMenu;
import model.resturant.Resturant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Manager extends Account {
    private final HashMap<String, Resturant> resturants;

    public Manager(String userName, String passWord, String firstName, String lastName) {
        super(userName, passWord, firstName, lastName);
        resturants = new HashMap<>();
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
}
