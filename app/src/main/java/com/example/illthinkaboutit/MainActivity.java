package com.example.illthinkaboutit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button button;
    ListView listView;
    public static final String CircleAction="com.example.illthinkaboutit.Show Circle";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button =findViewById(R.id.button);
        button.setOnClickListener(view -> {
            Toast.makeText(this,"S",Toast.LENGTH_SHORT).show();
        });
        ArrayList<String> arrayList = new ArrayList<>();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);
        listView = findViewById(R.id.View);
        listView.setAdapter(arrayAdapter);
        button = findViewById(R.id.previewCircle);


    }
}