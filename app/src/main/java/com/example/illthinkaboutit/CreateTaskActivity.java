package com.example.illthinkaboutit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateTaskActivity extends AppCompatActivity {
    //Todo: save instance if activity closed suddenly
    EditText Title;
    EditText Task;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);
        Title = findViewById(R.id.tv_title);
        Task = findViewById(R.id.tv_task);
        button = findViewById(R.id.button6);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title=Title.getText().toString();
                String text=Task.getText().toString();

                DBItem item = new DBItem(title,text);
                DBManager.add(item);

                finish();
                overridePendingTransition(R.anim.slide_in_bottom,R.anim.slide_out_bottom);
            }
        });
    }
}