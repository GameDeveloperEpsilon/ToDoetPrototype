package com.example.todoetprototype.store;

public class StoreItem {

    private int itemID;
    private int itemPrice;
    private String itemName;
    private String itemDescription;
    private String itemCategory;


    public StoreItem(int itemID, int itemPrice, String itemName, String itemDescription, String itemCategory) {
        this.itemID = itemID;
        this.itemPrice = itemPrice;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemCategory = itemCategory;;
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

}
