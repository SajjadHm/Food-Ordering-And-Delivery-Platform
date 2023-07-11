package model;

import model.social.Rating;
import org.json.simple.JSONObject;

public class Save {
    public static JSONObject saveRating(Rating rating) {
        JSONObject object = new JSONObject();
        object.put("rating", rating.getRating());
        object.put("count", rating.getCount());
        object.put("id", rating.getId());
        return object;
    }

}
