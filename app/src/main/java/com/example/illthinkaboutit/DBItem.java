package com.example.illthinkaboutit;

import java.io.Serializable;

public class DBItem implements Serializable {
    private String Title;
    private String Task;
    private int Stars;
    private Author author;
    private long created_date;

    public DBItem(String title, String text,long date) {
        this.Title = title;
        this.Task = text;
        this.created_date =date;
        this.author = new Author(MainActivity.getDisplayedName(),MainActivity.getAccountId());
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

    public long getCreated_date() {
        return created_date;
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
