package com.example.illthinkaboutit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MyTaskActivity extends AppCompatActivity {

RecyclerView view;
    Button button;
    Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_task);
        button = findViewById(R.id.btn_right);
        button2 = findViewById(R.id.btn_left);
        view = findViewById(R.id.Myfragment);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        view.setLayoutManager(manager);
        DBManager dbManager = new DBManager();
        dbManager.setMyTasks(view);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                dbManager.setMyTasks(view);}
                catch (Exception e){
                    Toast.makeText(MyTaskActivity.this, "NoInter", Toast.LENGTH_SHORT).show();
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                dbManager.setMyStarredTasks(view);}
                catch (Exception e){
                    Toast.makeText(MyTaskActivity.this, "NoInter", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}