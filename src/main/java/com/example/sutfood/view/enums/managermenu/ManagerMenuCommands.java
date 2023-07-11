package com.example.sutfood.view.enums.managermenu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ManagerMenuCommands {
    ADD_RESTAURANT("ADD\\s+RESTAURANT\\s+(?<name>\\w+)\\s+(?<location>\\w+)\\s+(?<foodType>[\\w\\s]+)"),
    REMOVE_RESTAURANT("REMOVE\\s+RESTAURANT\\s+(?<id>\\w+)"),
    SELECT_RESTAURANT("SELECT\\s+(?<id>\\w+)"),
    SHOW_RESTAURANTS("SHOW\\s+RESTAURANTS"),
    EDIT_FOODTYPES("EDIT\\s+FOODTYPE\\s+(?<foodType>[\\w\\s]+)"),
    JA("(Y)|(y)|(Yes)"),
    NEIN("(N)|(n)|(No)"),
    LOGOUT("LOGOUT"),
    CLEAR_MENU("CLEAR\\s+MENU"),
    SELECT_MENU("SELECT\\s+MENU"),
    ADD_FOOD("ADD\\s+FOOD\\s+(?<foodName>[\\w\\s]+)\\s+(?<price>\\d+)"),
    EDIT_FOOD("EDIT\\s+FOOD\\s+(?<foodID>\\w+)\\s+(?<field>\\w+)\\s+(?<newFieldValue>\\w+)"),
    DELETE_FOOD("DELETE\\s+FOOD\\s+(?<foodID>\\w+)"),
    DEACTIVE_FOOD("DEACTIVATE\\s+foFOODod\\s+(?<foodID>\\w+)"),
    ACTIVE_FOOD("ACTIVE\\s+FOOD\\s+(?<foodID>\\w+)"),
    DISCOUNT_FOOD("DISCOUNT\\s+FOOD\\s+(?<foodID>\\w+)\\s+(?<percent>\\d+\\.?\\d+?)\\s+(?<year>\\d{4})\\/(?<month>\\d{2})\\/(?<day>\\d{2})\\s{2}(?<hour>\\d{2}):(?<minute>\\d{2}):(?<second>\\d{2})"),
    SELECT_FOOD("SELECT\\s+FOOD\\s+(?<foodID>\\w+)"),
    DESELECT_FOOD("DESELECT\\s+FOOD"),
    DISPLAY_RATINGS("DISPLAY\\s+RATINGS"),
    DISPLAY_COMMENTS("DISPLAY\\s+COMMENTS"),
    ADD_NEW_RESPONSE("ADD\\s+NEW\\s+RESPONSE\\s+(?<commentID>\\w+)\\s+(?<message>[^\n]+)"),
    DISPLAY_OPEN_ORDERS("DISPLAY\\s+OPEN\\s+ORDERS"),
    EDIT_ORDER("EDIT\\s+ORDER\\s+(?<orderID>\\w+)"),
    SHOW_ORDER_HISTORY("SHOW\\s+ORDER\\s+HISTORY"),
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