package view;

import com.sun.xml.internal.bind.v2.TODO;
import controllers.UserMenuController;
import model.Memory;
import model.resturant.Food;
import model.resturant.FoodMenu;
import model.resturant.Resturant;
import view.enums.loginmenu.LoginMenuCommands;
import view.enums.loginmenu.LoginMenuMessages;
import view.enums.loginmenu.LoginMenuResults;
import view.enums.managermenu.ManagerMenuMessages;
import view.enums.usermenu.UserMenuCommands;
import view.enums.usermenu.UserMenuMessages;
import view.enums.usermenu.UserMenuResults;

import java.util.ArrayList;
import java.util.HashMap;
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
            else if ((matcher = UserMenuCommands.getMatcher(input, UserMenuCommands.LOGOUT)) != null)
            {
                message = UserMenuController.logoutController();
                print(message.getMessage());
                return UserMenuResults.LOG_OUT;
            }
            else if ((matcher = UserMenuCommands.getMatcher(input, UserMenuCommands.SEARCH_RESTAURANT)) != null)
            {
                message= UserMenuController.searchRestaurantController(matcher.group("restaurantName"));
                if(message.getMessage().equals(UserMenuMessages.RESTAURANT_NOT_FOUND.getMessage()))
                    print(message.getMessage());
                else
                {
                    print(message.getMessage());
                    printSearchedRestaurant(matcher.group("restaurantName"));
                }
            }
            else if ((matcher = UserMenuCommands.getMatcher(input, UserMenuCommands.SELECT_RESTAURANT)) != null)
            {
                message = UserMenuController.selectRestaurantController(matcher.group("restaurantId"));
                selectRestaurant(message);
            }
            else if ((matcher = UserMenuCommands.getMatcher(input, UserMenuCommands.SEARCH_FOOD)) != null)
            {
                message= UserMenuController.searchFoodController(matcher.group("foodName"));
                if(message.getMessage().equals(UserMenuMessages.FOOD_NOT_FOUND.getMessage()))
                    print(message.getMessage());
                else
                {
                    print(message.getMessage());
                    printSearchedFood(matcher.group("foodName"));
                }
            }
            else if ((matcher = UserMenuCommands.getMatcher(input, UserMenuCommands.SELECT_FOOD)) != null)
            {
                message= UserMenuController.selectFoodController(matcher.group("foodId"));
                if(message.getMessage().equals(UserMenuMessages.FOOD_NOT_FOUND.getMessage()))
                    print(message.getMessage());
                else
                {
                    print(message.getMessage());
                    selectFood(matcher.group("foodId"));
                }

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
                // TODO:get it from navigation!
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

    public static void printSearchedRestaurant(String name)
    {
        ArrayList<Resturant> searchResult = Memory.getRestaurants(name);
        for(int i=0 ;i<searchResult.size();i++)
        {
            System.out.println("Name: "+searchResult.get(i).getName()+"      "+"ID: "+searchResult.get(i).getId());
        }
    }

    public static void selectRestaurant(UserMenuMessages message)
    {
        if(message == UserMenuMessages.RESTAURANT_SELECTED_SUCCESSFULLY)
        {
            print(message.getMessage());
            //printing menu
            System.out.println("");
            System.out.println("The Menu:");
            Resturant userCurrentRestaurant = Memory.getCurrentUser().getUserCurrentRestaurant();
            FoodMenu menu =userCurrentRestaurant.getMenu();
            if (menu.size() == 0)
                System.out.println("MENU IS EMPTY!");
            else
            {
                for (Food food : menu)
                {
                    System.out.print("ID: "+food.getId()+"    Name: "+food.getName()+"    Price: "+food.getPrice()+"IRT    ");
                    if(!food.isUnlisted())
                        System.out.print("Status: Available");
                    else
                        System.out.print("Status: UnAvailable");
                    if(food.getDiscountPercent()>0)
                        System.out.println("    DiscountPercent: "+food.getDiscountPercent()+"%");
                    else
                        System.out.println("");
                }
            }

        }
        else
        {
            print(UserMenuMessages.RESTAURANT_NOT_FOUND.getMessage());
        }
    }

    public static void printSearchedFood(String name)
    {
        HashMap<String,Food> searchResults = searchFood(name);
        for (HashMap.Entry<String, Food> entry : searchResults.entrySet())
        {
            String restaurantId = entry.getKey().split(" ")[0];
            Food food = entry.getValue();
            System.out.print("Restaurant Name: "+Memory.getRestaurant(restaurantId).getName());
            System.out.print("    Restaurant Id: "+restaurantId);
            System.out.print("    Food Name: "+food.getName());
            System.out.print("    Food Id: "+food.getId());
            System.out.print("    Price: "+food.getPrice());
            if(!food.isUnlisted())
                System.out.print("    Status: Available");
            else
                System.out.print("    Status: UnAvailable");
            if(food.getDiscountPercent()>0)
                System.out.println("    Discount: "+food.getDiscountPercent()+"%");
            else
                System.out.println();
        }

    }

    public static HashMap<String,Food> searchFood(String foodName)//Key:id of the restaurant" "id of the food//value:the food
    {
        HashMap<String,Food> foodSearchResult = new HashMap<>();

        for(HashMap.Entry<String, Resturant> resturantEntry :Memory.getResturantsList().entrySet())
        {
            for(Food food:resturantEntry.getValue().getMenu())
            {
                if(food.getName().contains(foodName))
                    foodSearchResult.put(resturantEntry.getKey()+" "+food.getId(), food);
            }
        }

        if(foodSearchResult.size()!=0)
        {
            return foodSearchResult;
        }
        else
            return null;
    }

    public static Food selectFood(String id)
    {
        if(Memory.getFood(id)!=null)
            return Memory.getFood(id);
        else
            return null;
    }

}
