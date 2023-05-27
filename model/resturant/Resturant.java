package model.resturant;

import model.enums.ResturantFoodType;

public class Resturant {
    private String name, location;
    private ResturantFoodType[] foodTypes;

    private final String id;
    private static int idCount;

    static {
        idCount = 1;
    }

    public Resturant(String name, ResturantFoodType[] foodTypes, String location) {
        this.name = name;
        this.foodTypes = foodTypes.clone();
        this.id = String.valueOf(idCount);
        this.location = location;
        idCount++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ResturantFoodType[] getFoodTypes() {
        return foodTypes;
    }

    public void setFoodTypes(ResturantFoodType[] foodTypes) {
        // TODO: empty foods menu
        this.foodTypes = foodTypes.clone();
    }

    public String getId() {
        return id;
    }

    public static int getIdCount() {
        return idCount;
    }

}
