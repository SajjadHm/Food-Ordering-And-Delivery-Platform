package model.social;

import java.util.ArrayList;

public class Rating {
    private double rating;
    private int count;

    private String id;


    public Rating(double rating ,String id)
    {
        this.rating = rating;
        this.id = id;
    }

    public Rating() {
        this.count = 0;
        this.rating = 0;
    }

    public Rating(double rating, int count) {
        this.rating = rating;
        this.count = count;
    }

    public void addRating(int rate) {
        rating = ((rating * count) + rate) / (count + 1);
        count++;
    }

    public void removeRating(int rate) {
        if (count <= 1) {
            rating = 0;
            count = 0;
            return;
        }
        rating = ((rating * count) - rate) / (count - 1);
        count--;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating)
    {
        this.rating = rating;
    }

    public int getCount() {
        return count;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }



    public static double avgRatings(ArrayList<Rating> ratings)
    {
        int num = ratings.size();
        if(num==0)
            return 0;
        double avg  = 0;
        for(Rating rate:ratings)
        {
           avg += rate.getRating();
        }

        return avg/num;
    }

    public String toString() {
        if (count == 0) return "There's no votes for this food.";
        return rating + " (" + count + " votes)";
    }
}
