package controllers;

import model.Memory;
import model.resturant.Resturant;
import view.UserMenu;
import view.enums.usermenu.UserMenuMessages;

public class UserMenuController
{
    public static UserMenuMessages logoutController()
    {
        Memory.getCurrentUser().logout();
        Memory.setCurrentUser(null);
        return UserMenuMessages.LOGOUT;
    }
    public static UserMenuMessages chargeAccountController(String amount)
    {
        Memory.getCurrentUser().chargeAccount(Integer.parseInt(amount));
        return UserMenuMessages.CHARGED_SUCCESSFULLY;
    }

    public static  UserMenuMessages searchRestaurantController(String name)
    {
        if(Memory.getRestaurants(name) == null)
             return  UserMenuMessages.RESTAURANT_NOT_FOUND;
        else
            return UserMenuMessages.SEARCH_RESULTS;
    }

    public static UserMenuMessages selectRestaurantController(String id)
    {
        if(Memory.getRestaurant(id)!=null)
        {
            Memory.getCurrentUser().setUserCurrentRestaurant(Memory.getRestaurant(id));
            return UserMenuMessages.RESTAURANT_SELECTED_SUCCESSFULLY;
        }
        else
        {
            return null;
        }

    }

    public static UserMenuMessages searchFoodController(String name)
    {
        if(UserMenu.searchFood(name)==null)
            return UserMenuMessages.FOOD_NOT_FOUND;
        else
            return UserMenuMessages.SEARCH_RESULTS;
    }

    public static UserMenuMessages selectFoodController(String id)
    {
        if(UserMenu.selectFood(id)==null)
            return UserMenuMessages.FOOD_NOT_FOUND;
        else
        {
            Memory.getCurrentUser().setUserCurrentFood(Memory.getFood(id));
            return UserMenuMessages.FOOD_SELECTED_SUCCESSFULLY;
        }
    }

    public static UserMenuMessages displayCommentsController()
    {
        if(Memory.getCurrentUser().getUserCurrentRestaurant()==null)
            return UserMenuMessages.RESTAURANT_NOT_SELECTED;
        else
        {
            if(Memory.getCurrentUser().getUserCurrentRestaurant().getComments().size()==0)
                return UserMenuMessages.RESTAURANT_WITHOUT_COMMENT;
            else
                return UserMenuMessages.Comments;
        }
    }

    public static UserMenuMessages addCommentController()
    {
        if(Memory.getCurrentUser().getUserCurrentRestaurant()==null)
            return UserMenuMessages.RESTAURANT_NOT_SELECTED;
        else
        {
            return UserMenuMessages.ENTER_COMMENT;
        }
    }

    public static UserMenuMessages editCommentController(String commentId)
    {
        if(!UserMenu.checkEditComment(commentId))
            return UserMenuMessages.COMMENT_ID_INCORRECT;
        else
            return UserMenuMessages.ENTER_COMMENT;

    }

    public static UserMenuMessages displayRatingsController()
    {
        if(Memory.getCurrentUser().getUserCurrentRestaurant()==null)
            return UserMenuMessages.RESTAURANT_NOT_SELECTED;
        else
        {
            if(Memory.getCurrentUser().getUserCurrentRestaurant().getRatings().size()==0)
                return UserMenuMessages.RESTAURANT_WITHOUT_RATING;
            else
                return UserMenuMessages.THE_RATING;
        }
    }


    public static UserMenuMessages addRatingController()
    {
        if(Memory.getCurrentUser().getUserCurrentRestaurant()==null)
            return UserMenuMessages.RESTAURANT_NOT_SELECTED;
        else
        {
            return UserMenuMessages.ENTER_RATING;
        }

    }


    public static UserMenuMessages editRatingController()
    {
        if(Memory.getCurrentUser().getUserCurrentRestaurant()==null)
            return UserMenuMessages.RESTAURANT_NOT_SELECTED;
        else if(!UserMenu.checkEditRating())
        {
            return UserMenuMessages.NOT_RATED_YET;
        }
        else
            return UserMenuMessages.ENTER_RATING;

    }

    public static UserMenuMessages displayCommentsFoodController()
    {
        if(Memory.getCurrentUser().getUserCurrentFood()==null)
            return UserMenuMessages.FOOD_NOT_SELECTED;
        else
        {
            if(Memory.getCurrentUser().getUserCurrentFood().getComments().size()==0)
                return UserMenuMessages.FOOD_WITHOUT_COMMENT;
            else
                return UserMenuMessages.Comments;
        }
    }

}
