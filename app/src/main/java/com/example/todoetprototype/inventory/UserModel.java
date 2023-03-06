package com.example.todoetprototype.inventory;

public class UserModel {

    private static UserModel instance = null;

    public static UserModel getInstance() {
        if (instance == null)
            instance = new UserModel();
        return instance;
    }

    private int coins;

    private UserModel() {
        coins = 0;
        System.out.println("User Model Created!");
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

}
