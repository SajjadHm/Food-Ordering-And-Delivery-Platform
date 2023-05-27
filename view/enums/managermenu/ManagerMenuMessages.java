package view.enums.managermenu;

public enum ManagerMenuMessages {
    RESTAURANT_ADDED(""),
    RESTAURANT_EXISTS(""),
    RESTAURANT_NOT_FOUND(""),
    RESTAURANT_REMOVED(""),
    RESTAURANT_OPENED(""),
    NO_RESTAURANTS(""),
    ;

    private String message;

    ManagerMenuMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
