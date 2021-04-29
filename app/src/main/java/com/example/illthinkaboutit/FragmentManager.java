package com.example.illthinkaboutit;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class FragmentManager {
    RvFragment fragment;

    public FragmentManager(RvFragment fragment) {
        this.fragment = fragment;
    }
    public void setAdapter(ArrayList<Item> items){

    }
    public void getNewest(){
        DBManager manager = new DBManager();
       // manager.getAllTasksData(fragment);
       // this.adapter=new RvAdapter(items.size(),items);
    }
}
