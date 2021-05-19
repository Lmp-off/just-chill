package com.example.illthinkaboutit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ShowTaskActivity extends AppCompatActivity {
    TextView title;
    TextView task;
    Button back;
    Button delete;
    Item item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_task);
        Intent intent = getIntent();
        title = findViewById(R.id.tv_showtitle);
        task = findViewById(R.id.tv_showtask);
        back = findViewById(R.id.btn_back);
        delete = findViewById(R.id.btn_remove);
        try {
            item= (Item) intent.getExtras().get("Item");
            title.setText(item.getTitle());
            task.setText(item.getText());
            delete.setEnabled(item.getAuthor().getId().equals(MainActivity.getAccountId()));
        }
        catch (Exception e){
            e.printStackTrace();
            finish();
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialogFragment dialogFragment = new CustomDialogFragment(item,MainActivity.getAccountId());
                dialogFragment.show(getSupportFragmentManager(),"TAH");
            }
        });
    }
}