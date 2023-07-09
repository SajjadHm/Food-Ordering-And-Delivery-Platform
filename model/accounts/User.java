package model.accounts;

import model.Cart;
import model.resturant.Food;
import model.resturant.Order;
import model.resturant.Resturant;
import model.social.Comment;
import model.social.Rating;

import java.util.ArrayList;
import java.util.HashMap;

public class User extends Account
{
    private int balance ;
    private boolean loginStatus;
    private Resturant userCurrentRestaurant = null;
    private Food userCurrentFood = null;
    String location;
    private  HashMap<Comment,Resturant> userComments = new HashMap<>();
    private  HashMap<Rating,Resturant> userRatings = new HashMap<>();
    private  HashMap<Comment,Food> userCommentsFood = new HashMap<>();
    private  HashMap<Rating,Food> userRatingsFood = new HashMap<>();
    private ArrayList<Order> orders = new ArrayList<>();
    private ArrayList<Cart> userCart  = new ArrayList<>();



    public User(String userName, String passWord, String firstName, String lastName)
    {
        super(userName, passWord, firstName, lastName);
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
    public Resturant getUserCurrentRestaurant()
    {
        return userCurrentRestaurant;
    }
    public void setUserCurrentRestaurant(Resturant userCurrentRestaurant)
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

    public HashMap<Comment, Resturant> getUserComments()
    {
        return userComments;
    }

    public HashMap<Rating, Resturant> getUserRatings()
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

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
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





















}
