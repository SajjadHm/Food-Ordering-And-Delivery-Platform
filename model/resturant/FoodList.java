package model.resturant;

import java.util.ArrayList;

abstract class FoodList {
    private final ArrayList<Food> foods;
    private String name;

    {
        foods = new ArrayList<>();
    }

    FoodList(String name) {
        this.name = name;
    }
}
