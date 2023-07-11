package model.social;

import model.accounts.Account;
import view.others.Colors;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Comment {
    private String message;
    private final String id;
    private int rating;
    private final ArrayList<Comment> replies;
    private Account author;
    private final LocalDateTime timeCreated;
    private boolean isModified;

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
        this.timeCreated = LocalDateTime.now();
        this.isModified = false;
    }

    public Comment(String message, String id, int rating, ArrayList<Comment> replie, LocalDateTime timeCreated) {
        this.message = message;
        this.id = id;
        this.rating = rating;
        for (Comment comment : replies) {
            this.replies.add(comment);
        }
        this.timeCreated = timeCreated;
        this.isModified = false;
    }

    public Comment(String message, String id, Account author )
    {
        this.message = message;
        this.id = id;
        this.timeCreated = LocalDateTime.now();
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        this.isModified = true;
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

    public Account getAuthor() {
        return author;
    }

    public void setAuthor(Account author) {
        this.author = author;
    }

    public String toString() {
        String self = "";
        self += Colors.UNDERLINE + "#" + id + Colors.RESET + "\n";
        self += "@" + Colors.BOLD + author.getUserName() + Colors.RESET + "   at " + timeCreated.toString();
        if (isModified) self += " (Modified)";
        self += "\n" + "\t" + this.message;
        if (replies.size() != 0) {
            for (Comment reply : replies) {
                self += "\t\t---- " + Colors.UNDERLINE + "#" + reply.id + Colors.RESET + "\n";
                self += "\t\t     reply from " + "@" + Colors.BOLD + reply.author.getUserName() + Colors.RESET + "   at " + reply.timeCreated.toString() + "\n";
                self += "\t\t     " + reply.message + "\n";
            }
        }
        return self;
    }

}
