package model.accounts;

import model.resturant.Resturant;

import java.util.ArrayList;

public class Manager extends Account {
    private final ArrayList<Resturant> resturants;

    public Manager(String userName, String passWord, String firstName, String lastName) {
        super(userName, passWord, firstName, lastName);
        resturants = new ArrayList<>();
    }

    public ArrayList<Resturant> getResturants() {
        return resturants;
    }
}
