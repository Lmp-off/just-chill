package com.example.illthinkaboutit;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Date;

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
        newest = getActivity().findViewById(R.id.appCompatButtonNew);
        popular = getActivity().findViewById(R.id.appCompatButtonTopics);

        OnClick onClick = new OnClick();
        create.setOnClickListener(onClick);
        newest.setOnClickListener(onClick);
        popular.setOnClickListener(onClick);
    }

    //todo:make fragment manager
    class OnClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            DBManager dbManager = new DBManager();
            switch (v.getId()){
                case R.id.appCompatButtonNew:
                    dbManager.setTaskDataByDate();
                    break;
                case R.id.appCompatButtonTopics:
                    dbManager.setTaskDataByPopularity();
                    break;
                case R.id.btn_Create:
                    Intent intent = new Intent(getContext(), CreateTaskActivity.class);
                    startActivityForResult(intent,1);
                    //overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
            }
        }
    }
}