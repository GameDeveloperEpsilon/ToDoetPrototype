package com.example.todoetprototype.store;

import androidx.appcompat.app.AppCompatActivity;

public class StoreItem extends AppCompatActivity {

    private int itemID;
    private int itemprice;
    private String itemname;
    private String itemdescription;


    public StoreItem(int itemID, int itemprice, String itemname, String itemdescription) {
        this.itemID = itemID;
        this.itemprice = itemprice;
        this.itemname = itemname;
        this.itemdescription = itemdescription;
    }

    //Getters and Setters

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getItemprice() {
        return itemprice;
    }

    public void setItemprice(int itemprice) {
        this.itemprice = itemprice;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getItemdescription() {
        return itemdescription;
    }

    public void setItemdescription(String itemdescription) {
        this.itemdescription = itemdescription;
    }


}
