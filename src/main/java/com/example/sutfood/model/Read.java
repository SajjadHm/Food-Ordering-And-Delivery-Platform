package com.example.sutfood.model;

import com.example.sutfood.Main;
import com.example.sutfood.model.accounts.Manager;
import com.example.sutfood.model.accounts.User;
import com.example.sutfood.model.enums.ResturantFoodType;
import com.example.sutfood.model.resturant.Food;
import com.example.sutfood.model.resturant.FoodMenu;
import com.example.sutfood.model.resturant.Order;
import com.example.sutfood.model.resturant.Restaurant;
import com.example.sutfood.model.social.Comment;
import com.example.sutfood.model.social.Rating;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.example.sutfood.view.ManagerMenu;

import javax.naming.InitialContext;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Read {
    public static LocalDateTime readDateTime(JSONObject object) {
        LocalDateTime localDateTime = ManagerMenu.datetimeParser((String) object.get("string"));
        return localDateTime;
    }

    public static Rating readRating(JSONObject object) {
        Rating rating = new Rating(
                (Double) object.get("rating"),
                Math.toIntExact((Long) object.get("count")),
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
                Math.toIntExact((Long) object.get("rating")),
                (Boolean) object.get("isModified")
        );
        ArrayList<Comment> replies = comment.getReplies();
        for (Object reply : (JSONArray) object.get("replies"))
            replies.add(readComment((JSONObject) reply));
        return comment;
    }

    public static Food readFood(JSONObject object) {
        if (object.get("price") == null) return null;
        Food food = new Food(
                (String) object.get("id"),
                (String) object.get("name"),
                Math.toIntExact((Long) object.get("price")),
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
        for (Object object1 : (JSONArray) object.get("foodMenu"))
            foodMenu.add(readFood((JSONObject) object1));
        return foodMenu;
    }

    private static Restaurant readRestaurant(JSONObject object) {
        if (object == null) return null;
        ArrayList<ResturantFoodType> foodTypes = new ArrayList<>();
        if (((JSONArray) object.get("foodTypes")).size() > 0) {
            for (Object object1 : (JSONArray) object.get("foodTypes")) {
                foodTypes.add(ResturantFoodType.valueOf(object1.toString()));
            }
        }
        Restaurant resturant = new Restaurant (
                (String) object.get("name"),
                foodTypes,
                (String) object.get("location"),
                (String) object.get("id"),
                readFoodMenu((JSONObject) object.get("menu"))
        );
        Food selectedFood = readFood((JSONObject) object.get("selectedFood"));
        ArrayList<Comment> comments = resturant.getComments();
        for (Object comment : (JSONArray) object.get("comments"))
            comments.add(readComment((JSONObject) comment));
        ArrayList<Rating> ratings = resturant.getRatings();
        for (Object rating : (JSONArray) object.get("ratings"))
            ratings.add(readRating((JSONObject) rating));
        return resturant;
    }

    private static Manager readManager(JSONObject object) {
        ArrayList<String> restaurantsID = new ArrayList<>();
        JSONArray restaurantsArr = (JSONArray) object.get("restaurants");
        if (restaurantsArr != null)
            for (Object restaurantID : restaurantsArr)
                restaurantsID.add((String) restaurantID);
        Manager manager = new Manager(
                (String) object.get("userName"),
                (String) object.get("hashedPassWord"),
                (String) object.get("firstName"),
                (String) object.get("lastName"),
                true,
                restaurantsID,
                (String) object.get("currentRestaurantID")
        );

        return manager;
    }

    private static Order readOrder(JSONObject object) {
        Order order = new Order(
                (String) object.get("name"),
                (String) object.get("id"),
                readDateTime((JSONObject) object.get("time"))
        );
        JSONArray orderListArr = (JSONArray) object.get("orderList");
        if (orderListArr != null)
            for (Object object1 : orderListArr)
                order.add(readFood((JSONObject) object1));
        order.setRestaurantID((String) object.get("restaurantID"));
        return order;
    }

    private static Cart readCart(JSONObject object) {
        Cart cart = new Cart(
                (Restaurant) object.get("restaurantID"),
                readOrder((JSONObject) object.get("order"))
        );
        return cart;
    }

    public static User readUser(JSONObject object) {
        HashMap<Comment, Restaurant> userComments = new HashMap<>();
        JSONObject userCommentsObject = (JSONObject) object.get("userComments");
        if (userCommentsObject != null) {
            JSONParser parser = new JSONParser();
            for (Object userComment : userCommentsObject.keySet()) {
                try {
                    JSONObject parsed = (JSONObject) parser.parse((String) userComment);
                    userComments.put(readComment(parsed), readRestaurant((JSONObject) userCommentsObject.get(parsed)));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        HashMap<Rating, Restaurant> userRatings = new HashMap<>();
        JSONObject userRatingsObject = (JSONObject) object.get("userRatings");
        if (userRatingsObject != null)
            for (Object userRating : userRatingsObject.keySet())
                userRatings.put(readRating((JSONObject) userRating), readRestaurant((JSONObject) userRatingsObject.get(userRating)));

        HashMap<Comment,Food> userCommentsFood = new HashMap<>();
        JSONObject userCommentsFoodObject = (JSONObject) object.get("userCommentsFood");
        if (userCommentsFoodObject != null)
            for (Object userComment : userCommentsFoodObject.keySet())
                userCommentsFood.put(readComment((JSONObject) userComment), readFood((JSONObject) userCommentsFoodObject.get(userComment)));

        HashMap<Rating,Food> userRatingsFood = new HashMap<>();
        JSONObject userRatingsFoodObject = (JSONObject) object.get("userRatingsFood");
        if (userRatingsFoodObject != null)
            for (Object userRating : userRatingsFoodObject.keySet())
                userRatingsFood.put(readRating((JSONObject) userRating), readFood((JSONObject) userRatingsFoodObject.get(userRating)));

        ArrayList<Order> ordersHistory = new ArrayList<>();
        JSONArray ordersHistoryArr = (JSONArray) object.get("ordersHistory");
        if (ordersHistoryArr != null)
            for (Object order : ordersHistoryArr)
                ordersHistory.add(readOrder((JSONObject) order));

        ArrayList<Cart> userCart = new ArrayList<>();
        JSONArray userCartArr = (JSONArray) object.get("userCart");
        if (userCartArr != null)
            for (Object cart : userCartArr)
                userCart.add(readCart((JSONObject) cart));

        User user = new User (
                (String) object.get("userName"),
                (String) object.get("hashedPassWord"),
                (String) object.get("firstName"),
                (String) object.get("lastName"),
                true
        );
        user.getUserComments().putAll(userComments);
        user.getUserRatings().putAll(userRatings);
        user.getUserCommentsFood().putAll(userCommentsFood);
        user.getUserRatingsFood().putAll(userRatingsFood);
        user.getOrdersHistory().addAll(ordersHistory);
        user.getUserCart().addAll(userCart);
        return user;
    }

    public static void readMemory(JSONObject object) {
        Memory.setFoodIdCount(Math.toIntExact((Long) object.get("foodIDCount")));
        Memory.setCurrentUser(readUser((JSONObject) object.get("currentUser")));
        Memory.setCurrentAccount(readManager((JSONObject) object.get("currentAccount")));
        ArrayList<Manager> managers = new ArrayList<>();
        for (Object manager : (JSONArray) object.get("managers"))
            managers.add(readManager((JSONObject) manager));
        Memory.getAdmins().addAll(managers);
        ArrayList<User> users = new ArrayList<>();
        for (Object user : (JSONArray) object.get("users"))
            users.add(readUser((JSONObject) user));
        Memory.getUsers().addAll(users);
        HashMap<String, Restaurant> restaurantsList = new HashMap<>();
        JSONObject restaurantsListObject = (JSONObject) object.get("restaurantsList");
        for (Object restaurantID : restaurantsListObject.keySet())
            restaurantsList.put((String) restaurantID, readRestaurant((JSONObject) restaurantsListObject.get(restaurantID)));
        Memory.getRestaurantsList().putAll(restaurantsList);
    }

    public static void loadData() {
        JSONObject jsonObject = null;
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("D:/OOP/Food-Ordering-And-Delivery-Platform/src/main/resources/JSON/memory.json"));
            jsonObject = (JSONObject) obj;
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            // e.printStackTrace();
            // throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Read.readMemory(jsonObject);
        System.out.println("loaded!");
    }

}
