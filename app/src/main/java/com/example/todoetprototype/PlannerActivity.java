package com.example.todoetprototype;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.todoetprototype.Adapter.ToDoAdapter;
import com.example.todoetprototype.inventory.Model.ToDoModel;
import com.example.todoetprototype.Utils.DatabaseHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class PlannerActivity extends AppCompatActivity implements DialogCloseListener {

    //Extra note I needed to change the build from 32 to 33 in the build.gradle for the app to run

    private RecyclerView taskRecyclerView;
    private ToDoAdapter tasksAdapter;
    private List<ToDoModel> taskList;
    private DatabaseHandler db;
    private FloatingActionButton fab;

    // method for disabling the navigation bar
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planner);
        getSupportActionBar().hide(); // will not show top most navigation bar


        // set current date
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        TextView textViewDate = findViewById(R.id.text_date);
        textViewDate.setText(currentDate);

        // initi the database
        db = new DatabaseHandler(this);
        db.openDatabase();

        //init the task list
        taskList = new ArrayList<>(); // init taskList

        // for the recycler view: RecyclerView makes it easy to efficiently display large sets of data. You supply the data and define how each item looks, and the RecyclerView library dynamically creates the elements when they're needed.
        //As the name implies, RecyclerView recycles those individual elements. When an item scrolls off the screen, RecyclerView doesn't destroy its view. Instead, RecyclerView reuses the view for new items that have scrolled onscreen.
        // RecyclerView improves performance and your app's responsiveness, and it reduces power consumption.
        taskRecyclerView = findViewById(R.id.tasksRecyclerView);
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(this)); // define linear layout manager
        tasksAdapter = new ToDoAdapter(db, PlannerActivity.this);
        taskRecyclerView.setAdapter(tasksAdapter);

        //floating action button. For the button in the corner of the screen to create a new task
        fab = findViewById(R.id.fab);

        //updates task list
        taskList = db.getAllTasks();
        Collections.reverse(taskList); // reverse elements in an array
        tasksAdapter.setTask(taskList); // add task to recycler view


        //This is a utility class to add swipe to dismiss and drag &drop support to RecyclerView.
        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(new RecyclerItemTouchHelper(tasksAdapter));
        itemTouchHelper.attachToRecyclerView(taskRecyclerView);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewTask.newInstance().show(getSupportFragmentManager(), AddNewTask.TAG);
            }
        });

    }

    @Override
    public void handleDialogClose(DialogInterface dialog){
        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        tasksAdapter.setTask(taskList);
        tasksAdapter.notifyDataSetChanged();
    }
}