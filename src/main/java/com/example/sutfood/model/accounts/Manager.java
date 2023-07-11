package com.example.sutfood.model.accounts;

import com.example.sutfood.model.Memory;
import com.example.sutfood.model.resturant.Restaurant;

import java.util.ArrayList;
import java.util.HashMap;

public class Manager extends Account {
    private final ArrayList<String> restaurantsID;
    private String currentRestaurantID;

    public Manager(String userName, String passWord, String firstName, String lastName, boolean isPasswordHashed)
    {
        super(userName, passWord, firstName, lastName, isPasswordHashed);
        restaurantsID = new ArrayList<>();
        currentRestaurantID = null;
    }

    public Manager(String userName, String passWord, String firstName, String lastName, boolean isPasswordHashed, ArrayList<String> restaurantsID, String currentRestaurantID) {
        super(userName, passWord, firstName, lastName, isPasswordHashed);
        this.restaurantsID = restaurantsID;
        this.currentRestaurantID = currentRestaurantID;
    }

    public Restaurant getRestaurant(String name) {
        if (restaurantsID.size() == 0) return null;
        for (String id : restaurantsID) {
            if (name.equals(Memory.getRestaurantsList().get(id).getName())) return Memory.getRestaurantsList().get(id);
        }
        return null;
    }

    public Restaurant getRestaurantById(String id) {
        return Memory.getRestaurantsList().get(id);
    }

    public HashMap<String, Restaurant> getResturants() {
        HashMap<String, Restaurant> output = new HashMap<>();
        for (String id : restaurantsID) {
            output.put(id, Memory.getRestaurantsList().get(id));
        }
        return output;
    }

    public Restaurant getCurrentRestaurant() {
        return Memory.getRestaurant(currentRestaurantID);
    }

    public void setCurrentRestaurant(Restaurant currentRestaurant) {
        this.currentRestaurantID = currentRestaurant.getId();
    }

    public ArrayList<String> getRestaurantsID() {
        return restaurantsID;
    }

    public String getCurrentRestaurantID() {
        return currentRestaurantID;
    }
}
