package model.resturant;

import java.util.ArrayList;

public class FoodList extends ArrayList<Food> {
    private String name;
    private final String id;


    FoodList(String name, String id) {
        this.name = name;
        this.id = id;
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
