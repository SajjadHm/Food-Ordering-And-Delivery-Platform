package com.example.sutfood.controllers;

import com.example.sutfood.model.Memory;
import com.example.sutfood.view.UserMenu;
import com.example.sutfood.view.enums.usermenu.UserMenuMessages;

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
            Memory.getCurrentUser().setUserCurrentRestaurant(Memory.getRestaurant(Memory.getFood(id).getResturant()));
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

    public static UserMenuMessages addCommentControllerFood()
    {
        if(Memory.getCurrentUser().getUserCurrentFood()==null)
            return UserMenuMessages.FOOD_NOT_SELECTED;
        else if(!UserMenu.isInOrders(Memory.getCurrentUser().getUserCurrentFood().getId()))
        {
            return UserMenuMessages.ADD_BLIND_COMMENT_FOOD;
        }
        else
            return UserMenuMessages.ENTER_COMMENT;
    }

    public static UserMenuMessages editCommentControllerFood(String commentId)
    {
        if(!UserMenu.checkEditCommentFood(commentId))
            return UserMenuMessages.COMMENT_ID_INCORRECT;
        else
            return UserMenuMessages.ENTER_COMMENT;

    }

    public static UserMenuMessages displayRatingsControllerFood()
    {
        if(Memory.getCurrentUser().getUserCurrentFood()==null)
            return UserMenuMessages.FOOD_NOT_SELECTED;
        else
        {
            if(Memory.getCurrentUser().getUserCurrentFood().getRatings().size()==0)
                return UserMenuMessages.FOOD_WITHOUT_RATING;
            else
                return UserMenuMessages.THE_RATING;
        }
    }

    public static UserMenuMessages addRatingControllerFood()
    {
        if(Memory.getCurrentUser().getUserCurrentFood()==null)
            return UserMenuMessages.FOOD_NOT_SELECTED;

        else if(!UserMenu.isInOrders(Memory.getCurrentUser().getUserCurrentFood().getId()))
        {
            return UserMenuMessages.SUBMIT_BLIND_RATING_FOOD;
        }
        else
        {
            return UserMenuMessages.ENTER_RATING;
        }
    }

    public static UserMenuMessages editRatingControllerFood()
    {
        if(Memory.getCurrentUser().getUserCurrentFood()==null)
            return UserMenuMessages.FOOD_NOT_SELECTED;
        else if(!UserMenu.checkEditRatingFood())
        {
            return UserMenuMessages.NOT_RATED_YET;
        }
        else
            return UserMenuMessages.ENTER_RATING;

    }

    public static UserMenuMessages addFoodToCartController()
    {
        if(Memory.getCurrentUser().getUserCurrentFood()==null)
            return UserMenuMessages.FOOD_NOT_SELECTED;
        else
        {
            if(Memory.getCurrentUser().getUserCurrentFood().isUnlisted())
                return UserMenuMessages.CANT_ADD_TO_CART;
            else
                return UserMenuMessages.ADD_FOOD_TO_CART_SUCCESSFULLY;
        }
    }


    public static UserMenuMessages accessOrderHistoryController()
    {
        if(Memory.getCurrentUser().getOrdersHistory().size()==0)
            return UserMenuMessages.NO_HISTORY;
        else
            return UserMenuMessages.YOUR_HISTORY;
    }

    public static UserMenuMessages selectOrderController(String orderId)
    {
        if(!UserMenu.checkSelectOrder(orderId))
            return UserMenuMessages.ORDER_NOT_FOUND;
        else
            return UserMenuMessages.ORDER_SELECTED_SUCCESSFULLY;

    }

    public static UserMenuMessages displayCartStatusController()
    {
        if(Memory.getCurrentUser().getUserCart().size()==0)
            return UserMenuMessages.EMPTY_CART;
        else
            return UserMenuMessages.CART_STATUS;
    }

    public static UserMenuMessages confirmOrderController()
    {
        if(Memory.getCurrentUser().getUserCart().size()==0)
            return UserMenuMessages.EMPTY_CART;
        else
            return UserMenuMessages.ENTER_ORDER_ID;
    }
}
