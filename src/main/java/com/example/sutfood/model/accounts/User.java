package com.example.sutfood.model.accounts;

import com.example.sutfood.model.resturant.Food;
import com.example.sutfood.model.Cart;
import com.example.sutfood.model.resturant.Order;
import com.example.sutfood.model.resturant.Restaurant;
import com.example.sutfood.model.social.Comment;
import com.example.sutfood.model.social.Rating;

import java.util.ArrayList;
import java.util.HashMap;

public class User extends Account
{
    private int balance ;
    private boolean loginStatus;
    private Restaurant userCurrentRestaurant = null;
    private Food userCurrentFood = null;
    private String location;
    private  HashMap<Comment, Restaurant> userComments = new HashMap<>();
    private  HashMap<Rating, Restaurant> userRatings = new HashMap<>();
    private  HashMap<Comment,Food> userCommentsFood = new HashMap<>();
    private  HashMap<Rating,Food> userRatingsFood = new HashMap<>();
    private ArrayList<Order> ordersHistory = new ArrayList<>();
    private ArrayList<Cart> userCart  = new ArrayList<>();



    public User(String userName, String passWord, String firstName, String lastName, boolean isPasswordHashed)
    {
        super(userName, passWord, firstName, lastName, isPasswordHashed);
        this.balance = 0 ;
        this.loginStatus = false ;
    }

    public boolean getLoginStatus()
    {
        return loginStatus;
    }
    public void setLoginStatus(boolean loginStatus)
    {
        this.loginStatus = loginStatus;
    }
    public int getBalance()
    {
        return balance;
    }
    public void setBalance(int balance)
    {
        this.balance = balance;
    }
    public Restaurant getUserCurrentRestaurant()
    {
        return userCurrentRestaurant;
    }
    public void setUserCurrentRestaurant(Restaurant userCurrentRestaurant)
    {
        this.userCurrentRestaurant = userCurrentRestaurant;
    }
    public Food getUserCurrentFood()
    {
        return userCurrentFood;
    }

    public void setUserCurrentFood(Food userCurrentFood)
    {
        this.userCurrentFood = userCurrentFood;
    }

    public HashMap<Comment, Restaurant> getUserComments()
    {
        return userComments;
    }

    public HashMap<Rating, Restaurant> getUserRatings()
    {
        return userRatings;
    }
    public HashMap<Comment, Food> getUserCommentsFood() {
        return userCommentsFood;
    }

    public void setUserCommentsFood(HashMap<Comment, Food> userCommentsFood) {
        this.userCommentsFood = userCommentsFood;
    }

    public HashMap<Rating, Food> getUserRatingsFood() {
        return userRatingsFood;
    }

    public void setUserRatingsFood(HashMap<Rating, Food> userRatingsFood) {
        this.userRatingsFood = userRatingsFood;
    }

    public ArrayList<Order> getOrdersHistory() {
        return ordersHistory;
    }

    public void setOrdersHistory(ArrayList<Order> ordersHistory) {
        this.ordersHistory = ordersHistory;
    }

    public void setUserCart(ArrayList<Cart> userCart) {
        this.userCart = userCart;
    }

    public ArrayList<Cart> getUserCart() {
        return userCart;
    }

    public void chargeAccount(int amount)
    {
        this.balance = this.balance + amount;
    }
    public void logout()
    {
        this.setLoginStatus(false);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
