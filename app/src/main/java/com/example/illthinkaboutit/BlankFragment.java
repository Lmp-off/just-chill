package com.example.illthinkaboutit;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class BlankFragment extends Fragment {
    Button popular;
    Button newest;
    Button create;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        create = getActivity().findViewById(R.id.btn_Create);
        create.setOnClickListener(new OnClick());
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
                case R.id.btn_Create:
                    Intent intent = new Intent(getContext(), CreateTaskActivity.class);
                    startActivity(intent);
            }
        }
    }
}