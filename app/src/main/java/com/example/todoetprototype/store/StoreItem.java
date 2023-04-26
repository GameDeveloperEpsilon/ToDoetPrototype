package com.example.todoetprototype.store;

import com.example.todoetprototype.R;

public class StoreItem {

    private int itemID;
    private int itemPrice;
    private String itemName;
    private String itemDescription;
    private String itemCategory;
    private int drawable;

    private int effectMultiplier;


    public StoreItem(int itemID, int itemPrice, String itemName, String itemDescription, String itemCategory, int effectMultiplier) {
        this.itemID = itemID;
        this.itemPrice = itemPrice;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemCategory = itemCategory;
        this.drawable = R.drawable.berryimage;
       // this.drawable = R.drawable.soap;
        //this.drawable = R.drawable.brush;
        this.effectMultiplier = effectMultiplier;
    }

    //Getters and Setters
    //

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public int getDrawable() {
        return drawable;
    }

    public int getEffectMultiplier() {
        return effectMultiplier;
    }
}
