package model.resturant;

import model.Memory;
import model.enums.ResturantFoodType;


public class Resturant {
    private String name, location;
    private ResturantFoodType[] foodTypes;

    private final String id;

    public Resturant(String name, ResturantFoodType[] foodTypes, String location, String id) {
        this.name = name;
        this.foodTypes = foodTypes.clone();
        this.id = id;
        this.location = location;
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

}
