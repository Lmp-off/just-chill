package com.example.illthinkaboutit;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class FragmentFactory {
    static ArrayList<Item> items;
    static Fragment fragment;
    static Fragment fragment2;



    public Fragment create(int number) {
        fragment = new RvFragment();
        switch (number) {
            case 0:
                return new BlankFragment();
            default:
                return new RvFragment();

        }
    }

   /* static ArrayList<Item> items() {
        ArrayList<Item> items = new ArrayList<>();
        Author author = new Author("Ronald", "1");
        items.add(new Item(1, "title 1", "", false, 25000, author));
        items.add(new Item(2, "title 2", "", false, 21, author));
        items.add(new Item(3, "title 3", "", false, 2500, author));
        items.add(new Item(4, "title 4", "", false, 5000, author));
        items.add(new Item(5, "title 5", "", false, 0, author));
        items.add(new Item(6, "title 6", "", false, 1, author));
        items.add(new Item(7, "title 7", "", false, 3, author));
        items.add(new Item(8, "title 8", "", false, 14, author));
        items.add(new Item(9, "title 9", "", false, 1512, author));
        items.add(new Item(10, "title 10", "", false, 5112, author));
        items.add(new Item(11, "title 11", "", false, 12, author));
        items.add(new Item(12, "title 12", "", false, 0, author));
        items.add(new Item(1, "title 1", "", false, 25000, author));
        items.add(new Item(2, "title 2", "", false, 21, author));
        items.add(new Item(3, "title 3", "", false, 2500, author));
        items.add(new Item(4, "title 4", "", false, 5000, author));
        items.add(new Item(5, "title 5", "", false, 0, author));
        items.add(new Item(6, "title 6", "", false, 1, author));
        items.add(new Item(7, "title 7", "", false, 3, author));
        items.add(new Item(8, "title 8", "", false, 14, author));
        items.add(new Item(9, "title 9", "", false, 1512, author));
        items.add(new Item(10, "title 10", "", false, 5112, author));
        items.add(new Item(11, "title 11", "", false, 12, author));
        items.add(new Item(12, "title 12", "", false, 0, author));
        return items;
    }*/
}
