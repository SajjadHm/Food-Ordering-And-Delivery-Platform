package view.enums.managermenu;

import controllers.ManagerMenuController;
import model.Memory;
import model.accounts.Manager;
import model.enums.ResturantFoodType;
import model.resturant.Resturant;
import view.others.Colors;

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
            else if ((matcher = ManagerMenuCommands.getMatcher(input, ManagerMenuCommands.SHOW)) != null)
                message = checkShow();
            else if ((matcher = ManagerMenuCommands.getMatcher(input, ManagerMenuCommands.SELECT_RESTAURANT)) != null)
                message = checkSelect();
            else if ((matcher = ManagerMenuCommands.getMatcher(input, ManagerMenuCommands.EDIT_FOODTYPES)) != null)
                message = checkEditFoodType(scanner);
            else if ((matcher = ManagerMenuCommands.getMatcher(input, ManagerMenuCommands.LOGOUT)) != null) {
                message = checkLogOut();
                isRunning = false;
            }

            printer(message);
        }
        return null;
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
        if (Memory.getCurrentResturant() != null) System.out.println("Current Restaurant == " + Memory.getCurrentResturant().getId());
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
        ManagerMenuMessages message = ManagerMenuController.editFoodType(foodType);
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
}
