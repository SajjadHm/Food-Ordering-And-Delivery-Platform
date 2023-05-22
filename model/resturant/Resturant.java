package model.resturant;

import model.enums.ResturantFoodType;

public class Resturant {
    private String name;
    private ResturantFoodType foodType;
    private int id;
    private static int idCount;

    static {
        idCount = 1;
    }

    Resturant(String name, ResturantFoodType foodType) {
        this.name = name;
        this.foodType = foodType;
        this.id = idCount;
        idCount++;
    }

}
