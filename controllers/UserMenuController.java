package controllers;

import model.Memory;
import model.resturant.Resturant;
import view.enums.usermenu.UserMenuMessages;

public class UserMenuController
{
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
        if(Memory.getRestaurant(Integer.parseInt(id))!=null)
        {
            Memory.getCurrentUser().setUserCurrentRestaurant(Memory.getRestaurant(Integer.parseInt(id)));
            return UserMenuMessages.RESTAURANT_SELECTED_SUCCESSFULLY;
        }
        else
        {
            return null;
        }

    }


}
