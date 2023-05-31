package model.resturant;

import model.Memory;
import model.enums.ResturantFoodType;


public class Resturant {
    private String name, location;
    private ResturantFoodType[] foodTypes;
    private final FoodMenu menu;
    private final String id;

    public Resturant(String name, ResturantFoodType[] foodTypes, String location, String id) {
        this.name = name;
        this.foodTypes = foodTypes.clone();
        this.id = id;
        this.location = location;
        this.menu = new FoodMenu(name, id);
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public FoodMenu getMenu() {
        return menu;
    }

    public FoodMenu getListedMenu() {
        FoodMenu listedMenu = new FoodMenu();
        for (Food food : menu) {
            if (!food.isUnlisted()) listedMenu.add(food);
        }
        return listedMenu;
    }
}
