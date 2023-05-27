package model.accounts;

import model.resturant.Resturant;

import java.util.ArrayList;

public class Manager extends Account {
    private final ArrayList<Resturant> resturants;

    public Manager(String userName, String passWord, String firstName, String lastName) {
        super(userName, passWord, firstName, lastName);
        resturants = new ArrayList<>();
    }

    public Resturant getRestaurant(String name) {
        if (resturants.size() == 0) return null;
        for (Resturant resturant : resturants) {
            if (name.equals(resturant.getName())) return resturant;
        }
        return null;
    }

    public ArrayList<Resturant> getResturants() {
        return resturants;
    }
}
