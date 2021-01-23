package com.example.illthinkaboutit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button button;
    FloatingActionButton floatButton;
    ListView listView;
    public static final String CircleAction="com.example.illthinkaboutit.Show Circle";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<String> arrayList = new ArrayList<>();
        floatButton =findViewById(R.id.floatingActionButton2);
        floatButton.setOnClickListener(view -> {
            arrayList.add(String.valueOf(arrayList.size()+1));
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);
            listView.setAdapter(arrayAdapter);
            listView.scrollListBy(12);
            listView.deferNotifyDataSetChanged();
        });
        listView = findViewById(R.id.View);
        button = findViewById(R.id.previewCircle);


    }
}