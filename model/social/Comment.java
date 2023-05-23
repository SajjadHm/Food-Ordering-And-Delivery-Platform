package model.social;

import java.util.ArrayList;

public class Comment {
    private String message;
    private final String id;
    private int rating;
    private final ArrayList<Comment> replies;

    {
        replies = new ArrayList<>();
    }

    public Comment(String message, String id, int rating, ArrayList<Comment> replies) {
        this.message = message;
        this.id = id;
        this.rating = rating;
        for (Comment comment : replies) {
            this.replies.add(comment);
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public ArrayList<Comment> getReplies() {
        return replies;
    }

}
