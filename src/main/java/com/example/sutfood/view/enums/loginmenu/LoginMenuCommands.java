package com.example.sutfood.view.enums.loginmenu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginMenuCommands {
    ADD_ADMIN("add\\s+admin\\s+(?<userName>\\S+)\\s+(?<passWord>\\S+)"),
    ADD_USER("add\\s+user\\s+(?<userName>\\S+)\\s+(?<passWord>\\S+)"),
    LOGIN_ADMIN("login\\s+admin\\s+(?<userName>\\S+)\\s+(?<passWord>\\S+)"),
    LOGIN_USER("login\\s+user\\s+(?<userName>\\S+)\\s+(?<passWord>\\S+)"),
    LOGOUT("logout"),
    END("end"),
    BACK("back"),
    ;

    String regex;

    LoginMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, LoginMenuCommands command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        if (matcher.matches()) return matcher;
        return null;
    }
}
