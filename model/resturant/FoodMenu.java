package model.resturant;

public class FoodMenu extends FoodList {
    private String id;

    FoodMenu(String name, String id) {
        super(name);
        this.id = id;
    }
}
