package com.example.todoetprototype.inventory;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoetprototype.Nexus;
import com.example.todoetprototype.R;
import com.example.todoetprototype.adapter.InventoryItemAdapter;
import com.example.todoetprototype.databinding.ActivityInventoryBinding;
import com.example.todoetprototype.pet.PetActivity;
import com.example.todoetprototype.planner.PlannerActivity;
import com.example.todoetprototype.store.StoreActivity;
import com.example.todoetprototype.utils.DatabaseHandler;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class InventoryActivity extends AppCompatActivity {

    private ActivityInventoryBinding binding;
    private UserViewModel userViewModel;
    private RecyclerView inventoryItemsView;
    private InventoryItemAdapter inventoryItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInventoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        userViewModel.getUserData().observe(this, user -> {

            TextView coinsDisplay = findViewById(R.id.coinsDisplay);
            coinsDisplay.setText(String.valueOf(user.getCoins()));

            inventoryItemAdapter.setInventoryItemList(user.getInventory());

        });

        inventoryItemsView = findViewById(R.id.inventoryItemsRecyclerView);
        inventoryItemsView.setLayoutManager(new LinearLayoutManager(this));
        inventoryItemAdapter = new InventoryItemAdapter(new DatabaseHandler(this), this);
        inventoryItemsView.setAdapter(inventoryItemAdapter);

        // Navigation bar

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.getMenu().findItem(R.id.user_nav_item).setChecked(true);

        BottomNavigationItemView todoListNavItem = bottomNavigationView.findViewById(R.id.todo_list_nav_item);
        todoListNavItem.setOnClickListener(v -> {
            Intent changeActivities = new Intent(this, PlannerActivity.class);
            startActivity(changeActivities);
        });

        BottomNavigationItemView userNavItem = bottomNavigationView.findViewById(R.id.user_nav_item);
        userNavItem.setOnClickListener(v -> {
            Intent changeActivities = new Intent(this, InventoryActivity.class);
            startActivity(changeActivities);
        });

        BottomNavigationItemView homeNavItem = bottomNavigationView.findViewById(R.id.home_nav_item);
        homeNavItem.setOnClickListener(v -> {
            Intent changeActivities = new Intent(this, Nexus.class);
            startActivity(changeActivities);
        });

        BottomNavigationItemView petsNavItem = bottomNavigationView.findViewById(R.id.pets_nav_item);
        petsNavItem.setOnClickListener(v -> {
            Intent changeActivities = new Intent(this, PetActivity.class);
            startActivity(changeActivities);
        });

        BottomNavigationItemView storeNavItem = bottomNavigationView.findViewById(R.id.store_nav_item);
        storeNavItem.setOnClickListener(v -> {
            Intent changeActivities = new Intent(this, StoreActivity.class);
            startActivity(changeActivities);
        });
    }

    public UserViewModel getUserViewModel() {
        return userViewModel;
    }
}