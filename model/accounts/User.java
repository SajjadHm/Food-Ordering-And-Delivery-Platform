package model.accounts;

import model.resturant.FoodList;
import model.resturant.Resturant;
import model.social.Comment;
import model.social.Rating;

import java.util.ArrayList;

public class User extends Account
{
    private int balance ;
    private boolean loginStatus;

    private Resturant userCurrentRestaurant;
    String location;
    ArrayList<Comment> userComments = new ArrayList<>();
    ArrayList<Rating> userRatings = new ArrayList<>();


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

    public void searchRestaurant(String restaurantName)
    {

    }
    public void selectRestaurant(int restaurantId)
    {

    }
    public void searchFood(String foodName)
    {

    }
    public void selectFood(int foodId)
    {

    }
    public void displayComments(Resturant restaurant)
    {

    }
    public void addNewComments(Resturant restaurant)
    {

    }
    public void editComments(int commentId ,Resturant restaurant)
    {

    }
    public void displayRating(Resturant restaurant)
    {

    }
    public void submitRating(Resturant restaurant)
    {

    }
    public void editRating(Resturant restaurant)
    {

    }
    public void displayComments(FoodList food)
    {

    }
    public void addNewComments(FoodList food)
    {

    }
    public void editComments(int commentId , FoodList food)
    {

    }
    public void displayRating(FoodList food)
    {

    }
    public void submitRating(FoodList food)
    {

    }
    public void editRating(FoodList food)
    {

    }
    public void addFoodTOCart()
    {

    }
    public void orderHistory()
    {

    }
    public void selectOrder()
    {

    }
    public  void displayCartStatus()
    {

    }
    public void confirmOrder()
    {

    }
    public void showDeliveryTime()
    {

    }
    public void chargeAccount(int amount)
    {
        this.balance = this.balance + amount;
    }
//    public void displayAccountCharge()
//    {
//
//    }




















}
