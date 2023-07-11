package model.resturant;

import model.social.Comment;
import model.social.Rating;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Food {
    private final String id;
    private String name;
    private int price;
    private double discountPercent;
    private boolean isUnlisted;
    private String restaurantID;
    private ArrayList<Comment> comments;

    private ArrayList<Rating> ratings = new ArrayList<>();
    private Rating rating;
    private LocalDateTime discountTime;

    //TODO: adding refrence back to restaurantID

    {
        isUnlisted = false;
        comments = new ArrayList<>();
        rating = new Rating();
    }

    public Food(String id, String name, int price, double discountPercent) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discountPercent = discountPercent;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        if (discountTime == null) return price;
        if (LocalDateTime.now().compareTo(discountTime) > 0) return price;
        return (int) Math.round(price * discountPercent / 100);
    }

    public boolean isDiscounted() {
        if (discountTime == null) return false;
        return LocalDateTime.now().compareTo(discountTime) <= 0;
    }

    public String getTextPrice() {
        if (discountTime == null) return String.valueOf(price);
        if (LocalDateTime.now().compareTo(discountTime) > 0) return String.valueOf(price);
        return "\u001B[9m" + String.valueOf(price) + "$" + "\u001B[0m" + "  " + String.valueOf((int) Math.round(price * discountPercent / 100));
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public boolean isUnlisted() {
        return isUnlisted;
    }

    public void setUnlisted(boolean unlisted) {
        isUnlisted = unlisted;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }


    public Rating getRating() {
        return rating;
    }

    public ArrayList<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(ArrayList<Rating> ratings) {
        this.ratings = ratings;
    }


    public String getResturant() {
        return restaurantID;
    }

    public void setRestaurantID(Restaurant restaurant) {
        this.restaurantID = restaurant.getId();
    }
    public void setRestaurantID(String restaurantID) {
        this.restaurantID = restaurantID;
    }

    public LocalDateTime getDiscountTime() {
        return discountTime;
    }

    public void setDiscountTime(LocalDateTime discountTime) {
        this.discountTime = discountTime;
    }
}
