package com.example.sutfood.view.enums.managermenu;

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
    DISCOUNT_FOOD("discount\\s+food\\s+(?<foodID>\\w+)\\s+(?<percent>\\d+\\.?\\d+?)\\s+(?<year>\\d{4})\\/(?<month>\\d{2})\\/(?<day>\\d{2})\\s{2}(?<hour>\\d{2}):(?<minute>\\d{2}):(?<second>\\d{2})"),
    SELECT_FOOD("select\\s+food\\s+(?<foodID>\\w+)"),
    DESELECT_FOOD("deselect\\s+food"),
    DISPLAY_RATINGS("display\\s+ratings"),
    DISPLAY_COMMENTS("display\\s+comments"),
    ADD_NEW_RESPONSE("add\\s+new\\s+response\\s+(?<commentID>\\w+)\\s+(?<message>[^\n]+)"),
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
/*
add admin parsa Pass1234!
login admin parsa Pass1234!
add restaurant sagPaz inja Persian
show restaurants
select b6589fc6
add food khoresh fesenjan 100
select menu
discount food ab0dc 50 2023/07/13  12:00:00
select menu
select food ab0dc
display ratings
display comments
logout
add user Parsaa Pass1234!
login user Parsaa Pass1234!
SEARCH RESTAURANT sagPaz
SELECT RESTAURANT b6589fc6
SELECT FOOD ab0dc
ADD NEW COMMENT
che ghazaye kiriei dari!
logout
login admin parsa Pass1234!
select b6589fc6
select menu
select food ab0dc
display comments



add admin parsa Pass1234!
login admin parsa Pass1234!
add restaurant sagPaz inja Persian
show restaurants
select b6589fc6
add food khoresh fesenjan 100
select menu
discount food ab0dc 50 2023/07/13  12:00:00
select menu
select food ab0dc
display ratings
display comments
logout
add user Parsaa Pass1234!
login user Parsaa Pass1234!
SEARCH RESTAURANT sagPaz
SELECT RESTAURANT b6589fc6
ADD NEW COMMENT
che ghazaye kiriei dari!
logout
login admin parsa Pass1234!
select b6589fc6
select menu
deselect food
display comments

 */