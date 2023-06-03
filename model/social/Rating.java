package model.social;

public class Rating {
    private double rating;
    private int count;

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

    public int getCount() {
        return count;
    }
}
