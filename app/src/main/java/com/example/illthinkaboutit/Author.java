package com.example.illthinkaboutit;

import java.io.Serializable;

//Todo:Remove class use google account instead this
public class Author implements Serializable {
    private String name;
    private String id;

    public Author(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
