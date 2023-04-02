package com.example.todoetprototype.store;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoetprototype.R;
import com.example.todoetprototype.adapter.StoreItemAdapter;
import com.example.todoetprototype.databinding.ActivityStoreBinding;
import com.example.todoetprototype.inventory.UserModel;
import com.example.todoetprototype.utils.DatabaseHandler;

public class StoreActivity extends AppCompatActivity {

    private ActivityStoreBinding binding;
    private StoreViewModel storeViewModel;
    private RecyclerView storeItemsView;
    private StoreItemAdapter storeItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStoreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        storeViewModel = new ViewModelProvider(this).get(StoreViewModel.class);

        storeViewModel.getStoreData().observe(this, storeModel -> {

            storeItemAdapter.setStoreItemList(storeModel.getCatalog());

            // Get user coins from UserModel
            TextView userCoin = findViewById(R.id.userCoin);
            String coinDisplayFormatted = "Current coins: " + UserModel.getInstance().getCoins();
            userCoin.setText(coinDisplayFormatted);
        });

        storeItemsView = findViewById(R.id.storeItemsRecyclerView);
        storeItemsView.setLayoutManager(new LinearLayoutManager(this));
        storeItemAdapter = new StoreItemAdapter(new DatabaseHandler(this), this);
        storeItemsView.setAdapter(storeItemAdapter);

        // Test adding some items
        storeItemAdapter.setStoreItemList(StoreModel.getInstance().getCatalog());

    }

    public StoreViewModel getStoreViewModel() {
        return storeViewModel;
    }
}