package view.enums.managermenu;

import view.ManagerMenu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ManagerMenuCommands {
    ADD_RESTAURANT("add\\s+(?<name>\\w+)\\s+(?<foodType>\\w+)\\s+(?<location>\\w+)"),
    REMOVE_RESTAURANT("remove\\s+(?<id>\\w+)"),
    SELECT_RESTAURANT("select\\s+(?<id>\\w+)"),
    SHOW("show"),
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
