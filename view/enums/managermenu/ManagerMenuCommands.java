package view.enums.managermenu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ManagerMenuCommands {
    ADD_RESTAURANT("add\\s+restaurant\\s+(?<name>\\w+)\\s+(?<location>\\w+)\\s+(?<foodType>[\\w\\s]+)"),
    REMOVE_RESTAURANT("remove\\s+restaurant\\s+(?<id>\\w+)"),
    SELECT_RESTAURANT("select\\s+(?<id>\\w+)"),
    SHOW_RESTAURANTS("show\\s+restaurants"),
    EDIT_FOODTYPES("edit\\s+foodtype\\s+(?<foodType>[\\w\\s]+)"),
    JA("(Y)|(y)|(Yes)"),
    NEIN("(N)|(n)|(No)"),
    LOGOUT("logout"),
    CLEAR_MENU("clear\\s+menu"),
    SELECT_MENU("select\\s+menu"),
    ADD_FOOD("add\\s+food\\s+(?<foodName>[\\w\\s]+)\\s+(?<price>\\d+)"),
    EDIT_FOOD("edit\\s+food\\s+(?<foodID>\\w+)\\s+(?<field>\\w+)\\s+(?<newFieldValue>\\w+)"),
    DELETE_FOOD("delete\\s+food\\s+(?<foodID>\\w+)"),
    DEACTIVE_FOOD("deactive\\s+food\\s+(?<foodID>\\w+)"),
    ACTIVE_FOOD("active\\s+food\\s+(?<foodID>\\w+)"),
    DISCOUNT_FOOD("discount\\s+food\\s+(?<foodID>\\w+)\\s+(?<percent>\\w+)\\s+(?<year>\\d{4})\\/(?<month>\\d{2})\\/(?<day>\\d{2})\\s{2}(?<hour>\\d{2}):(?<minute>\\d{2}):(?<second>\\d{2})"),
    SELECT_FOOD("select\\s+food\\s+(?<foodID>\\w+)"),
    DISPLAY_RATINGS("display\\s+ratings"),
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
