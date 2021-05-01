package com.example.illthinkaboutit;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
/*
* Singleton
*/
public class FragmentFactory {
    private static FragmentFactory factory;
    private static ArrayList<Item> items;
    private static RvFragment fragment1;
    private static Fragment fragment0;
    private FragmentFactory(){
        fragment0=new BlankFragment();
        fragment1=new RvFragment();
    }
    public static FragmentFactory getFactory(){
        if (factory==null){
            factory=new FragmentFactory();
            return factory;
        }
        else return factory;
    }
    public Fragment create(int number) {
        switch (number) {
            case 0:
                return new BlankFragment();
            default:
                return fragment1;
        }
    }
    public void setContent(ArrayList<Item> adapter){
         fragment1.setAdapter(adapter);
    }
}
