package view;

import controllers.UserMenuController;
import model.Cart;
import model.*;
import model.resturant.*;
import model.social.Comment;
import model.social.Rating;
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
                message = UserMenuController.editCommentControllerFood(matcher.group("commentId"));
                if(message.getMessage().equals(UserMenuMessages.ENTER_COMMENT.getMessage()))
                {
                    print(message.getMessage());
                    String newText = scanner.nextLine().trim();
                    editCommentFood(matcher.group("commentId"),newText);
                    print(UserMenuMessages.COMMENT_EDITED_SUCCESSFULLY.getMessage());
                }
                else
                {
                    print(message.getMessage());
                }
            }
            else if ((matcher = UserMenuCommands.getMatcher(input, UserMenuCommands.DISPLAY_RATINGS_FOOD)) != null)
            {
                message = UserMenuController.displayRatingsControllerFood();
                if(message.getMessage().equals(UserMenuMessages.THE_RATING.getMessage()))
                {
                    System.out.print(message.getMessage());
                    System.out.printf("%.2f\n",displayRatingFood());
                }
                else
                    print(message.getMessage());
            }
            else if ((matcher = UserMenuCommands.getMatcher(input, UserMenuCommands.SUBMIT_RATING_FOOD)) != null)
            {
                message = UserMenuController.addRatingControllerFood();
                if(message.getMessage().equals(UserMenuMessages.FOOD_NOT_SELECTED.getMessage()))
                    print(message.getMessage());
                else if(message.getMessage().equals(UserMenuMessages.SUBMIT_BLIND_RATING_FOOD.getMessage()))
                    print(message.getMessage());
                else
                {
                    addNewRatingFood(matcher.group("rating"));
                    print(UserMenuMessages.RATE_SUBMIT_SUCCESSFULLY.getMessage());
                }
            }
            else if ((matcher = UserMenuCommands.getMatcher(input, UserMenuCommands.EDIT_RATING_FOOD)) != null)
            {
                message = UserMenuController.editRatingControllerFood();
                if(message.getMessage().equals(UserMenuMessages.ENTER_RATING.getMessage()))
                {
                    print(message.getMessage());
                    editRatingFood(Integer.parseInt(scanner.nextLine().trim()));
                    print(UserMenuMessages.RATE_EDIT_SUCCESSFULLY.getMessage());
                }
                else
                    print(message.getMessage());

            }
            else if ((matcher = UserMenuCommands.getMatcher(input, UserMenuCommands.ADD_FOOD_TO_CART)) != null)
            {
                message = UserMenuController.addFoodToCartController();
                if(message.getMessage().equals(UserMenuMessages.ADD_FOOD_TO_CART_SUCCESSFULLY.getMessage()))
                {
                    addFoodToCart();
                    print(message.getMessage());
                }
                else
                    print(message.getMessage());

            }
            else if ((matcher = UserMenuCommands.getMatcher(input, UserMenuCommands.ORDER_HISTORY)) != null)
            {
                message = UserMenuController.accessOrderHistoryController();
                if(message.getMessage().equals(UserMenuMessages.NO_HISTORY.getMessage()))
                    print(message.getMessage());
                else
                {
                    print(message.getMessage());
                    accessOrderHistory();
                }
            }
            else if ((matcher = UserMenuCommands.getMatcher(input, UserMenuCommands.SELECT_ORDER)) != null)
            {
                message = UserMenuController.selectOrderController(matcher.group("orderId"));
                if(message.getMessage().equals(UserMenuMessages.ORDER_NOT_FOUND.getMessage()))
                    print(message.getMessage());
                else
                {
                    print(message.getMessage());
                    selectOrder(matcher.group("orderId"));
                }

            }
            else if ((matcher = UserMenuCommands.getMatcher(input, UserMenuCommands.DISPLAY_CART_STATUS)) != null)
            {
                message = UserMenuController.displayCartStatusController();
                if(message.getMessage().equals(UserMenuMessages.EMPTY_CART.getMessage()))
                    print(message.getMessage());
                else
                {
                    print(message.getMessage());
                    displayCartStatus();
                }
            }
            else if ((matcher = UserMenuCommands.getMatcher(input, UserMenuCommands.CONFIRM_ORDER)) != null)
            {
                message = UserMenuController.confirmOrderController();
                if(message.getMessage().equals(UserMenuMessages.EMPTY_CART.getMessage()))
                    print(message.getMessage());
                else
                {
                    print(message.getMessage());
                    String orderId = scanner.nextLine();
                    if(!checkConfirmOrder(orderId))
                        print(UserMenuMessages.ORDER_NOT_FOUND.getMessage());
                    else
                    {
                        print(confirmOrder(orderId).getMessage());
                    }
                }
            }
            else if ((matcher = UserMenuCommands.getMatcher(input, UserMenuCommands.SHOW_DELIVERY_TIME)) != null)
            {

                System.out.println("ESTIMATED DELIVERY TIME: ");
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
        ArrayList<Restaurant> searchResult = Memory.getRestaurants(name);
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
            Restaurant userCurrentRestaurant = Memory.getCurrentUser().getUserCurrentRestaurant();
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

        for(HashMap.Entry<String, Restaurant> resturantEntry :Memory.getResturantsList().entrySet())
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
        Restaurant userCurrentRestaurant = Memory.getCurrentUser().getUserCurrentRestaurant();
        int id = userCurrentRestaurant.getComments().size();
        Comment newComment = new Comment(text,id+"rc", Memory.getCurrentUser());
        userCurrentRestaurant.getComments().add(newComment);
        Memory.getCurrentUser().getUserComments().put(newComment,userCurrentRestaurant);

    }

    public static boolean checkEditComment(String commentId)
    {
        boolean edit = false;
        HashMap<Comment, Restaurant> comments = Memory.getCurrentUser().getUserComments();
        for(Map.Entry<Comment, Restaurant> entry:comments.entrySet())
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
        HashMap<Comment, Restaurant> comments = Memory.getCurrentUser().getUserComments();
        for(Map.Entry<Comment, Restaurant> entry:comments.entrySet())
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
            Restaurant userCurrentRestaurant = Memory.getCurrentUser().getUserCurrentRestaurant();
            int id = userCurrentRestaurant.getRatings().size();
            Rating newRate= new Rating(Double.parseDouble(rate),id+"rr");
            userCurrentRestaurant.getRatings().add(newRate);
            Memory.getCurrentUser().getUserRatings().put(newRate,userCurrentRestaurant);
    }

    public static boolean checkEditRating()
    {
        boolean edit = false;
        HashMap<Rating, Restaurant> ratings = Memory.getCurrentUser().getUserRatings();
        for(Map.Entry<Rating, Restaurant> entry:ratings.entrySet())
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
        HashMap<Rating, Restaurant> ratings = Memory.getCurrentUser().getUserRatings();
        for(Map.Entry<Rating, Restaurant> entry:ratings.entrySet())
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
        for(Order order:Memory.getCurrentUser().getOrdersHistory())
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
        Comment newComment = new Comment(text,id+"fc", Memory.getCurrentUser());
        userCurrentFood.getComments().add(newComment);
        Memory.getCurrentUser().getUserCommentsFood().put(newComment,userCurrentFood);

    }

    public static boolean checkEditCommentFood(String commentId)
    {

        HashMap<Comment, Food> comments = Memory.getCurrentUser().getUserCommentsFood();
        for(Map.Entry<Comment, Food> entry:comments.entrySet())
        {
            if(entry.getKey().getId().equals(commentId))
            {
                return true;
            }
        }
        return false;
    }
    public static void editCommentFood(String commentId,String newText)
    {
        HashMap<Comment, Food> comments = Memory.getCurrentUser().getUserCommentsFood();
        for(Map.Entry<Comment, Food> entry:comments.entrySet())
        {
            if(entry.getKey().getId().equals(commentId))
            {
                entry.getKey().setMessage(newText);
                break;
            }
        }

    }
    public static double displayRatingFood()
    {
        double avgRatings = Rating.avgRatings(Memory.getCurrentUser().getUserCurrentFood().getRatings());
        System.out.print("");
        return avgRatings;
    }

    public static void addNewRatingFood(String rate)
    {
        Food userCurrentFood = Memory.getCurrentUser().getUserCurrentFood();
        int id = userCurrentFood.getRatings().size();
        Rating newRate= new Rating(Double.parseDouble(rate),id+"fr");
        userCurrentFood.getRatings().add(newRate);
        Memory.getCurrentUser().getUserRatingsFood().put(newRate,userCurrentFood);
    }

    public static boolean checkEditRatingFood()
    {
        HashMap<Rating, Food> ratings = Memory.getCurrentUser().getUserRatingsFood();
        for(Map.Entry<Rating, Food> entry:ratings.entrySet())
        {
            if(entry.getValue().getId().equals(Memory.getCurrentUser().getUserCurrentFood().getId()))
            {
                return true;
            }
        }
        return false;
    }

    public static void editRatingFood(int newRating)
    {
        HashMap<Rating, Food> ratings = Memory.getCurrentUser().getUserRatingsFood();
        for(Map.Entry<Rating, Food> entry:ratings.entrySet())
        {
            if(entry.getValue().getId().equals(Memory.getCurrentUser().getUserCurrentFood().getId()))
            {
                entry.getKey().setRating(newRating);
                break;
            }
        }

    }

    public static void addFoodToCart()
    {
        Restaurant userCurrentRestaurant = Memory.getCurrentUser().getUserCurrentRestaurant();
        Food userCurrentFood = Memory.getCurrentUser().getUserCurrentFood();
        if(checkRestaurantInCart()!=null)
        {
            checkRestaurantInCart().getOrder().add(userCurrentFood);
        }
        else
        {
            Order order = new Order(userCurrentRestaurant.getName(),userCurrentRestaurant.getId()+Memory.getCurrentUser().getOrdersHistory().size());
            order.setRestaurant(userCurrentRestaurant);
            order.add(userCurrentFood);
            Memory.getCurrentUser().getUserCart().add(new Cart(userCurrentRestaurant,order));
        }

    }

    public static Cart checkRestaurantInCart()
    {
        Restaurant userCurrentRestaurant = Memory.getCurrentUser().getUserCurrentRestaurant();
        for(Cart cart:Memory.getCurrentUser().getUserCart())
        {
            if(cart.getRestaurant().getId().equals(userCurrentRestaurant.getId()))
                return cart;
        }
        return null;
    }

    public static void accessOrderHistory()
    {
        for(Order order:Memory.getCurrentUser().getOrdersHistory())
        {
            System.out.print("Restaurant Name: "+order.getRestaurant().getName()+"||");
            System.out.print("Order Id: "+order.getId()+"||");
            System.out.println("Total Price: "+order.getTotalPrice()+"IRT");
            System.out.println("-------------------------------------------------------");
        }
    }


    public static boolean checkSelectOrder(String orderId)
    {
        for(Order order:Memory.getCurrentUser().getOrdersHistory())
        {
            if(order.getId().equals(orderId))
                return true;
        }
        return false;
    }

    public static void selectOrder(String orderId)
    {
        Order foundedOrder=null;
        for(Order order:Memory.getCurrentUser().getOrdersHistory())
        {
            if(order.getId().equals(orderId))
            {
                foundedOrder = order;
                break;
            }
        }
        try
        {
            System.out.print("Restaurant Name: "+foundedOrder.getRestaurant().getName()+"||");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        System.out.print("Order Id: "+foundedOrder.getId()+"||");
        System.out.println("Total Price: "+foundedOrder.getTotalPrice()+"IRT");
        System.out.println("Order Food(s)");
        for(Food food:foundedOrder)
        {
            System.out.println("Food Name: "+food.getName()+"||"+"Food Id: "+food.getId()+"||"+"Food Price: "+food.getPrice()+"IRT");
        }
    }


    public static void displayCartStatus()
    {
        ArrayList<Cart> userCarts = Memory.getCurrentUser().getUserCart();
        for(Cart cart:userCarts)
        {
            System.out.print("Restaurant Name: "+cart.getRestaurant().getName()+"||");
            System.out.println("Order Id: "+cart.getOrder().getId()+"||"+"Order Total Price: "+cart.getOrder().getTotalPrice()+"IRT");
            System.out.println("Order Details: ");
            for (Food food: cart.getOrder())
            {
                System.out.println("Food Name: "+food.getName()+"||"+"Food Price: "+food.getPrice()+"IRT");
            }
            System.out.println("--------------------------------------------------------");

        }
    }

    public static boolean checkConfirmOrder(String orderId)
    {
        for(Cart cart:Memory.getCurrentUser().getUserCart())
        {
            if(cart.getOrder().getId().equals(orderId))
                return true;
        }
        return false;
    }

    public static UserMenuMessages confirmOrder(String orderId)
    {
        for(Cart cart:Memory.getCurrentUser().getUserCart())
        {
            if(cart.getOrder().getId().equals(orderId))
            {
                if(Memory.getCurrentUser().getBalance()>cart.getOrder().getTotalPrice())
                {
                    Memory.getCurrentUser().setBalance(Memory.getCurrentUser().getBalance()-cart.getOrder().getTotalPrice());
                    cart.getOrder().setStatus(DeliveryStatus.PREPARING);
                    Memory.getCurrentUser().getOrdersHistory().add(cart.getOrder());
                    Memory.getCurrentUser().getUserCart().remove(cart);
                    return UserMenuMessages.CONFIRMED_SUCCESSFULLY;
                }
                return UserMenuMessages.BALANCE_NOT_ENOUGH;

            }

        }
        return null;
    }



}
