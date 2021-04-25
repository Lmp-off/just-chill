package com.example.illthinkaboutit;

import java.io.Serializable;

public class DBItem implements Serializable {
    private String Title;
    private String Task;
    private int Stars;
    private Author author;

    public DBItem(String title, String text, int stars) {
        this.Title = title;
        this.Task = text;
        this.Stars = stars;
        this.author = new Author(MainActivity.account.getDisplayName(),MainActivity.account.getId());
    }

    public DBItem(String title, String text) {
        this.Title = title;
        this.Task = text;
        this.author = new Author(MainActivity.account.getDisplayName(),MainActivity.account.getId());
        this.Stars =0;
    }

    public String getTitle() {
        return Title;
    }

    public String getTask() {
        return Task;
    }

    public int getStars() {
        return Stars;
    }

    public Author getAuthor() {
        return author;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public void setTask(String task) {
        this.Task = task;
    }

    public void setStars(int stars) {
        this.Stars = stars;
    }
}
