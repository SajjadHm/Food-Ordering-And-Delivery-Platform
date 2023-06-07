package view;

import controllers.UserMenuController;
import model.Memory;
import view.enums.loginmenu.LoginMenuCommands;
import view.enums.loginmenu.LoginMenuMessages;
import view.enums.loginmenu.LoginMenuResults;
import view.enums.usermenu.UserMenuCommands;
import view.enums.usermenu.UserMenuMessages;
import view.enums.usermenu.UserMenuResults;

import java.util.Scanner;
import java.util.regex.Matcher;

public class UserMenu
{
    static boolean isRunning;
    static Matcher matcher;

    public static UserMenuResults run(Scanner scanner)
    {
        isRunning = true;
        String input;
        UserMenuMessages message;

        while (isRunning)
        {
            input = scanner.nextLine().trim();

            if ((matcher = UserMenuCommands.getMatcher(input, UserMenuCommands.END)) != null)
            {
                return UserMenuResults.END;
            }
            else if ((matcher = UserMenuCommands.getMatcher(input, UserMenuCommands.BACK)) != null)
            {
                return UserMenuResults.BACK;
            }
            else if ((matcher = UserMenuCommands.getMatcher(input, UserMenuCommands.SEARCH_RESTAURANT)) != null)
            {

            }
            else if ((matcher = UserMenuCommands.getMatcher(input, UserMenuCommands.SELECT_RESTAURANT)) != null)
            {

            }
            else if ((matcher = UserMenuCommands.getMatcher(input, UserMenuCommands.SEARCH_FOOD)) != null)
            {

            }
            else if ((matcher = UserMenuCommands.getMatcher(input, UserMenuCommands.SELECT_FOOD)) != null)
            {

            }
            else if ((matcher = UserMenuCommands.getMatcher(input, UserMenuCommands.DISPLAY_COMMENTS)) != null)
            {

            }
            else if ((matcher = UserMenuCommands.getMatcher(input, UserMenuCommands.ADD_NEW_COMMENT)) != null)
            {

            }
            else if ((matcher = UserMenuCommands.getMatcher(input, UserMenuCommands.EDIT_COMMENT)) != null)
            {

            }
            else if ((matcher = UserMenuCommands.getMatcher(input, UserMenuCommands.DISPLAY_RATING)) != null)
            {

            }
            else if ((matcher = UserMenuCommands.getMatcher(input, UserMenuCommands.SUBMIT_RATING)) != null)
            {

            }
            else if ((matcher = UserMenuCommands.getMatcher(input, UserMenuCommands.EDIT_RATING)) != null)
            {

            }
            else if ((matcher = UserMenuCommands.getMatcher(input, UserMenuCommands.DISPLAY_COMMENTS_FOOD)) != null)
            {

            }
            else if ((matcher = UserMenuCommands.getMatcher(input, UserMenuCommands.ADD_NEW_COMMENT_FOOD)) != null)
            {

            }
            else if ((matcher = UserMenuCommands.getMatcher(input, UserMenuCommands.EDIT_COMMENT_FOOD)) != null)
            {

            }
            else if ((matcher = UserMenuCommands.getMatcher(input, UserMenuCommands.DISPLAY_RATINGS_FOOD)) != null)
            {

            }
            else if ((matcher = UserMenuCommands.getMatcher(input, UserMenuCommands.SUBMIT_RATING_FOOD)) != null)
            {

            }
            else if ((matcher = UserMenuCommands.getMatcher(input, UserMenuCommands.EDIT_RATING_FOOD)) != null)
            {

            }
            else if ((matcher = UserMenuCommands.getMatcher(input, UserMenuCommands.ADD_FOOD_TO_CART)) != null)
            {

            }
            else if ((matcher = UserMenuCommands.getMatcher(input, UserMenuCommands.ORDER_HISTORY)) != null)
            {

            }
            else if ((matcher = UserMenuCommands.getMatcher(input, UserMenuCommands.SELECT_ORDER)) != null)
            {

            }
            else if ((matcher = UserMenuCommands.getMatcher(input, UserMenuCommands.DISPLAY_CART_STATUS)) != null)
            {

            }
            else if ((matcher = UserMenuCommands.getMatcher(input, UserMenuCommands.CONFIRM_ORDER)) != null)
            {

            }
            else if ((matcher = UserMenuCommands.getMatcher(input, UserMenuCommands.SHOW_DELIVERY_TIME)) != null)
            {

            }
            else if ((matcher = UserMenuCommands.getMatcher(input, UserMenuCommands.CHARGE_ACCOUNT)) != null)
            {
                message = UserMenuController.chargeAccountController(matcher.group("amount"));
                print(message.getMessage());
            }
            else if ((matcher = UserMenuCommands.getMatcher(input, UserMenuCommands.DISPLAY_ACCOUNT_CHARGE)) != null)
            {
                System.out.println("YOUR ACCOUNT CHARGE IS "+Memory.getCurrentUser().getBalance()+" IRT");
            }
            else
            {
                System.out.println("Invalid Command!");
            }
        }
        return null;
    }

    public static void print(String str)
    {
        System.out.println(str);
    }

}
