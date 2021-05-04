package com.example.illthinkaboutit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ShowTaskActivity extends AppCompatActivity {
    TextView title;
    TextView task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_task);
        Intent intent = getIntent();
        title = findViewById(R.id.tv_showtitle);
        task = findViewById(R.id.tv_showtask);
        try {
            Item item= (Item) intent.getExtras().get("Item");
            title.setText(item.getTitle());
            task.setText(item.getText());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}