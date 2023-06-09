package com.example.sutfood.view.enums.usermenu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum UserMenuCommands
{
    SEARCH_RESTAURANT("SEARCH\\s+RESTAURANT\\s+(?<restaurantName>\\S+)"),
    SELECT_RESTAURANT("SELECT\\s+RESTAURANT\\s+(?<restaurantId>\\S+)"),
    SEARCH_FOOD("SEARCH\\s+FOOD\\s+(?<foodName>\\S+)"),
    SELECT_FOOD("SELECT\\s+FOOD\\s+(?<foodId>\\S+)"),
    DISPLAY_COMMENTS("DISPLAY\\s+COMMENTS"),
    ADD_NEW_COMMENT("ADD\\s+NEW\\s+COMMENT"),
    EDIT_COMMENT("EDIT\\s+COMMENT\\s+(?<commentId>\\S+)"),
    DISPLAY_RATING("DISPLAY\\s+RATING"),
    SUBMIT_RATING("SUBMIT\\s+RATING\\s+(?<rating>\\S+)"),
    EDIT_RATING("EDIT\\s+RATING"),
    DISPLAY_COMMENTS_FOOD("DISPLAY\\s+COMMENTS\\s+FOOD"),
    ADD_NEW_COMMENT_FOOD("ADD\\s+NEW\\s+COMMENT\\s+FOOD"),
    EDIT_COMMENT_FOOD("EDIT\\s+COMMENT\\s+FOOD\\s+(?<commentId>\\S+)"),
    DISPLAY_RATINGS_FOOD("DISPLAY\\s+RATINGS\\s+FOOD"),
    SUBMIT_RATING_FOOD("SUBMIT\\s+RATING\\s+FOOD\\s+(?<rating>\\S+)"),
    EDIT_RATING_FOOD("EDIT\\s+RATING\\s+FOOD"),
    ADD_FOOD_TO_CART("ADD\\s+THIS\\s+FOOD\\s+TO\\s+CART"),
    ORDER_HISTORY("ACCESS\\s+ORDER\\s+HISTORY"),
    SELECT_ORDER("SELECT\\s+ORDER\\s+(?<orderId>\\S+)"),
    DISPLAY_CART_STATUS("DISPLAY\\s+CART\\s+STATUS"),
    CONFIRM_ORDER("CONFIRM\\s+ORDER"),
    SHOW_DELIVERY_TIME("SHOW\\s+ESTIMATED\\s+DELIVERY\\s+TIME"),
    CHARGE_ACCOUNT("CHARGE\\s+ACCOUNT\\s+(?<amount>\\d+)"),
    DISPLAY_ACCOUNT_CHARGE("DISPLAY\\s+ACCOUNT\\s+CHARGE"),
    END("end"),
    BACK("back"),
    LOGOUT("logout"),
    ;



    final String regex;

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
