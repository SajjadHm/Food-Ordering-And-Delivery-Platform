package model;

import com.sun.org.apache.xpath.internal.operations.Or;
import model.accounts.Manager;
import model.accounts.User;
import model.enums.ResturantFoodType;
import model.resturant.Food;
import model.resturant.FoodMenu;
import model.resturant.Order;
import model.resturant.Restaurant;
import model.social.Comment;
import model.social.Rating;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import view.Main;
import view.ManagerMenu;

import javax.jws.Oneway;
import javax.management.ObjectName;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
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
        for (Object object1 : (JSONArray) object.get("foodMenu"))
            foodMenu.add(readFood((JSONObject) object1));
        return foodMenu;
    }

    private static Restaurant readRestaurant(JSONObject object) {
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
        for (Object restaurantID : (JSONArray) object.get("restaurants"))
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
        for (Object object1 : (JSONArray) object.get("orderList"))
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
        for (Object userComment : userCommentsObject.keySet())
            userComments.put(readComment((JSONObject) userComment), readRestaurant((JSONObject) userCommentsObject.get(userComment)));
        HashMap<Rating, Restaurant> userRatings = new HashMap<>();
        JSONObject userRatingsObject = (JSONObject) object.get("userRatings");
        for (Object userRating : userRatingsObject.keySet())
            userRatings.put(readRating((JSONObject) userRating), readRestaurant((JSONObject) userRatingsObject.get(userRating)));
        HashMap<Comment,Food> userCommentsFood = new HashMap<>();
        JSONObject userCommentsFoodObject = (JSONObject) object.get("userCommentsFood");
        for (Object userComment : userCommentsFoodObject.keySet())
            userCommentsFood.put(readComment((JSONObject) userComment), readFood((JSONObject) userCommentsFoodObject.get(userComment)));
        HashMap<Rating,Food> userRatingsFood = new HashMap<>();
        JSONObject userRatingsFoodObject = (JSONObject) object.get("userRatingsFood");
        for (Object userRating : userRatingsFoodObject.keySet())
            userRatingsFood.put(readRating((JSONObject) userRating), readFood((JSONObject) userRatingsFoodObject.get(userRating)));
        ArrayList<Order> ordersHistory = new ArrayList<>();
        for (Object order : (JSONArray) object.get("ordersHistory"))
            ordersHistory.add(readOrder((JSONObject) order));
        ArrayList<Cart> userCart = new ArrayList<>();
        for (Object cart : (JSONArray) object.get("userCart"))
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
        Memory.setFoodIdCount((Integer) object.get("foodIDCount"));
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
