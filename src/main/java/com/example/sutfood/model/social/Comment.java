package com.example.sutfood.model.social;

import com.example.sutfood.model.Memory;
import com.example.sutfood.model.accounts.Account;
import com.example.sutfood.model.accounts.User;
import com.example.sutfood.view.others.Colors;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Comment {
    private String message;
    private final String id;
    private int rating;
    private final ArrayList<Comment> replies;
    private String authorID;
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

    public Comment(String message, String id, int rating, ArrayList<Comment> replies, LocalDateTime timeCreated) {
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
        this.authorID = author.getUserName();
    }

    public Comment(String message, String id, String authorID, LocalDateTime timeCreated, int rating, boolean isModified )
    {
        this.message = message;
        this.id = id;
        this.timeCreated = timeCreated;
        this.authorID = authorID;
        this.rating = rating;
        this.isModified = isModified;
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

    public String getAuthorID() {
        return authorID;
    }

    public Account getAuthor() {
        Account author = Memory.getUser(authorID);
        if (author == null) author = Memory.getAdmin(authorID);
        return author;
    }

    public void setAuthor(Account author) {
        this.authorID = author.getUserName();
    }

    public String toString() {
        Account author = Memory.getUser(authorID);
        if (author == null) author = Memory.getAdmin(authorID);
        String self = "";
        self += Colors.UNDERLINE + "#" + id + Colors.RESET + "\n";
        self += "@" + Colors.BOLD + author.getUserName() + Colors.RESET + "   at " + timeCreated.format(Memory.dateTimeFormatter);
        if (isModified) self += " (Modified)";
        self += "\n" + "\t" + this.message + "\n";
        if (replies.size() != 0) {
            for (Comment reply : replies) {
                self += "\t\t---- " + Colors.UNDERLINE + "#" + reply.id + Colors.RESET + "\n";
                self += "\t\t     reply from " + "@" + Colors.BOLD + reply.getAuthor().getUserName() + Colors.RESET + "   at " + reply.timeCreated.format(Memory.dateTimeFormatter) + "\n";
                self += "\t\t     " + reply.message + "\n";
            }
        }
        return self;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public boolean isModified() {
        return isModified;
    }
}
