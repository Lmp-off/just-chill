package com.example.illthinkaboutit;

import android.util.Log;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
/*
* Singleton
*/
public class FragmentFactory {
    private static FragmentFactory factory;
    private static RvFragment fragment1;
    private static Fragment fragment0;
    private static DBManager manager;
    
    private FragmentFactory(){
        fragment0=new BlankFragment();
        fragment1=new RvFragment();
        manager= new DBManager();
    }

    public static FragmentFactory getFactory(){
        if (factory==null){
            factory=new FragmentFactory();
            return factory;
        }
        else return factory;
    }

    public Fragment ReceiveMainFragment(int number) {
        switch (number) {
            case 0:
                fragment0 = new BlankFragment();
                return fragment0;
            default:
                fragment1 = new RvFragment();
                return fragment1;
        }
    }

    public void setMainFragmentContent(ArrayList<Item> adapter){
         fragment1.setAdapter(adapter);
    }
}
