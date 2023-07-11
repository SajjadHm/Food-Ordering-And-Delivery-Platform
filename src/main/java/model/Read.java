package model;

import model.social.Rating;
import org.json.simple.JSONObject;

public class Read {
    public static Rating readRating(JSONObject object) {
        Rating rating = new Rating(
                (Double) object.get("rating"),
                (Integer) object.get("count"),
                (String) object.get("id")
        );
        return rating;
    }

}
