package model.resturant;

import java.util.ArrayList;

public class FoodList {
    private final ArrayList<Food> foods;
    private String name;
    private final String id;


    {
        foods = new ArrayList<>();
    }

    FoodList(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }
}
