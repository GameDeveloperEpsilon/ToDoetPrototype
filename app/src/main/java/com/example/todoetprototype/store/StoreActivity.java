package com.example.todoetprototype.store;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoetprototype.R;
import com.example.todoetprototype.adapter.StoreItemAdapter;
import com.example.todoetprototype.inventory.UserViewModel;
import com.example.todoetprototype.utils.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

public class StoreActivity extends AppCompatActivity {

    private RecyclerView storeItemsView;
    private StoreItemAdapter storeItemAdapter;
    private List<StoreItem> storeItemList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        userViewModel.getUserData().observe(this, user -> {

            TextView userCoin = findViewById(R.id.userCoin);
            String coinDisplayFormatted = "Current coins: " + user.getCoins();
            userCoin.setText(coinDisplayFormatted);
        });

        storeItemsView = findViewById(R.id.storeItemsRecyclerView);
        storeItemsView.setLayoutManager(new LinearLayoutManager(this));
        storeItemAdapter = new StoreItemAdapter(new DatabaseHandler(this), this);
        storeItemsView.setAdapter(storeItemAdapter);

        // Test adding some items
        storeItemList = new ArrayList<>();
        storeItemAdapter.setStoreItemList(StoreModel.getInstance().getCatalog());

    }
}