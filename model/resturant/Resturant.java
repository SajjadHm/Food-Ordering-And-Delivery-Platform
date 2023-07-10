package model.resturant;

import model.Memory;
import model.enums.ResturantFoodType;
import model.social.Comment;
import model.social.Rating;

import java.util.ArrayList;


public class Resturant {
    private String name, location;
    private ArrayList<ResturantFoodType> foodTypes;
    private final FoodMenu menu;
    private final String id;
    private Food selectedFood;
    private ArrayList<Comment> comments = new ArrayList<>();

    private ArrayList<Rating> ratings = new ArrayList<>();

    public Resturant(String name, ArrayList<ResturantFoodType> foodTypes, String location, String id) {
        this.name = name;
        this.foodTypes = (ArrayList<ResturantFoodType>) foodTypes.clone();
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

    public ArrayList<ResturantFoodType> getFoodTypes() {
        return foodTypes;
    }

    public void setFoodTypes(ArrayList<ResturantFoodType> foodTypes) {
        // TODO: empty foods menu
        this.foodTypes = (ArrayList<ResturantFoodType>) foodTypes.clone();
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
        if (menu.size() == 0) return null;
        FoodMenu listedMenu = new FoodMenu();
        for (Food food : menu) {
            if (!food.isUnlisted()) listedMenu.add(food);
        }
        return listedMenu;
    }

    public Food getSelectedFood() {
        return selectedFood;
    }

    public void setSelectedFood(Food selectedFood) {
        this.selectedFood = selectedFood;
    }


    public ArrayList<Comment> getComments()
    {
        return comments;
    }

    public ArrayList<Rating> getRatings()
    {
        return ratings;
    }


}
