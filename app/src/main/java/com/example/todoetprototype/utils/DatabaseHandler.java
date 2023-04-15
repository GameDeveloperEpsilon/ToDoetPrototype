package com.example.todoetprototype.utils;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.todoetprototype.planner.PlannerItem;


import com.example.todoetprototype.inventory.InventoryActivity;
import com.example.todoetprototype.inventory.UserModel;
import com.example.todoetprototype.store.StoreItem;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static DatabaseHandler databaseHandlerInstance = null;

    public static DatabaseHandler getInstance() {
        if (databaseHandlerInstance == null)
            throw new RuntimeException("Database not primed with context");
        return databaseHandlerInstance;
    }

    public static DatabaseHandler getInstance(Context context) {
        if (databaseHandlerInstance == null)
            databaseHandlerInstance = new DatabaseHandler(context);
        return databaseHandlerInstance;
    }

    private static final int CURRENT_VERSION = 4;

    // table pet
    private static final String PET_TABLE = "petdata";
    private static final String PET_ID = "petid";
    private static final String PET_NAME = "petname";
    private static final String HUNGER = "hunger";
    private static final String HEALTH = "health";
    private static final String AFFECTION = "affection";
    private static final String SPECIES = "species";
    //public static final String EVOLUTION_TABLE_NAME = "CREATURE_EVOLUTION";
    private static final String DEATH = "death";
    private static final String AGE = "age";

    // table pet
    private static final String CREATE_PET_TABLE ="CREATE TABLE " + PET_TABLE + "("+
            PET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            PET_NAME + " TEXT, " +
            HUNGER + " INTEGER, " +
            HEALTH + " INTEGER, " +
            AFFECTION + "INTEGER," +
            SPECIES + "TEXT," +
            DEATH + "INTEGER," +
            AGE + "INTEGER)";

    // table user
    private static final String USER_TABLE = "user_data";
    private static final String USER_ID = "userid";
    private static final String USER_NAME = "user_name";
    private static final String USER_COIN = "user_coin";
    //private static final String USER_PET = "user_pet";
    private static final String USER_LAST_LOGON = "user_last_logon";

    // table user
    private static final String CREATE_USER_TABLE ="CREATE TABLE " + USER_TABLE + "("+
            USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            USER_NAME + " TEXT, " +
            USER_COIN + " INTEGER, " +
            USER_LAST_LOGON + "INTEGER)";

    // table store
    private static final String STORE_TABLE = "store_data";
    private static final String ITEM_ID = "item_id";
    private static final String ITEM_NAME = "item_name";
    private static final String ITEM_PRICE = "item_price";
    private static final String ITEM_DESCRIPTION = "item_description";
    private static final String ITEM_CATEGORY = "item_category";

    // table store
    private static final String CREATE_STORE_TABLE ="CREATE TABLE " + STORE_TABLE + "("+
            ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ITEM_NAME + " TEXT, " +
            ITEM_PRICE + " INTEGER, " +
            ITEM_DESCRIPTION + " TEXT, " +
            ITEM_CATEGORY + "TEXT)";


    // table inventory
    private static final String INVENTORY_TABLE = "table_inventory";

    private static final String ITEM_IMAGE_ID = "item_image_id";

    // table inventory
    private static final String CREATE_INVENTORY_TABLE = "CREATE TABLE " + INVENTORY_TABLE + "(" +
            ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ITEM_NAME + " TEXT, " +
            ITEM_DESCRIPTION + " TEXT, " +
            ITEM_CATEGORY + " TEXT, " +
            ITEM_IMAGE_ID + " INTEGER)";


    // todolist table
    private static final String SCHEMA_NAME = "todoet_database";
    private static final String TODO_TABLE = "todo";
    private static final String ID = "id";
    private static final String TASK = "task";
    private static final String STATUS = "status";
    private static final String CAN_GIVE_COINS = "can_give_coins";
    private static final String DATE = "date";

    // to do table
    private static final String CREATE_TODO_TABLE =
            "CREATE TABLE " + TODO_TABLE + "(" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    TASK + " TEXT, " +
                    STATUS + " INTEGER, " +
                    CAN_GIVE_COINS + " INTEGER, " +
                    DATE + " TEXT)";


    private SQLiteDatabase db;
    private Context context;

    private DatabaseHandler(Context context) {
        super(context, SCHEMA_NAME, null, CURRENT_VERSION);
        this.context = context;
    }

    // table creation
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TODO_TABLE);
        //db.execSQL(CREATE_PET_TABLE);
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_STORE_TABLE);
        db.execSQL(CREATE_INVENTORY_TABLE);

        System.out.println("DB version : " + db.getVersion());
        //db.execSQL("ALTER TABLE " + TODO_TABLE + " ADD " + DATE + " datatype; ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older tables if they exist
        db.execSQL("DROP TABLE IF EXISTS " + TODO_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + PET_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + STORE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + INVENTORY_TABLE);
        // Create tables again
        onCreate(db);
    }

    public void openDatabase() {
        db = this.getWritableDatabase();
    }

    public void insertTask(PlannerItem task) {
        ContentValues cv = new ContentValues();
        cv.put(TASK, task.getTask());
        cv.put(STATUS, task.getStatus());
        cv.put(CAN_GIVE_COINS, task.canGivenCoins() ? 1 : 0);
        cv.put(DATE, task.getDate());
       // ((PlannerActivity) context).getPlannerViewModel().addPlannerItem(task);
        db.insert(TODO_TABLE, null, cv);
    }

    @SuppressLint("Range")
    public List<PlannerItem> getAllTasks() {
        List<PlannerItem> taskList = new ArrayList<>();
        db = this.getReadableDatabase();

        db.beginTransaction();
        try (Cursor cur = db.query(TODO_TABLE, null, null, null, null, null, null, null)) {

            assert cur != null;
            if (cur.moveToFirst()) {
                do {
                    PlannerItem task = new PlannerItem();
                    task.setId(cur.getInt(cur.getColumnIndex(ID)));
                    task.setTask(cur.getString(cur.getColumnIndex(TASK)));
                    task.setStatus(cur.getInt(cur.getColumnIndex(STATUS)));
                    task.setCanGivenCoins(cur.getColumnIndex(CAN_GIVE_COINS) != 0);
                    task.setDate(cur.getString(cur.getColumnIndex(DATE)));
                    taskList.add(task);
                    System.out.println("From getAllTasks: " + task);
                }
                while (cur.moveToNext());
            }
        } finally {
            db.endTransaction();
        }
        return taskList;
    }

    public void deleteAllTasks() {
        db.delete(TODO_TABLE, null, null);
    }

    public void updateStatus(int id, int status) {
        ContentValues cv = new ContentValues();
        cv.put(STATUS, status);
        db.update(TODO_TABLE, cv, ID + "= ?", new String[]{String.valueOf(id)});
    }

    @SuppressLint("Range")
    public void updateCanGiveCoins(int id, boolean canGiveCoins) {
        System.out.println("id = " + id + ", canGiveCoins = " + canGiveCoins);
        ContentValues cv = new ContentValues();
        System.out.println(CAN_GIVE_COINS + " = " + (canGiveCoins ? 1 : 0));
        cv.put(CAN_GIVE_COINS, 0);
        db = this.getWritableDatabase();
        db.update(TODO_TABLE, cv, ID + "= ?", new String[]{String.valueOf(id)});

        db = this.getReadableDatabase();

        PlannerItem task = new PlannerItem();
        db.beginTransaction();
        try (Cursor cur = db.query(TODO_TABLE, null, ID + " = ?", new String[]{String.valueOf(id)}, null, null, null, null)) {

            if (cur.moveToFirst()) {
                task.setId(cur.getInt(cur.getColumnIndex(ID)));
                task.setTask(cur.getString(cur.getColumnIndex(TASK)));
                task.setStatus(cur.getInt(cur.getColumnIndex(STATUS)));
                task.setCanGivenCoins(cur.getInt(cur.getColumnIndex(CAN_GIVE_COINS)) != 0);
                task.setDate(cur.getString(cur.getColumnIndex(DATE)));
            }
        } finally {
            db.endTransaction();
            System.out.println("After DB update: " + task);
        }
    }

    public void updateTask(int id, String task) {
        ContentValues cv = new ContentValues();
        cv.put(TASK, task);
        db.update(TODO_TABLE, cv, ID + "= ?", new String[]{String.valueOf(id)});
    }

    public void updateDate(int id, String date) {
        ContentValues cv = new ContentValues();
        cv.put(DATE, date);
        db.update(TODO_TABLE, cv, ID + "= ?", new String[]{String.valueOf(id)});
    }

    public void deleteTask(int id) {
        db.delete(TODO_TABLE, ID + "= ?", new String[]{String.valueOf(id)});
    }

    // ---------------------- Inventory ---------------------------

    @SuppressLint("Range")
    public void loadAllInventoryItems() {

        db = this.getReadableDatabase();
        Cursor cur = null;
        db.beginTransaction();
        try {
            cur = db.query(INVENTORY_TABLE, null, null, null, null, null, null, null);
            if (cur != null) {
                if (cur.moveToFirst()) {
                    UserModel.getInstance().clearInventory();
                    do {
                        StoreItem item = new StoreItem(
                                cur.getInt(cur.getColumnIndex(ITEM_ID)),
                                0,
                                cur.getString(cur.getColumnIndex(ITEM_NAME)),
                                cur.getString(cur.getColumnIndex(ITEM_DESCRIPTION)),
                                cur.getString(cur.getColumnIndex(ITEM_CATEGORY))
                        );
                        ((InventoryActivity) context).getUserViewModel().addItemToInventory(item);
                    }
                    while (cur.moveToNext());
                }
            }
        } finally {
            db.endTransaction();
            assert cur != null;
            cur.close();
        }
    }

    public void deleteAllInventoryItems() {
        db.delete(INVENTORY_TABLE, null, null);
    }

    public void insertInventoryItem(StoreItem inventoryItem) {
        ContentValues cv = new ContentValues();
        //cv.put(ITEM_ID, inventoryItem.getItemID());
        cv.put(ITEM_NAME, inventoryItem.getItemName());
        cv.put(ITEM_DESCRIPTION, inventoryItem.getItemDescription());
        cv.put(ITEM_CATEGORY, inventoryItem.getItemCategory());
        cv.put(ITEM_IMAGE_ID, inventoryItem.getDrawable());
        db.insert(INVENTORY_TABLE, null, cv);
    }

    public void deleteInventoryItem(int id) {
        db.delete(INVENTORY_TABLE, ITEM_ID + "= ?", new String[]{String.valueOf(id)});
    }
}
