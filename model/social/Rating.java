package model.social;

public class Rating {
    private double rating;
    private int count;

    public Rating(double rating) {
        this.count = 1;
        this.rating = rating;
    }

    public void addRating(int rate) {
        rating = ((rating * count) + rate) / (count + 1);
        count++;
    }

    public void removeRating(int rate) {
        if (count == 1) {
            rating = 0;
            return;
        }
        rating = ((rating * count) - rate) / (count - 1);
        count--;
    }

    public double getRating() {
        return rating;
    }
}
