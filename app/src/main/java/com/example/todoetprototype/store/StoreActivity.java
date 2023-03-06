package com.example.todoetprototype.store;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.TextView;

import com.example.todoetprototype.R;
import com.example.todoetprototype.inventory.UserViewModel;

public class StoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        userViewModel.getUserData().observe(this, user -> {

            TextView usercoin = findViewById(R.id.usercoin);
            usercoin.setText(String.valueOf(user.getCoins()));
        });
    }
}