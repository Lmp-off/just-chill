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
    Button myTasks;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        create = getActivity().findViewById(R.id.btn_Create);
        newest = getActivity().findViewById(R.id.appCompatButtonNew);
        popular = getActivity().findViewById(R.id.appCompatButtonTopics);
        myTasks = getActivity().findViewById(R.id.btn_MyTasks);

        OnClick onClick = new OnClick();
        create.setOnClickListener(onClick);
        newest.setOnClickListener(onClick);
        popular.setOnClickListener(onClick);
        myTasks.setOnClickListener(onClick);
    }

    //todo:make fragment manager
    class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            DBManager dbManager = new DBManager();
            switch (v.getId()) {
                case R.id.appCompatButtonNew:
                    dbManager.setTaskDataByDate();
                    break;
                case R.id.appCompatButtonTopics:
                    dbManager.setTaskDataByPopularity();
                    break;
                case R.id.btn_Create:
                    Intent intent = new Intent(getContext(), CreateTaskActivity.class);
                    startActivityForResult(intent, 1);
                    return;
                case R.id.btn_MyTasks:
                    Intent intenta = new Intent(getContext(), MyTaskActivity.class);
                    startActivity(intenta);
                    return;
            }
            MainActivity.showTaskPage();
        }
    }
}