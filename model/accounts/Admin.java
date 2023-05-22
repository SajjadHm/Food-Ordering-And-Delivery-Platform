package model.accounts;

import model.resturant.Resturant;

import java.util.ArrayList;

public class Admin extends Account {
    private final ArrayList<Resturant> resturants;

    public Admin(String userName, String passWord) {
        super(userName, passWord);
        resturants = new ArrayList<>();
    }

    public ArrayList<Resturant> getResturants() {
        return resturants;
    }
}
