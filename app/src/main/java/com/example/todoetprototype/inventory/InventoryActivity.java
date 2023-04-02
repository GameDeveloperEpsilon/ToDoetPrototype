package com.example.todoetprototype.inventory;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoetprototype.R;
import com.example.todoetprototype.adapter.InventoryItemAdapter;
import com.example.todoetprototype.databinding.ActivityInventoryBinding;
import com.example.todoetprototype.utils.DatabaseHandler;

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
    }

    public UserViewModel getUserViewModel() {
        return userViewModel;
    }
}