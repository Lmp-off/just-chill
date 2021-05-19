package com.example.illthinkaboutit;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Item implements Serializable {
    private String id;
    private final String title;
    private final String text;
    private boolean isStared;
    private int numberOfStars;
    private final Author author;
    public Item(@NonNull String id, String title, String text, boolean stared, int stars, Author author) {
        this.title = title;
        this.text = text;
        this.isStared = stared;
        this.numberOfStars = stars;
        this.author = author;
        this.id=id;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public boolean isStared() {
        return isStared;
    }

    public int getNumberOfStars() {
        return numberOfStars;
    }

    public Author getAuthor() {
        return author;
    }

    public void setStared(boolean stared) {
        this.isStared = stared;
    }

    public void setNumberOfStars(int numberOfStars) {
        this.numberOfStars = numberOfStars;
    }

}
