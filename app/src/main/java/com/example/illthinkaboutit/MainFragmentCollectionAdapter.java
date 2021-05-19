package com.example.illthinkaboutit;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MainFragmentCollectionAdapter extends FragmentPagerAdapter {

    public MainFragmentCollectionAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public Fragment getItem(int position) {
        FragmentFactory fragmentFactory =FragmentFactory.getFactory();
        return fragmentFactory.ReceiveMainFragment(position);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
