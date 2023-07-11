package model;

import model.resturant.Food;
import model.resturant.FoodMenu;
import model.resturant.Restaurant;
import model.social.Comment;
import model.social.Rating;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import view.ManagerMenu;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Observable;

public class Read {
    public static LocalDateTime readDateTime(JSONObject object) {
        LocalDateTime localDateTime = ManagerMenu.datetimeParser((String) object.get("string"));
        return localDateTime;
    }

    public static Rating readRating(JSONObject object) {
        Rating rating = new Rating(
                (Double) object.get("rating"),
                (Integer) object.get("count"),
                (String) object.get("id")
        );
        return rating;
    }

    public static Comment readComment(JSONObject object) {
        Comment comment = new Comment(
                (String) object.get("message"),
                (String) object.get("id"),
                (String) object.get("authorID"),
                readDateTime((JSONObject) object.get("timeCreated")),
                (Integer) object.get("rating"),
                (Boolean) object.get("isModified")
        );
        ArrayList<Comment> replies = comment.getReplies();
        for (Object reply : (JSONArray) object.get("replies"))
            replies.add(readComment((JSONObject) reply));
        return comment;
    }

    public static Food readFood(JSONObject object) {
        Food food = new Food(
                (String) object.get("id"),
                (String) object.get("name"),
                (Integer) object.get("price"),
                (Double) object.get("discountPercent")
        );
        food.setUnlisted((Boolean) object.get("isUnlisted"));
        food.setRestaurantID((String) object.get("restaurantID")); //CHANGE IT
        food.setDiscountTime(readDateTime((JSONObject) object.get("discountTime")));
        food.setRating(readRating((JSONObject) object.get("rating")));
        ArrayList<Rating> ratings = food.getRatings();
        for (Object rating : (JSONArray) object.get("ratings"))
            ratings.add(readRating((JSONObject) rating));
        ArrayList<Comment> comments = food.getComments();
        for (Object comment : (JSONArray) object.get("comments"))
            comments.add(readComment((JSONObject) comment));
        return food;
    }

    private static FoodMenu readFoodMenu(JSONObject object) {
        FoodMenu foodMenu = new FoodMenu(
                (String) object.get("name"),
                (String) object.get("id")
        );
        if (((JSONArray) object.get("foodMenu")).size() > 0) {
            for (Object object1 : (JSONArray) object.get("foodMenu")) {
                foodMenu.add(readFood((JSONObject) object1));
            }
        }
        return foodMenu;
    }

}
