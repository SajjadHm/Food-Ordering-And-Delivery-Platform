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

    ;

    private String message;

    ManagerMenuMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
