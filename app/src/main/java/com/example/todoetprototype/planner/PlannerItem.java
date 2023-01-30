package com.example.todoetprototype.planner;

import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PlannerItem {

    public final String name;
    private final Button button;

    public PlannerItem(AppCompatActivity context, String itemName) {
        name = itemName;

        // Make new button for task
        button = new Button(context);
        button.setText(name);
        button.setOnClickListener(view ->
                Toast.makeText(context, name + " Completed!", Toast.LENGTH_SHORT).show());
    }

    public Button getButton() {
        return button;
    }

}
