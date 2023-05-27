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
    ;

    private String message;

    ManagerMenuMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
