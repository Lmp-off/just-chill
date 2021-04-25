package com.example.illthinkaboutit;

import androidx.annotation.NonNull;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Comparator;

public class Item {
    private int id;
    private final String title;
    private final String text;
    private boolean stared;
    private int stars;
    private final Author author;
    public Item(@NonNull int id, String title, String text, boolean stared, int stars, Author author) {
        this.title = title;
        this.text = text;
        this.stared = stared;
        this.stars = stars;
        this.author = author;
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public boolean isStared() {
        return stared;
    }

    public int getStars() {
        return stars;
    }

    public Author getAuthor() {
        return author;
    }

    public void setStared(boolean stared) {
        this.stared = stared;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

}
