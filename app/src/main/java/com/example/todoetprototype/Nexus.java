package com.example.todoetprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class Nexus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nexus);

        Button petPageBtn = findViewById(R.id.petPageBtn);
        petPageBtn.setOnClickListener(view -> {
            Toast.makeText(this, "Changing To Pet Activity", Toast.LENGTH_SHORT).show();
            Intent changeActivities = new Intent(this, PetActivity.class);
            startActivity(changeActivities);
        });

        Button plannerPageBtn = findViewById(R.id.plannerPageBtn);
        plannerPageBtn.setOnClickListener(view -> {
            Toast.makeText(this, "Changing To Planner Activity", Toast.LENGTH_SHORT).show();
            Intent changeActivities = new Intent(this, PlannerActivity.class);
            startActivity(changeActivities);
        });

        Button inventoryPageBtn = findViewById(R.id.inventoryPageBtn);
        inventoryPageBtn.setOnClickListener(view -> {
            Toast.makeText(this, "Changing To Inventory Activity", Toast.LENGTH_SHORT).show();
            Intent changeActivities = new Intent(this, InventoryActivity.class);
            startActivity(changeActivities);
        });

        Button storePageBtn = findViewById(R.id.storePageBtn);
        storePageBtn.setOnClickListener(view -> {
            Toast.makeText(this, "Changing To Store Activity", Toast.LENGTH_SHORT).show();
            Intent changeActivities = new Intent(this, StoreActivity.class);
            startActivity(changeActivities);
        });
    }
}