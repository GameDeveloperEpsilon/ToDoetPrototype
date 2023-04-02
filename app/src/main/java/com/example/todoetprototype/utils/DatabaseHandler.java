package com.example.todoetprototype.utils;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.todoetprototype.planner.ToDoModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    // table pet
    private static final String TBL_PET = "Pet_table_database";
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
    private static final String TBL_USER = "user_table_database";
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
    private static final String TBL_STORE = "store_table_database";
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

//    // table inventory
//    private static final String ITEM_QUANTITY = "item_quantity";
//
//
//    // table inventory
//    private static final String CREATE_PET_TABLE ="CREATE TABLE " + PET_TABLE + "("+
//            PET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//            PET_NAME + " TEXT, " +
//            HUNGER + " INTEGER, " +
//            HEALTH + " INTEGER, " +
//            AFFECTION + "INTEGER," +
//            SPECIES + "TEXT," +
//            DEATH + "INTEGER," +
//            AGE + "INTEGER)";


    // todolist table
    private static final int CURRENT_VERSION = 2;
    private static final String NAME = "toDoListDatabase";
    private static final String TODO_TABLE = "todo";
    private static final String ID = "id";
    private static final String TASK = "task";
    private static final String STATUS = "status";
    private static final String DATE = "date";

    // to do table
    private static final String CREATE_TODO_TABLE =
            "CREATE TABLE " + TODO_TABLE + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TASK + " TEXT, " + STATUS + " INTEGER, " + DATE + " TEXT)";


    private SQLiteDatabase db;

    public DatabaseHandler(Context context) {
        super(context, NAME, null, CURRENT_VERSION);
    }

    // table creation
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TODO_TABLE);
        db.execSQL(CREATE_PET_TABLE);
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_STORE_TABLE);
        //db.execSQL(CREATE_INVENTORY_TABLE);

        System.err.println("DB version : " + db.getVersion());
        //db.execSQL("ALTER TABLE " + TODO_TABLE + " ADD " + DATE + " datatype; ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TODO_TABLE);
        // Create tables again
        onCreate(db);
    }

    public void openDatabase() {
        db = this.getWritableDatabase();
    }

    public void insertTask(ToDoModel task) {
        ContentValues cv = new ContentValues();
        cv.put(TASK, task.getTask());
        cv.put(STATUS, 0);
        cv.put(DATE, task.getDate());
        db.insert(TODO_TABLE, null, cv);
    }

    @SuppressLint("Range")
    public List<ToDoModel> getAllTasks() {
        List<ToDoModel> taskList = new ArrayList<>();
        Cursor cur = null;
        db.beginTransaction();
        try {
            cur = db.query(TODO_TABLE, null, null, null, null, null, null, null);
            if (cur != null) {
                if (cur.moveToFirst()) {
                    do {
                        ToDoModel task = new ToDoModel();
                        task.setId(cur.getInt(cur.getColumnIndex(ID)));
                        task.setTask(cur.getString(cur.getColumnIndex(TASK)));
                        task.setStatus(cur.getInt(cur.getColumnIndex(STATUS)));
                        task.setDate(cur.getString(cur.getColumnIndex(DATE)));
                        taskList.add(task);
                    }
                    while (cur.moveToNext());
                }
            }
        } finally {
            db.endTransaction();
            assert cur != null;
            cur.close();
        }
        return taskList;
    }

    public void updateStatus(int id, int status) {
        ContentValues cv = new ContentValues();
        cv.put(STATUS, status);
        db.update(TODO_TABLE, cv, ID + "= ?", new String[]{String.valueOf(id)});
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


    }



