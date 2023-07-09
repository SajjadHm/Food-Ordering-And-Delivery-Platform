package view;

import com.sun.xml.internal.bind.v2.TODO;
import controllers.UserMenuController;
import model.Memory;
import model.resturant.Food;
import model.resturant.FoodMenu;
import model.resturant.Order;
import model.resturant.Resturant;
import model.social.Comment;
import model.social.Rating;
import view.enums.loginmenu.LoginMenuCommands;
import view.enums.loginmenu.LoginMenuMessages;
import view.enums.loginmenu.LoginMenuResults;
import view.enums.managermenu.ManagerMenuMessages;
import view.enums.usermenu.UserMenuCommands;
import view.enums.usermenu.UserMenuMessages;
import view.enums.usermenu.UserMenuResults;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
                message = UserMenuController.displayCommentsController();
                if(message.getMessage().equals(UserMenuMessages.Comments.getMessage()))
                {
                    print(message.getMessage());
                    System.out.println("");
                    displayComments();
                }
                else
                    print(message.getMessage());
            }
            else if ((matcher = UserMenuCommands.getMatcher(input, UserMenuCommands.ADD_NEW_COMMENT)) != null)
            {
                message = UserMenuController.addCommentController();
                if(message.getMessage().equals(UserMenuMessages.RESTAURANT_NOT_SELECTED.getMessage()))
                    print(message.getMessage());
                else
                {
                    print(message.getMessage());
                    String text = scanner.nextLine().trim();
                    addNewComment(text);
                    print(UserMenuMessages.COMMENT_ADDED_SUCCESSFULLY.getMessage());
                }

            }
            else if ((matcher = UserMenuCommands.getMatcher(input, UserMenuCommands.EDIT_COMMENT)) != null)
            {
                message = UserMenuController.editCommentController(matcher.group("commentId"));
                if(message.getMessage().equals(UserMenuMessages.COMMENT_ID_INCORRECT.getMessage()))
                    print(message.getMessage());
                else
                {
                    print(message.getMessage());
                    String newText = scanner.nextLine().trim();
                    editComment(matcher.group("commentId"),newText);
                    print(UserMenuMessages.COMMENT_EDITED_SUCCESSFULLY.getMessage());
                }
            }
            else if ((matcher = UserMenuCommands.getMatcher(input, UserMenuCommands.DISPLAY_RATING)) != null)
            {
                message = UserMenuController.displayRatingsController();
                if(message.getMessage().equals(UserMenuMessages.THE_RATING.getMessage()))
                {
                    System.out.print(message.getMessage());
                    System.out.printf("%.2f\n",displayRating());
                }
                else
                    print(message.getMessage());
            }
            else if ((matcher = UserMenuCommands.getMatcher(input, UserMenuCommands.SUBMIT_RATING)) != null)
            {
                message = UserMenuController.addRatingController();
                if(message.getMessage().equals(UserMenuMessages.RESTAURANT_NOT_SELECTED.getMessage()))
                    print(message.getMessage());
                else
                {
                    addNewRating(matcher.group("rating"));
                    print(UserMenuMessages.RATE_SUBMIT_SUCCESSFULLY.getMessage());
                }
            }
            else if ((matcher = UserMenuCommands.getMatcher(input, UserMenuCommands.EDIT_RATING)) != null)
            {
                message = UserMenuController.editRatingController();
                if(message.getMessage().equals(UserMenuMessages.ENTER_RATING.getMessage()))
                {
                    print(message.getMessage());
                    editRating(Integer.parseInt(scanner.nextLine().trim()));
                    print(UserMenuMessages.RATE_EDIT_SUCCESSFULLY.getMessage());
                }
                else
                    print(message.getMessage());

            }
            else if ((matcher = UserMenuCommands.getMatcher(input, UserMenuCommands.DISPLAY_COMMENTS_FOOD)) != null)
            {
                message = UserMenuController.displayCommentsFoodController();
                if(message.getMessage().equals(UserMenuMessages.Comments.getMessage()))
                {
                    print(message.getMessage());
                    System.out.println("");
                    displayCommentsFood();
                }
                else
                    print(message.getMessage());
            }
            else if ((matcher = UserMenuCommands.getMatcher(input, UserMenuCommands.ADD_NEW_COMMENT_FOOD)) != null)
            {
                message = UserMenuController.addCommentControllerFood();
                if(message.getMessage().equals(UserMenuMessages.ENTER_COMMENT.getMessage()))
                {
                    print(message.getMessage());
                    addNewCommentFood(scanner.nextLine());
                    print(UserMenuMessages.COMMENT_ADDED_SUCCESSFULLY.getMessage());
                }
                else
                {
                    print(message.getMessage());
                }
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

    public static void displayComments()
    {
        for(Comment comment:Memory.getCurrentUser().getUserCurrentRestaurant().getComments())
        {
            System.out.println("CommentText: "+comment.getMessage());
            System.out.println("CommentID: "+comment.getId());
            if(comment.getReplies().size()!=0)
            {
                System.out.println("The Reply(s)");
                for (Comment reply:comment.getReplies())
                {
                    System.out.println("ReplyText: "+reply.getMessage());
                    System.out.println("ReplyID: "+reply.getId());
                }
            }
            System.out.println("---------------------------------------");

        }

    }

    public static void addNewComment(String text)
    {
        Resturant userCurrentRestaurant = Memory.getCurrentUser().getUserCurrentRestaurant();
        int id = userCurrentRestaurant.getComments().size();
        Comment newComment = new Comment(text,id+"rc");
        userCurrentRestaurant.getComments().add(newComment);
        Memory.getCurrentUser().getUserComments().put(newComment,userCurrentRestaurant);

    }

    public static boolean checkEditComment(String commentId)
    {
        boolean edit = false;
        HashMap<Comment, Resturant> comments = Memory.getCurrentUser().getUserComments();
        for(Map.Entry<Comment, Resturant> entry:comments.entrySet())
        {
            if(entry.getKey().getId().equals(commentId))
            {
                edit = true;
                break;
            }
        }
        return edit;
    }
    public static void editComment(String commentId,String newText)
    {
        HashMap<Comment, Resturant> comments = Memory.getCurrentUser().getUserComments();
        for(Map.Entry<Comment, Resturant> entry:comments.entrySet())
        {
            if(entry.getKey().getId().equals(commentId))
            {
                entry.getKey().setMessage(newText);
                break;
            }
        }

    }

    public static double displayRating()
    {
        double avgRatings = Rating.avgRatings(Memory.getCurrentUser().getUserCurrentRestaurant().getRatings());
        return avgRatings;
    }

    public static void addNewRating(String rate)
    {
            Resturant userCurrentRestaurant = Memory.getCurrentUser().getUserCurrentRestaurant();
            int id = userCurrentRestaurant.getRatings().size();
            Rating newRate= new Rating(Double.parseDouble(rate),id+"rr");
            userCurrentRestaurant.getRatings().add(newRate);
            Memory.getCurrentUser().getUserRatings().put(newRate,userCurrentRestaurant);
    }

    public static boolean checkEditRating()
    {
        boolean edit = false;
        HashMap<Rating, Resturant> ratings = Memory.getCurrentUser().getUserRatings();
        for(Map.Entry<Rating, Resturant> entry:ratings.entrySet())
        {
            if(entry.getValue().getId().equals(Memory.getCurrentUser().getUserCurrentRestaurant().getId()))
            {
                edit = true;
                break;
            }
        }
        return edit;
    }

    public static void editRating(int newRating)
    {
        HashMap<Rating, Resturant> ratings = Memory.getCurrentUser().getUserRatings();
        for(Map.Entry<Rating, Resturant> entry:ratings.entrySet())
        {
            if(entry.getValue().getId().equals(Memory.getCurrentUser().getUserCurrentRestaurant().getId()))
            {
                entry.getKey().setRating(newRating);
                break;
            }
        }

    }

    private static void displayCommentsFood()
    {
        for(Comment comment:Memory.getCurrentUser().getUserCurrentFood().getComments())
        {
            System.out.println("CommentText: "+ comment.getMessage());
            System.out.println("CommentID: "+comment.getId());
            if(comment.getReplies().size()!=0)
            {
                System.out.println("The Reply(s)");
                for (Comment reply:comment.getReplies())
                {
                    System.out.println("ReplyText: "+reply.getMessage());
                    System.out.println("ReplyID: "+reply.getId());
                }
            }
            System.out.println("---------------------------------------");
        }
    }


    public static boolean isInOrders(String foodId)
    {
        for(Order order:Memory.getCurrentUser().getOrders())
        {
            for (Food food :order)
            {
                if(food.getId().equals(foodId))
                    return true;
            }
        }
        return false;
    }

    public static void addNewCommentFood(String text)
    {
        Food userCurrentFood = Memory.getCurrentUser().getUserCurrentFood();
        int id = userCurrentFood.getComments().size();
        Comment newComment = new Comment(text,id+"fc");
        userCurrentFood.getComments().add(newComment);
        Memory.getCurrentUser().getUserCommentsFood().put(newComment,userCurrentFood);

    }


}
