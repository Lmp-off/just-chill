package com.example.illthinkaboutit;

import java.util.Comparator;

public class ViewComparatorByAlphabet implements Comparator<ViewItem> {

    @Override
    public int compare(ViewItem o1, ViewItem o2) {
        return o2.getTitle().compareTo(o2.getTitle());
    }
}
