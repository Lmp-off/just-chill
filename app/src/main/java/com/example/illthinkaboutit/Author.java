package com.example.illthinkaboutit;

import java.io.Serializable;
import java.util.Map;

//Todo:Remove class use google account instead this
public class Author implements Serializable {
    private String name;
    private String id;

    public Author(String name, String id) {
        this.name = name;
        this.id = id;
    }
    public Author(Map<String,Object> map) {
        this.name = map.get("name").toString();
        this.id = map.get("id").toString();
    }
    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
