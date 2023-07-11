package model;

import model.accounts.Manager;
import model.enums.ResturantFoodType;
import model.resturant.*;
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

    public static JSONObject saveRestaurant(Restaurant resturant) {
        if (resturant == null) return null;

        JSONObject object = new JSONObject();
        object.put("name", resturant.getName());
        object.put("location", resturant.getLocation());
        object.put("id", resturant.getId());
        object.put("menu", saveFoodMenu(resturant.getMenu()));
        JSONArray array = new JSONArray();
        for (ResturantFoodType foodType : resturant.getFoodTypes()) {
            array.add(foodType.toString());
        }
        object.put("foodTypes", array);
        object.put("selectedFood", saveFood(resturant.getSelectedFood()));
        JSONArray comments = new JSONArray();
        for (Comment comment : resturant.getComments())
            comments.add(saveComment(comment));
        object.put("comments", comments);
        JSONArray ratings = new JSONArray();
        for (Rating rating : resturant.getRatings())
            ratings.add(saveRating(rating));
        object.put("ratings", ratings);
        return object;
    }

    public static JSONObject saveManager(Manager manager) {
        JSONObject object = new JSONObject();
        object.put("userName", manager.getUserName());
        object.put("hashedPassWord", manager.getHashedPassWord());
        object.put("firstName", manager.getFirstName());
        object.put("lastName", manager.getLastName());
        JSONArray array = new JSONArray();
        for (String restaurantID : manager.getRestaurantsID()) {
            array.add(restaurantID);
        }
        String id = null;
        if (manager.getCurrentRestaurant() != null) id = manager.getCurrentRestaurantID();
        object.put("currentRestaurantID", id);
        object.put("restaurants", array);
        return object;
    }

    public static JSONObject saveOrder(Order order) {
        if (order == null) return null;

        JSONObject object = new JSONObject();
        object.put("name", order.getName());
        object.put("id", order.getId());
        JSONArray array = new JSONArray();
        for (Food food : order) {
            array.add(saveFood(food));
        }
        object.put("orderList", array);
        object.put("time", saveLocalDateTime(order.time));
        object.put("restaurantID", order.getRestaurantID());
        return object;
    }

    public static JSONObject saveCart(Cart cart) {
        JSONObject object = new JSONObject();
        object.put("restaurantID", cart.getRestaurantID());
        object.put("order", saveOrder(cart.getOrder()));
        return object;
    }

}
