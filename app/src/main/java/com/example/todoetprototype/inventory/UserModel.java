package com.example.todoetprototype.inventory;

import com.example.todoetprototype.store.StoreItem;

import java.util.ArrayList;
import java.util.List;

public class UserModel {

    private static UserModel instance = null;

    public static UserModel getInstance() {
        if (instance == null)
            instance = new UserModel();
        return instance;
    }

    private int coins;
    private final List<StoreItem> inventory = new ArrayList<>();

    private UserModel() {
        coins = 100;
        System.out.println("User Model Created!");
    }

    public int getCoins() {
        return coins;
    }
    public void setCoins(int coins) {
        this.coins = coins;
    }

    public List<StoreItem> getInventory() {
        return inventory;
    }
    public void addItemToInventory(StoreItem newItem) {
        inventory.add(newItem);
    }
    public void removeItemFromInventory(StoreItem itemToRemove) {
        inventory.remove(itemToRemove);
    }
}
