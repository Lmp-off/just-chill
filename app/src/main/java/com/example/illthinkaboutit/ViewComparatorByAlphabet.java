package com.example.illthinkaboutit;

import java.util.Comparator;

public class ViewComparatorByAlphabet implements Comparator<Item> {

    @Override
    public int compare(Item o1, Item o2) {
        return o2.getTitle().compareTo(o2.getTitle());
    }
}
