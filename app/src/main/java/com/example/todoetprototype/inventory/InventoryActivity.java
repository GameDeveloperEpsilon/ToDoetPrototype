package com.example.todoetprototype.inventory;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.todoetprototype.R;
import com.example.todoetprototype.databinding.ActivityInventoryBinding;

public class InventoryActivity extends AppCompatActivity {

    private ActivityInventoryBinding binding;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInventoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //TextView coinsDisplay = findViewById(R.id.coinsDisplay);
        //coinsDisplay.setText("51");

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        userViewModel.getUserData().observe(this, user -> {

            TextView coinsDisplay = findViewById(R.id.coinsDisplay);
            coinsDisplay.setText(String.valueOf(user.getCoins()));
        });

        //userViewModel.init();
    }
}