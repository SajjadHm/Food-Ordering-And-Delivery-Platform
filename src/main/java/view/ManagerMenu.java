package view;

import controllers.ManagerMenuController;
import model.Memory;
import model.accounts.Manager;
import model.enums.ResturantFoodType;
import model.resturant.Food;
import model.resturant.FoodMenu;
import model.resturant.Resturant;
import model.social.Comment;
import view.enums.managermenu.ManagerMenuCommands;
import view.enums.managermenu.ManagerMenuMessages;
import view.enums.managermenu.ManagerMenuResults;
import view.others.Colors;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;

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
        HashMap<String, Resturant> resturants = manager.getResturants();
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
        Resturant resturant = ((Manager) Memory.getCurrentAccount()).getCurrentRestaurant();
        if (resturant == null) return ManagerMenuMessages.INVALID_COMMAND;
        FoodMenu menu = resturant.getMenu();
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
        Resturant resturant = ((Manager) Memory.getCurrentAccount()).getCurrentRestaurant();
        Food food = resturant.getSelectedFood();
        printer(food.getRating().toString());
        return null;
    }

    public static ManagerMenuMessages checkDisplayComments() {
        ManagerMenuMessages result = ManagerMenuController.checkDisplayComments();
        if (result != ManagerMenuMessages.DISPLAY_COMMENTS && result != ManagerMenuMessages.NO_FOOD_SELECTED) return result;
        Resturant resturant = ((Manager) Memory.getCurrentAccount()).getCurrentRestaurant();
        if (result == ManagerMenuMessages.NO_FOOD_SELECTED) {
            ArrayList<Comment> restaurantComments = resturant.getComments();
            if (restaurantComments.size() == 0) {
                printer("There is no comments for this restaurant.");
                return null;
            }
            for (Comment comment : restaurantComments)
                printer(comment.toString() + "\n");
            return null;
        }
        Food food = resturant.getSelectedFood();
        ArrayList<Comment> comments = food.getComments();
        if (comments.size() == 0) {
            printer("There is no comments for this food.");
            return null;
        }
        for (Comment comment : comments)
            printer(comment.toString() + "\n");
        return null;
    }
}
