package model.resturant;

import model.social.Comment;
import model.social.Rating;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.util.ArrayList;

public class Food {
    private final String id;
    private String name;
    private int price;
    private double discountPercent;
    private boolean isUnlisted;
    private Resturant resturant;
    private ArrayList<Comment> comments;

    private ArrayList<Rating> ratings = new ArrayList<>();
    private Rating rating;

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
        return price;
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


    public Resturant getResturant() {
        return resturant;
    }

    public void setResturant(Resturant resturant) {
        this.resturant = resturant;
    }

}
