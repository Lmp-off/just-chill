package com.example.illthinkaboutit;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class RvFragment extends Fragment {
    Adapter adapter;
    RecyclerView topic;
    LinearLayoutManager Lmanager;
    DBManager manager= new DBManager();
    public RvFragment() {
        Lmanager  = new LinearLayoutManager(getContext());
    }

    public RvFragment(ArrayList<Item> items){
        Lmanager  = new LinearLayoutManager(getContext());
        this.adapter=new RvAdapter(items.size(),items);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_rv, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        topic = getActivity().findViewById(R.id.Topics);
       //manager.getAllTasksData(this);
    }

    @Override
    public void onStart() {
        super.onStart();
       //manager.getAllTasksData(this);

        topic.setLayoutManager(Lmanager);
        topic.setHasFixedSize(true);
        topic.setAdapter(this.adapter);
        if(adapter==null) {
            DBInitThread thread = new DBInitThread(this);
            thread.start();
        }
    }
    //
    public void setAdapter(ArrayList<Item> items){
        this.adapter=new RvAdapter(items.size(),items);
        topic.setAdapter(adapter);
    }

}