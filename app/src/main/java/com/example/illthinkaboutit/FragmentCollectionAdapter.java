package com.example.illthinkaboutit;

import android.os.Build;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

public class FragmentCollectionAdapter extends FragmentPagerAdapter {

    public FragmentCollectionAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public FragmentCollectionAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public Fragment getItem(int position) {
        FragmentFactory fragmentFactory =FragmentFactory.getFactory();
        return fragmentFactory.create(position);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
