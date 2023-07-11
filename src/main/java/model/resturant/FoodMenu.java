package model.resturant;

import model.Memory;
import model.accounts.Manager;

public class FoodMenu extends FoodList {
    public FoodMenu(String name, String id) {
        super(name, id);
    }

    public FoodMenu() {
        this("", "");
    }

    public Food get(String foodID) {
        if (size() == 0) return null;
        for (Food food : this) {
            if (food.getId().equals(foodID)) return food;
        }
        return null;
    }

    public Food getByName(String name) {
        if (size() == 0) return null;
        for (Food food : this) {
            if (food.getName().equals(name)) return food;
        }
        return null;
    }

    public void addFood(String name, int price) {
        addFood(name, price, 0);
    }

    public void addFood(String name, int price, int discountPercent) {
        String newID = getUID(String.valueOf(Memory.getFoodIdCount()), 8, 8 + 5);
        while (get(newID) != null) {
            Memory.setFoodIdCount(Memory.getFoodIdCount() + 1);
            newID = getUID(String.valueOf(Memory.getFoodIdCount()), 8, 8 + 5);
        }
        Memory.setFoodIdCount(Memory.getFoodIdCount() + 1);
        Food food = new Food(newID, name, price, discountPercent);
        food.setRestaurantID(((Manager) Memory.getCurrentAccount()).getCurrentRestaurant());
        add(food);
    }

    public static int getFoodIdCount() {
        return Memory.getFoodIdCount();
    }
}
