package controllers;

import model.Memory;
import view.enums.usermenu.UserMenuMessages;

public class UserMenuController
{
    public static UserMenuMessages chargeAccountController(String amount)
    {
        Memory.getCurrentUser().chargeAccount(Integer.parseInt(amount));
        return UserMenuMessages.CHARGED_SUCCESSFULLY;
    }


}
