package view.enums.managermenu;

import controllers.ManagerMenuController;
import model.Memory;
import model.accounts.Manager;
import model.enums.ResturantFoodType;
import model.resturant.Resturant;
import view.others.Colors;

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
        for (Resturant resturant : manager.getResturants()) {
            System.out.printf("id: %s    name: %s\n", resturant.getId(), resturant.getName());
        }
        return null;
    }
}
