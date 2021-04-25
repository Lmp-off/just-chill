package com.example.illthinkaboutit;

import java.util.Comparator;

public class ViewComparatorByStars implements Comparator<Item> {

    @Override
    public int compare(Item o1, Item o2) {
        return o2.getStars()-o1.getStars();
    }
}
