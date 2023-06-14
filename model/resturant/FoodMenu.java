package model.resturant;

public class FoodMenu extends FoodList {
    private static int foodIdCount = 0;
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
        String newID = getUID(String.valueOf(foodIdCount), 8, 8 + 5);
        while (get(newID) != null) {
            foodIdCount++;
            newID = getUID(String.valueOf(foodIdCount), 8, 8 + 5);
        }
        foodIdCount++;
        Food food = new Food(newID, name, price, discountPercent);
        add(food);
    }

    public static int getFoodIdCount() {
        return foodIdCount;
    }
}
