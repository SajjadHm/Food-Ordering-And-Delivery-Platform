package model.resturant;

import java.util.ArrayList;

public class Food {
    private final String id;
    private String name;
    private int price;
    private double discountPercent0;
    private boolean isUnlisted;
    private ArrayList comments;
    private ArrayList rating;

    {
        isUnlisted = false;
    }

    public Food() {
    }
}
