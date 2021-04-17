package com.example.illthinkaboutit;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class RvFragment extends Fragment {
    Adapter adapter;
    RecyclerView topic;
    LinearLayoutManager manager;
    public RvFragment() {
        this.adapter=new RvAdapter(24,FragmentFactory.items());
        manager  = new LinearLayoutManager(getContext());
    }

    public RvFragment(Adapter adapter){
        this.adapter=adapter;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_rv, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        topic =getActivity().findViewById(R.id.Topics);
        topic.setLayoutManager(manager);
        topic.setHasFixedSize(true);
        topic.setAdapter(this.adapter);
    }
    public void setAdapter(Adapter adapter){
        this.adapter=adapter;
        topic.setAdapter(adapter);
    }

}