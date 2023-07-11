package model;

import model.resturant.Food;
import model.resturant.FoodList;
import model.resturant.FoodMenu;
import model.social.Comment;
import model.social.Rating;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.time.LocalDateTime;
import java.util.regex.Matcher;

public class Save {
    public static JSONObject saveLocalDateTime(LocalDateTime time) {
        JSONObject object = new JSONObject();
        object.put("string", time.format(Memory.dateTimeFormatter));
        return object;
    }

    public static JSONObject saveRating(Rating rating) {
        JSONObject object = new JSONObject();
        object.put("rating", rating.getRating());
        object.put("count", rating.getCount());
        object.put("id", rating.getId());
        return object;
    }

    public static JSONObject saveComment(Comment comment) {
        JSONObject object = new JSONObject();
        object.put("message", comment.getMessage());
        object.put("id", comment.getId());
        object.put("rating", comment.getRating());
        object.put("authorID", comment.getAuthorID());
        object.put("timeCreated", saveLocalDateTime(comment.getTimeCreated()));
        object.put("isModified", comment.isModified());
        JSONArray replies = new JSONArray();
        for (Comment reply : comment.getReplies())
            replies.add(saveComment(comment));
        object.put("replies", replies);
        return object;
    }

    public static JSONObject saveFood(Food food) {
        JSONObject object = new JSONObject();
        object.put("id", food.getId());
        object.put("name", food.getName());
        object.put("price", food.getPrice());
        object.put("discountPercent", food.getDiscountPercent());
        object.put("isUnlisted", food.isUnlisted());
        object.put("restaurantID", food.getResturant());
        object.put("rating", saveRating(food.getRating()));
        object.put("discountTime", saveLocalDateTime(food.getDiscountTime()));
        JSONArray ratings = new JSONArray();
        for (Rating rating : food.getRatings())
            ratings.add(saveRating(rating));
        object.put("ratings", ratings);
        JSONArray comments = new JSONArray();
        for (Comment comment : food.getComments())
            comments.add(saveComment(comment));
        object.put("comments", comments);
        return object;
    }

    public static JSONObject saveFoodMenu(FoodMenu foodMenu) {
        if (foodMenu == null) return null;

        JSONObject object = new JSONObject();
        object.put("name", foodMenu.getName());
        object.put("id", foodMenu.getId());
        JSONArray array = new JSONArray();
        for (Food food : foodMenu) {
            array.add(saveFood(food));
        }
        object.put("foodMenu", array);
        return object;
    }

}
