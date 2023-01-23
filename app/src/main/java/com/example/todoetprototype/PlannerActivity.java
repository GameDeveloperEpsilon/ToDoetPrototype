package com.example.todoet_prototype;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todoet_prototype.planner.Planner;
import com.example.todoet_prototype.planner.PlannerItem;

public class PlannerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planner);

        Planner planner = new Planner(this);

        Button addTaskBtn = findViewById(R.id.addTaskBtn);
        addTaskBtn.setOnClickListener(view -> {
            Toast.makeText(this, "Adding Task", Toast.LENGTH_SHORT).show();

            PlannerItem item = planner.makePlannerItem();
            LinearLayout taskList = findViewById(R.id.taskList);
            taskList.addView(item.getButton());

            Toast.makeText(this, item.name + " Added", Toast.LENGTH_SHORT).show();
        });
    }
}