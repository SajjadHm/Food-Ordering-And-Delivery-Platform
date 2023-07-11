package model;

import model.social.Rating;
import org.json.simple.JSONObject;
import view.ManagerMenu;

import java.time.LocalDateTime;

public class Read {
    public static LocalDateTime readDateTime(JSONObject object) {
        LocalDateTime localDateTime = ManagerMenu.datetimeParser((String) object.get("string"));
        return localDateTime;
    }

    public static Rating readRating(JSONObject object) {
        Rating rating = new Rating(
                (Double) object.get("rating"),
                (Integer) object.get("count"),
                (String) object.get("id")
        );
        return rating;
    }

}
