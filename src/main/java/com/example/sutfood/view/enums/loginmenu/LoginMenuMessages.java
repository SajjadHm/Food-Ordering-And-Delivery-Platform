package com.example.sutfood.view.enums.loginmenu;

public enum LoginMenuMessages {
    INCORRECT_USERNAME("Incorrect Username!"
            + "\n" + "Username only contains letters and numbers."),
    INCORRECT_PASSWORD("Incorrect Password!"
            + "\n" + "Password doesn't contain spaces."),
    WEAK_PASSWORD("Weak Password!"
            + "\n" + "Password must be at least 8 characters long and contains both " +
            "upper-case and lower-case letters, numbers and special characters."),
    INVALID_ADMIN_USERNAME("Invalid Admin Username!"
            + "\n" + "There is no admin account with this username."),
    INVALID_USER_USERNAME("Invalid User Username!"
            + "\n" + "There is no user account with this username."),
    INVALID_PASSWORD("Invalid Password"
            + "\n" + "Passwords doesn't match."),
    ADMIN_EXISTS("Admin with this username already exists."),
    USER_EXISTS("User with this username already exists."),
    ADMIN_ACCOUNT_CREATED("Admin account created successfully."),
    USER_ACCOUNT_CREATED("User account created successfully."),
    ADMIN_LOGIN_SUCCESSFUL("Admin account logged in successfully."),
    USER_LOGIN_SUCCESSFUL("User account logged in successfully."),
    LOGOUT("Logged Out"),
    INVALID_COMMAND("Invalid Command!"),
    ;

    final String message;

    LoginMenuMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
