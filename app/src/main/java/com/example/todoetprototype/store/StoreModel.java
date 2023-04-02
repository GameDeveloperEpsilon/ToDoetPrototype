package com.example.todoetprototype.store;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class StoreModel extends AppCompatActivity {

    public static StoreModel instance = null;

    public static StoreModel getInstance() {
        if (instance == null)
            instance = new StoreModel();
        return instance;
    }

    private final List<StoreItem> catalog = new ArrayList<>();

    private StoreModel() {
        // Add dummy data
        catalog.add(new StoreItem(1, 5, "Test Item", "Some description", "Category A"));
        catalog.add(new StoreItem(2, 10, "Toothbrush", "A brush for cleaning teeth", "Category B"));
        catalog.add(new StoreItem(3, 4, "todofood", "Delicious food for pets", "FOOD"));  // FOOD
        catalog.add(new StoreItem(4, 3, "NA","NA","POTION"));  // POTION
        catalog.add(new StoreItem(5, 3, "NA","NA","MEDICINE"));  // MEDICINE

    }

    //Getters and Setters

    public List<StoreItem> getCatalog() {
        return catalog;
    }
}



