package controllers;

import model.Memory;
import model.accounts.Account;
import model.accounts.Manager;
import model.enums.ResturantFoodType;
import model.resturant.Resturant;
import view.enums.managermenu.ManagerMenuCommands;
import view.enums.managermenu.ManagerMenuMessages;

public class ManagerMenuController {

    public static ManagerMenuMessages checkAddRestaurant(String name, String location, ResturantFoodType[] foodTypes) {
        Manager manager = (Manager) Memory.getCurrentAccount();
        Resturant resturant = manager.getRestaurant(name);
        if (resturant != null) return ManagerMenuMessages.RESTAURANT_EXISTS;
        if (foodTypes == null) return ManagerMenuMessages.INVALID_FOOD_TYPE;
        resturant = new Resturant(name, foodTypes, location);
        manager.getResturants().add(resturant);
        return ManagerMenuMessages.RESTAURANT_ADDED;
    }
}
