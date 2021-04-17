package com.example.illthinkaboutit;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class BlankFragment extends Fragment {
    Button popular;
    Button newest;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }
    //todo:make fragment manager
    class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.appCompatButtonNew:
                    //Fm.setAdapter(Fm.NEW)
                case R.id.appCompatButtonTopics:
                    //Fm.setAdapter(Fm.NEW)
            }
        }
    }
}