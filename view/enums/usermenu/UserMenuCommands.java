package view.enums.usermenu;

import view.enums.loginmenu.LoginMenuCommands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum UserMenuCommands
{
    SEARCH_RESTAURANT(""),
    SELECT_RESTAURANT(""),
    SEARCH_FOOD(""),
    SELECT_FOOD(""),
    DISPLAY_COMMENTS(""),
    ADD_NEW_COMMENTS(""),
    EDIT_COMMENTS(""),
    DISPLAY_RATINGS(""),
    SUBMIT_RATINGS(""),
    EDIT_RATINGS(""),
    DISPLAY_COMMENTS_FOOD(""),
    ADD_NEW_COMMENTS_FOOD(""),
    EDIT_COMMENTS_FOOD(""),
    DISPLAY_RATINGS_FOOD(""),
    SUBMIT_RATINGS_FOOD(""),
    EDIT_RATINGS_FOOD(""),
    ADD_FOOD_TO_CART(""),
    ORDER_HISTORY(""),
    SELECT_ORDER(""),
    DISPLAY_CART_STATUS(""),
    CONFIRM_ORDER(""),
    SHOW_DELIVERY_TIME(""),
    CHARGE_ACCOUNT(""),
    DISPLAY_ACCOUNT_CHARGE("");



    String regex;

    UserMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, UserMenuCommands command)
    {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        if (matcher.matches())
            return matcher;
        else
            return null;
    }
}
