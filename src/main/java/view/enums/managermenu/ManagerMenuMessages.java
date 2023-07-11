package view.enums.managermenu;

public enum ManagerMenuMessages {
    RESTAURANT_ADDED("Restaurant added successfully."),
    RESTAURANT_EXISTS("Restaurant already exists with this name."),
    RESTAURANT_NOT_FOUND("There's no restaurant with this name."),
    RESTAURANT_REMOVED("Restaurant removed successfully."),
    RESTAURANT_OPENED("Entering restaurant:"),
    NO_RESTAURANTS("There are no restaurants."),
    INVALID_FOOD_TYPE("Invalid food type(s)."),
    INVALID_COMMAND("Invalid command!"),
    LOGGED_OUT("Manager logged out successfully."),
    CANCELLED("Cancelled."),
    NO_RESTAURANT_SELECTED("No restaurants are selected."),
    ARE_YOU_SURE_FOODTYPE("Are you sure you want to change your restaurant type? [Y:Yes / N:No]"),
    OK(""),
    RESTAURANT_FOOD_TYPE_CHANGED("Restaurant food type changed successfully."),
    EMPTY_MENU("Menu is empty!"),
    FOOD_ADDED("Food added to menu successfully."),
    FOOD_EXISTS("Food with this name already exists."),
    FOOD_NOT_FOUND("Food with this name/id doesn't exist."),
    FOOD_REMOVED("Food removed from menu successfully."),
    ACTIVE_ORDER("You can't deactivate this food. There's an order ongoing."),
    FOOD_DEACTIVATED("Food deactivated."),
    FOOD_ACTIVATED("Food activated."),
    INVALID_DISCOUNT_PERCENT("Discount percent value is between 0 and 100."),
    INVALID_TIMESTAMP("You can't travel in the time!"),
    FOOD_DISCOUNTED("Food discounted successfully."),
    FOOD_SELECTED("Food selected successfully."),
    FOOD_DESELECTED("Food deselected successfully."),
    NO_FOOD_SELECTED("No food has been selected."),
    DISPLAY_RATINGS(""),
    DISPLAY_COMMENTS(""),
    ;

    private String message;

    ManagerMenuMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
