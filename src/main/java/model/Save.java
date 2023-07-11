package model;

import model.resturant.Food;
import model.social.Rating;
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

    public static JSONObject saveFood(Food food) {
        JSONObject object = new JSONObject();
        object.put("id", food.getId());
        object.put("name", food.getName());
        object.put("price", food.getPrice());
        object.put("discountPercent", food.getDiscountPercent());
        object.put("isUnlisted", food.isUnlisted());
        object.put("restaurantID", food.getResturant().getId());
        object.put("rating", saveRating(food.getRating()));
//        object.put("", food.);
//        object.put("", food.);
//        object.put("", food.);
        return object;
    }

}
