package com.example.illthinkaboutit;

import java.util.Comparator;

public class ViewComparatorByStars implements Comparator<ViewItem> {

    @Override
    public int compare(ViewItem o1, ViewItem o2) {
        return o2.getStars()-o1.getStars();
    }
}
