package com.example.sutfood.model.resturant;

import com.example.sutfood.model.enums.ResturantFoodType;
import com.example.sutfood.model.social.Comment;
import com.example.sutfood.model.social.Rating;

import java.util.ArrayList;


public class Restaurant {
    private String name, location;
    private ArrayList<ResturantFoodType> foodTypes;
    private final FoodMenu menu;
    private final String id;
    private Food selectedFood;
    private ArrayList<Order> orderHistory = new ArrayList<>();
    private ArrayList<Order> currentOrders = new ArrayList<>();
    private final ArrayList<Comment> comments = new ArrayList<>();

    private ArrayList<Rating> ratings = new ArrayList<>();

    public Restaurant(String name, ArrayList<ResturantFoodType> foodTypes, String location, String id) {
        this.name = name;
        this.foodTypes = (ArrayList<ResturantFoodType>) foodTypes.clone();
        this.id = id;
        this.location = location;
        this.menu = new FoodMenu(name, id);
    }

    public Restaurant(String name, ArrayList<ResturantFoodType> foodTypes, String location, String id, FoodMenu menu) {
        this.name = name;
        this.foodTypes = (ArrayList<ResturantFoodType>) foodTypes.clone();
        this.id = id;
        this.location = location;
        this.menu = menu;
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

    public ArrayList<Order> getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(ArrayList<Order> orderHistory) {
        this.orderHistory = orderHistory;
    }

    public ArrayList<Order> getCurrentOrders() {
        return currentOrders;
    }

    public void setCurrentOrders(ArrayList<Order> currentOrders) {
        this.currentOrders = currentOrders;
    }
}
