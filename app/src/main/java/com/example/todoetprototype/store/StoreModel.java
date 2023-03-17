package com.example.todoetprototype.store;

import androidx.appcompat.app.AppCompatActivity;

public class StoreModel extends AppCompatActivity {

    private int itemID;
    private int itemprice;
    private String itemname;
    private String itemdescription;
    private String itemcategory;


    public StoreModel(int itemID, int itemprice, String itemname, String itemdescription, String itemcategory) {
        this.itemID = itemID;
        this.itemprice = itemprice;
        this.itemname = itemname;
        this.itemdescription = itemdescription;
        this.itemcategory = itemcategory;;
    }

    //Getters and Setters
    //

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

    public String getItemcategory() {
        return itemcategory;
    }

    public void setItemcategory(String itemcategory) {
        this.itemcategory = itemcategory;
    }
}



