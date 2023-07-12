package com.example.sutfood.view;

import com.example.sutfood.controllers.ManagerMenuController;
import com.example.sutfood.model.Memory;
import com.example.sutfood.model.accounts.Manager;
import com.example.sutfood.model.enums.ResturantFoodType;
import com.example.sutfood.model.resturant.Food;
import com.example.sutfood.model.resturant.Order;
import com.example.sutfood.model.resturant.Restaurant;
import com.example.sutfood.view.enums.managermenu.ManagerMenuResults;
import com.example.sutfood.model.resturant.FoodMenu;
import com.example.sutfood.model.social.Comment;
import com.example.sutfood.view.enums.managermenu.ManagerMenuCommands;
import com.example.sutfood.view.enums.managermenu.ManagerMenuMessages;
import com.example.sutfood.view.others.Colors;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManagerMenu {
    private static boolean isRunning;
    private static Matcher matcher;
    private static String input;
    private static ManagerMenuMessages message;

    static {
        isRunning = false;
    }

    public static void printer(ManagerMenuMessages message) {
        if (message != null) System.out.println(message.getMessage() + Colors.RESET);
    }

    public static void printer(String message) {
        if (message != null) System.out.println(message + Colors.RESET);
    }

    public static ManagerMenuResults run(Scanner scanner) {
        isRunning = true;
        while (isRunning) {
            message = ManagerMenuMessages.INVALID_COMMAND;
            input = scanner.nextLine().trim();
            if ((matcher = ManagerMenuCommands.getMatcher(input, ManagerMenuCommands.END)) != null)
                return ManagerMenuResults.END;
            else if ((matcher = ManagerMenuCommands.getMatcher(input, ManagerMenuCommands.ADD_RESTAURANT)) != null)
                message = checkAddRestaurant();
            else if ((matcher = ManagerMenuCommands.getMatcher(input, ManagerMenuCommands.REMOVE_RESTAURANT)) != null)
                message = checkRemoveRestaurant();
            else if ((matcher = ManagerMenuCommands.getMatcher(input, ManagerMenuCommands.SHOW_RESTAURANTS)) != null)
                message = checkShow();
            else if ((matcher = ManagerMenuCommands.getMatcher(input, ManagerMenuCommands.SELECT_MENU)) != null)
                message = checkSelectMenu();
            else if ((matcher = ManagerMenuCommands.getMatcher(input, ManagerMenuCommands.SELECT_RESTAURANT)) != null)
                message = checkSelect();
            else if ((matcher = ManagerMenuCommands.getMatcher(input, ManagerMenuCommands.EDIT_FOODTYPES)) != null)
                message = checkEditFoodType(scanner);
            else if ((matcher = ManagerMenuCommands.getMatcher(input, ManagerMenuCommands.ADD_FOOD)) != null)
                message = checkAddFood();
            else if ((matcher = ManagerMenuCommands.getMatcher(input, ManagerMenuCommands.DELETE_FOOD)) != null)
                message = checkRemoveFood();
            else if ((matcher = ManagerMenuCommands.getMatcher(input, ManagerMenuCommands.DEACTIVE_FOOD)) != null)
                message = checkDeactiveFood();
            else if ((matcher = ManagerMenuCommands.getMatcher(input, ManagerMenuCommands.ACTIVE_FOOD)) != null)
                message = checkActiveFood();
            else if ((matcher = ManagerMenuCommands.getMatcher(input, ManagerMenuCommands.DISCOUNT_FOOD)) != null)
                message = checkDiscountFood();
            else if ((matcher = ManagerMenuCommands.getMatcher(input, ManagerMenuCommands.SELECT_FOOD)) != null)
                message = checkSelectFood();
            else if ((matcher = ManagerMenuCommands.getMatcher(input, ManagerMenuCommands.DESELECT_FOOD)) != null)
                message = checkDeselectFood();
            else if ((matcher = ManagerMenuCommands.getMatcher(input, ManagerMenuCommands.DISPLAY_RATINGS)) != null)
                message = checkDisplayRatings();
            else if ((matcher = ManagerMenuCommands.getMatcher(input, ManagerMenuCommands.DISPLAY_COMMENTS)) != null)
                message = checkDisplayComments();
            else if ((matcher = ManagerMenuCommands.getMatcher(input, ManagerMenuCommands.ADD_NEW_RESPONSE)) != null)
                message = checkAddNewResponse();
            else if ((matcher = ManagerMenuCommands.getMatcher(input, ManagerMenuCommands.DISPLAY_OPEN_ORDERS)) != null)
                message = checkDisplayOpenOrders();
            else if ((matcher = ManagerMenuCommands.getMatcher(input, ManagerMenuCommands.LOGOUT)) != null) {
                message = checkLogOut();
                isRunning = false;
            }
            printer(message);
        }
        return null;
    }

    private static LocalDateTime checkTimeStamp() {
        int year = Integer.parseInt(matcher.group("year"));
        int month = Integer.parseInt(matcher.group("month"));
        int day = Integer.parseInt(matcher.group("day"));
        int hour = Integer.parseInt(matcher.group("hour"));
        int minute = Integer.parseInt(matcher.group("minute"));
        int second = Integer.parseInt(matcher.group("second"));
        LocalDateTime timestamp;
        try {
            timestamp = LocalDateTime.of(year, month, day, hour, minute, second);
            return timestamp;
        } catch (Exception e) {
            return null;
        }
    }

    public static LocalDateTime datetimeParser(String time) {
        if (time == null) return null;
        Pattern pattern = Pattern.compile("(?<year>\\d{4})\\/(?<month>\\d{2})\\/(?<day>\\d{2})\\s{2}(?<hour>\\d{2}):(?<minute>\\d{2}):(?<second>\\d{2})");
        Matcher m = pattern.matcher(time);
        if (!m.matches()) return null;
        int year = Integer.parseInt(m.group("year"));
        int month = Integer.parseInt(m.group("month"));
        int day = Integer.parseInt(m.group("day"));
        int hour = Integer.parseInt(m.group("hour"));
        int minute = Integer.parseInt(m.group("minute"));
        int second = Integer.parseInt(m.group("second"));
        LocalDateTime timestamp;
        try {
            timestamp = LocalDateTime.of(year, month, day, hour, minute, second);
            return timestamp;
        } catch (Exception e) {
            return null;
        }
    }

    public static ManagerMenuMessages checkAddRestaurant() {
        String name = matcher.group("name");
        ResturantFoodType[] foodType = ResturantFoodType.getType(matcher.group("foodType").split("\\s+"));
        String location = matcher.group("location");
        return ManagerMenuController.checkAddRestaurant(name, location, foodType);
    }

    public static ManagerMenuMessages checkLogOut() {
        return ManagerMenuController.checkLogout();
    }

    public static ManagerMenuMessages checkRemoveRestaurant() {
        String id = matcher.group("id");
        return ManagerMenuController.checkRemoveRestaurant(id);
    }

    public static ManagerMenuMessages checkShow() {
        Manager manager = (Manager) Memory.getCurrentAccount();
        if (manager.getResturants().size() == 0) return ManagerMenuMessages.NO_RESTAURANTS;
        System.out.println("Number of restaurants: " + manager.getResturants().size());
        if (manager.getCurrentRestaurant() != null) System.out.println("Current Restaurant == " + manager.getCurrentRestaurant().getId());
        HashMap<String, Restaurant> resturants = manager.getResturants();
        String[] keys = resturants.keySet().toArray(new String[0]);
        Arrays.sort(keys);
        for (String key : keys) {
            System.out.printf("id: %s    name: %s\n", resturants.get(key).getId(), resturants.get(key).getName());
            System.out.println("foodtype(s):");
            for (ResturantFoodType resturantFoodType : resturants.get(key).getFoodTypes()) {
                System.out.println(resturantFoodType.getName());
            }
        }
        return null;
    }

    public static ManagerMenuMessages checkSelect() {
        String id = matcher.group("id");
        return ManagerMenuController.checkSelect(id);
    }

    public static ManagerMenuMessages checkEditFoodType(Scanner scanner) {
        ResturantFoodType[] foodType = ResturantFoodType.getType(matcher.group("foodType").split("\\s+"));
        ManagerMenuMessages message = ManagerMenuController.checkEditFoodType(foodType);
        printer(message);
        if (message != ManagerMenuMessages.OK) return null;
        String input;
        while (true) {
            input = scanner.nextLine().trim();
            if ((matcher = ManagerMenuCommands.getMatcher(input, ManagerMenuCommands.JA)) != null)
                return ManagerMenuController.editFoodType(foodType);
            else if ((matcher = ManagerMenuCommands.getMatcher(input, ManagerMenuCommands.NEIN)) != null)
                return ManagerMenuMessages.CANCELLED;
            else
                printer(ManagerMenuMessages.INVALID_COMMAND);
        }
    }

    public static ManagerMenuMessages checkSelectMenu() {
        Restaurant restaurant = ((Manager) Memory.getCurrentAccount()).getCurrentRestaurant();
        if (restaurant == null) return ManagerMenuMessages.INVALID_COMMAND;
        FoodMenu menu = restaurant.getMenu();
        if (menu.size() == 0) return ManagerMenuMessages.EMPTY_MENU;
        for (Food food : menu) {
            System.out.printf("%s. %s  %s$    isUnlisted: %b\n", food.getId(), food.getName(), food.getTextPrice(), food.isUnlisted());
        }
        return null;
    }

    public static ManagerMenuMessages checkAddFood() {
        String name = matcher.group("foodName");
        int price = Integer.parseInt(matcher.group("price"));
        return ManagerMenuController.checkAddFood(name, price);
    }

    public static ManagerMenuMessages checkRemoveFood() {
        String id = matcher.group("foodID");
        return ManagerMenuController.checkRemoveFood(id);
    }

    public static ManagerMenuMessages checkDeactiveFood() {
        String foodID = matcher.group("foodID");
        return ManagerMenuController.checkDeactiveFood(foodID);
    }

    public static ManagerMenuMessages checkActiveFood() {
        String foodID = matcher.group("foodID");
        return ManagerMenuController.checkActiveFood(foodID);
    }

    public static ManagerMenuMessages checkDiscountFood() {
        String foodID = matcher.group("foodID");
        double discountPercent = Double.parseDouble(matcher.group("percent"));
        LocalDateTime timeStamp = checkTimeStamp();
        return ManagerMenuController.checkDiscountFood(foodID, discountPercent, timeStamp);
    }

    public static ManagerMenuMessages checkSelectFood() {
        String foodID = matcher.group("foodID");
        return ManagerMenuController.checkSelectFood(foodID);
    }

    public static ManagerMenuMessages checkDeselectFood() {
        return ManagerMenuController.checkDeselectFood();
    }

    public static ManagerMenuMessages checkDisplayRatings() {
        ManagerMenuMessages result = ManagerMenuController.checkDisplayRatings();
        if (result != ManagerMenuMessages.DISPLAY_RATINGS) return result;
        Restaurant restaurant = ((Manager) Memory.getCurrentAccount()).getCurrentRestaurant();
        Food food = restaurant.getSelectedFood();
        printer(food.getRating().toString());
        return null;
    }

    public static ManagerMenuMessages checkDisplayComments() {
        ManagerMenuMessages result = ManagerMenuController.checkDisplayComments();
        if (result != ManagerMenuMessages.DISPLAY_COMMENTS && result != ManagerMenuMessages.NO_FOOD_SELECTED) return result;
        Restaurant restaurant = ((Manager) Memory.getCurrentAccount()).getCurrentRestaurant();
        if (result == ManagerMenuMessages.NO_FOOD_SELECTED) {
            ArrayList<Comment> restaurantComments = restaurant.getComments();
            if (restaurantComments.size() == 0) {
                printer("There is no comments for this restaurant.");
                return null;
            }
            for (Comment comment : restaurantComments)
                printer(comment.toString() + "\n");
            return null;
        }
        Food food = restaurant.getSelectedFood();
        ArrayList<Comment> comments = food.getComments();
        if (comments.size() == 0) {
            printer("There is no comments for this food.");
            return null;
        }
        for (Comment comment : comments)
            printer(comment.toString() + "\n");
        return null;
    }

    public static ManagerMenuMessages checkAddNewResponse() {
        String commentID = matcher.group("commentID");
        String message = matcher.group("message");
        return ManagerMenuController.checkAddResponse(commentID, message);
    }

    public static ManagerMenuMessages checkDisplayOpenOrders() {
        ManagerMenuMessages result = ManagerMenuController.checkDisplayOrders();
        if (result != ManagerMenuMessages.DISPLAY_CURRENT_ORDERS) return result;
        Restaurant restaurant = ((Manager) Memory.getCurrentAccount()).getCurrentRestaurant();
        ArrayList<Order> currentOrders = restaurant.getCurrentOrders();
        for (Order order : currentOrders)
            System.out.println(order.toString());
        return null;
    }
}
