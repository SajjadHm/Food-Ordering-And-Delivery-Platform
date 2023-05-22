package model.resturant;

import model.enums.ResturantFoodType;

public class Resturant {
    private String name;
    private ResturantFoodType foodType;
    private final int id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ResturantFoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(ResturantFoodType foodType) {
        // TODO: empty foods menu
        this.foodType = foodType;
    }

    public int getId() {
        return id;
    }

    public static int getIdCount() {
        return idCount;
    }

}
