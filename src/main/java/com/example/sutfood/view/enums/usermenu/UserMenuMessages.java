package com.example.sutfood.view.enums.usermenu;

public enum UserMenuMessages
{
    RESTAURANT_NOT_FOUND("RESTAURANT NOT FOUND!"),
    SEARCH_RESULTS("SEARCH RESULTS:"),
    RESTAURANT_SELECTED_SUCCESSFULLY("THE RESTAURANT SELECTED SUCCESSFULLY!"),
    FOOD_NOT_FOUND("FOOD NOT FOUND!"),
    FOOD_SELECTED_SUCCESSFULLY("THE FOOD SELECTED SUCCESSFULLY!"),
    RESTAURANT_NOT_SELECTED("YOU SHOULD SELECT A RESTAURANT FIRST!"),
    RESTAURANT_WITHOUT_COMMENT("THE RESTAURANT HAS NO COMMENT!"),
    Comments("The Comment(s):"),
    ENTER_COMMENT("ENTER YOUR COMMENT:"),
    COMMENT_ADDED_SUCCESSFULLY("YOUR COMMENT ADDED SUCCESSFULLY!"),
    COMMENT_ID_INCORRECT("INCORRECT COMMENT ID!"),
    COMMENT_EDITED_SUCCESSFULLY("YOUR COMMENT EDITED SUCCESSFULLY!"),
    RESTAURANT_WITHOUT_RATING("THE RESTAURANT HAS NO RATING!"),
    THE_RATING("THE RATING: "),
    ENTER_RATING("ENTER YOUR RATING IN 0 TO 5:"),
    RATE_SUBMIT_SUCCESSFULLY("THX FOR RATING! YOUR RATING SUBMITTED SUCCESSFULLY!"),
    NOT_RATED_YET("YOU HAVE NOT RATED YET!"),
    RATE_EDIT_SUCCESSFULLY("THX FOR RATING! YOUR RATING EDITED SUCCESSFULLY!"),
    FOOD_NOT_SELECTED("YOU SHOULD SELECT A FOOD FIRST!"),
    FOOD_WITHOUT_COMMENT("THE FOOD HAS NO COMMENT!"),
    ADD_BLIND_COMMENT_FOOD("YOU CAN'T COMMENT THIS FOOD BECAUSE YOU HAVE NOT ORDERED IT YET!"),
    FOOD_WITHOUT_RATING("THE FOOD HAS NO RATING!"),
    SUBMIT_BLIND_RATING_FOOD("YOU CAN'T RATE THIS FOOD BECAUSE YOU HAVE NOT ORDERED IT YET!"),
    CANT_ADD_TO_CART("SORRY! THIS FOOD IS UNAVAILABLE :(("),
    ADD_FOOD_TO_CART_SUCCESSFULLY("THE FOOD ADDED TO CART SUCCESSFULLY!"),
    NO_HISTORY("THERE IS NO ORDER HISTORY!"),
    YOUR_HISTORY("YOUR  ORDER HISTORY: "),
    ORDER_NOT_FOUND("THE ORDER NOT FOUND!"),
    ORDER_SELECTED_SUCCESSFULLY("THE ORDER SELECTED! ORDER DETAILS: "),
    EMPTY_CART("YOUR CART IS EMPTY :("),
    CART_STATUS("YOUR CART STATUS: "),
    CONFIRMED_SUCCESSFULLY("THE ORDER CONFIRMED SUCCESSFULLY!"),
    ENTER_ORDER_ID("ENTER ORDER ID: "),
    BALANCE_NOT_ENOUGH("YOUR BALANCE ISN'T ENOUGH! PLEASE CHARGE YOUR ACCOUNT!"),
    CHARGED_SUCCESSFULLY("THE ACCOUNT CHARGED SUCCESSFULLY!"),
    LOGOUT("USER LOGGED OUT SUCCESSFULLY!")

    ;

    final String message;

    UserMenuMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
