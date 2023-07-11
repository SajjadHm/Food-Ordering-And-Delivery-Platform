package model.accounts;

import model.Memory;
import model.resturant.Restaurant;

import java.util.ArrayList;
import java.util.HashMap;

public class Manager extends Account {
    private final ArrayList<String> restaurantsID;
    private Restaurant currentRestaurant;

    public Manager(String userName, String passWord, String firstName, String lastName, boolean isPasswordHashed)
    {
        super(userName, passWord, firstName, lastName, isPasswordHashed);
        restaurantsID = new ArrayList<>();
        currentRestaurant = null;
    }

    public Restaurant getRestaurant(String name) {
        if (restaurantsID.size() == 0) return null;
        for (String id : restaurantsID) {
            if (name.equals(Memory.getResturantsList().get(id).getName())) return Memory.getResturantsList().get(id);
        }
        return null;
    }

    public Restaurant getRestaurantById(String id) {
        return Memory.getResturantsList().get(id);
    }

    public HashMap<String, Restaurant> getResturants() {
        HashMap<String, Restaurant> output = new HashMap<>();
        for (String id : restaurantsID) {
            output.put(id, Memory.getResturantsList().get(id));
        }
        return output;
    }

    public Restaurant getCurrentRestaurant() {
        return currentRestaurant;
    }

    public void setCurrentRestaurant(Restaurant currentRestaurant) {
        this.currentRestaurant = currentRestaurant;
    }

    public ArrayList<String> getRestaurantsID() {
        return restaurantsID;
    }
}
