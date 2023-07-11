package model.accounts;

import model.Memory;
import model.resturant.FoodMenu;
import model.resturant.Resturant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Manager extends Account {
    private final ArrayList<String> restaurantsID;
    private Resturant currentRestaurant;

    public Manager(String userName, String passWord, String firstName, String lastName)
    {
        super(userName, passWord, firstName, lastName);
        restaurantsID = new ArrayList<>();
        currentRestaurant = null;
    }

    public Resturant getRestaurant(String name) {
        if (restaurantsID.size() == 0) return null;
        for (String id : restaurantsID) {
            if (name.equals(Memory.getResturantsList().get(id).getName())) return Memory.getResturantsList().get(id);
        }
        return null;
    }

    public Resturant getRestaurantById(String id) {
        return Memory.getResturantsList().get(id);
    }

    public HashMap<String, Resturant> getResturants() {
        HashMap<String, Resturant> output = new HashMap<>();
        for (String id : restaurantsID) {
            output.put(id, Memory.getResturantsList().get(id));
        }
        return output;
    }

    public Resturant getCurrentRestaurant() {
        return currentRestaurant;
    }

    public void setCurrentRestaurant(Resturant currentRestaurant) {
        this.currentRestaurant = currentRestaurant;
    }

    public ArrayList<String> getRestaurantsID() {
        return restaurantsID;
    }
}
