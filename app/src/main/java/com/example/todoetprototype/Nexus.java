package com.example.todoetprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.todoetprototype.inventory.InventoryActivity;
import com.example.todoetprototype.pet.PetActivity;
import com.example.todoetprototype.planner.PlannerActivity;
import com.example.todoetprototype.store.StoreActivity;
import com.example.todoetprototype.store.StoreItem;

public class Nexus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nexus);

        // Navigate to the pet page
        Button petPageBtn = findViewById(R.id.petPageBtn);
        petPageBtn.setOnClickListener(view -> {
            Toast.makeText(this, "Changing To Pet Activity", Toast.LENGTH_SHORT).show();
            Intent changeActivities = new Intent(this, PetActivity.class);
            startActivity(changeActivities);
        });

        // Navigate to the planner page
        Button plannerPageBtn = findViewById(R.id.plannerPageBtn);
        plannerPageBtn.setOnClickListener(view -> {
            Toast.makeText(this, "Changing To Planner Activity", Toast.LENGTH_SHORT).show();
            Intent changeActivities = new Intent(this, PlannerActivity.class);
            startActivity(changeActivities);
        });

        // Navigate to the Inventory page
        Button inventoryPageBtn = findViewById(R.id.inventoryPageBtn);
        inventoryPageBtn.setOnClickListener(view -> {
            Toast.makeText(this, "Changing To Inventory Activity", Toast.LENGTH_SHORT).show();
            Intent changeActivities = new Intent(this, InventoryActivity.class);
            startActivity(changeActivities);
        });

        // Navigate to the Store page
        Button storePageBtn = findViewById(R.id.storePageBtn);
        storePageBtn.setOnClickListener(view -> {
            Toast.makeText(this, "Changing To Store Activity", Toast.LENGTH_SHORT).show();
            Intent changeActivities = new Intent(this, StoreActivity.class);
            startActivity(changeActivities);
        });
    }
}