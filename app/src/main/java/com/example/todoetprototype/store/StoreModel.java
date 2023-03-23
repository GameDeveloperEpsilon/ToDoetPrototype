package com.example.todoetprototype.store;

import androidx.appcompat.app.AppCompatActivity;

public class StoreModel extends AppCompatActivity {

    private int itemID;
    private int itemPrice;
    private String itemName;
    private String itemDescription;
    private String itemCategory;


    public StoreModel(int itemID, int itemPrice, String itemName, String itemDescription, String itemCategory) {
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


    // store items


    public enum StoreItem {
        FOOD(1, 4, "todofood", "Delicious food for pets", "FOOD"),
        POTION(2, 3, "NA","NA","POTION"),
        MEDICINE(3, 3, "NA","NA","MEDICINE");

        private final int itemID;
        private final int itemprice;
        private final String itemname;
        private final String itemdescription;
        private final String itemcategory;

        StoreItem(int itemID, int itemprice, String itemname, String itemdescription, String itemcategory) {

            this.itemID = itemID;
            this.itemprice = itemprice;
            this.itemname = itemname;
            this.itemdescription = itemdescription;
            this.itemcategory = itemcategory;
        }
    }


}



