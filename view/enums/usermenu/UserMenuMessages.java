package view.enums.usermenu;

public enum UserMenuMessages
{
    RESTAURANT_NOT_FOUND("RESTAURANT NOT FOUND!"),
    SEARCH_RESULTS("SEARCH RESULTS:"),
    RESTAURANT_SELECTED_SUCCESSFULLY("THE RESTAURANT SELECTED SUCCESSFULLY!"),
    FOOD_NOT_FOUND("FOOD NOT FOUND!"),
    FOOD_SELECTED_SUCCESSFULLY("THE FOOD SELECTED SUCCESSFULLY"),
    RESTAURANT_WITHOUT_COMMENT("THE RESTAURANT HAS NO COMMENT!"),
    COMMENT_NOT_FOUND("COMMENT NOT FOUND!"),
    RESTAURANT_WITHOUT_RATING("THE RESTAURANT HAS NO RATING!"),
    FOOD_WITHOUT_COMMENT("THE FOOD HAS NO COMMENT!"),
    ADD_BLIND_COMMENT_FOOD("YOU CAN'T COMMENT THIS FOOD BECAUSE YOU HAVE NOT ORDERED IT YET"),
    FOOD_WITHOUT_RATING("THE FOOD HAS NO RATING!"),
    SUBMIT_BLIND_RATING_FOOD("YOU CAN'T COMMENT THIS FOOD BECAUSE YOU HAVE NOT ORDERED IT YET"),
    ADD_FOOD_TO_CART_SUCCESSFULLY("THE FOOD ADDED TO CART SUCCESSFULLY"),
    NO_HISTORY("THERE IS NO ORDER HISTORY"),
    ORDER_NOT_FOUND("THE ORDER NOT FOUND!"),
    EMPTY_CART("YOUR CART IS EMPTY :("),
    CONFIRMED_SUCCESSFULLY("THE ORDER CONFIRMED SUCCESSFULLY!"),
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
