package view.enums.managermenu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ManagerMenuCommands {
    ADD_RESTAURANT("add\\s+restaurant\\s+(?<name>\\w+)\\s+(?<location>\\w+)\\s+(?<foodType>[\\w\\s]+)"),
    REMOVE_RESTAURANT("remove\\s+restaurant\\s+(?<id>\\w+)"),
    SELECT_RESTAURANT("select\\s+(?<id>\\w+)"),
    SHOW("show"),
    LOGOUT("logout"),
    END("end"),
    ;

    String regex;
    ManagerMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, ManagerMenuCommands command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        if (matcher.matches()) return matcher;
        return null;
    }
}
