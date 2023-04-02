package com.example.todoetprototype.planner;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoetprototype.adapter.ToDoAdapter;
import com.example.todoetprototype.DialogCloseListener;
import com.example.todoetprototype.R;
import com.example.todoetprototype.utils.DatabaseHandler;

import com.example.todoetprototype.inventory.UserViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PlannerActivity extends AppCompatActivity implements DialogCloseListener {

    // Extra note I needed to change the build from 32 to 33 in the build.gradle for the app to run

    private UserViewModel userViewModel;
    private RecyclerView taskRecyclerView;
    private ToDoAdapter tasksAdapter;
    private List<ToDoModel> taskList;
    private DatabaseHandler db;
    private FloatingActionButton fab;

    // Method for disabling the navigation bar
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planner);
        Objects.requireNonNull(getSupportActionBar()).hide(); // will not show top most navigation bar

        // Initialize userViewModel
        userViewModel = new ViewModelProvider(this) .get(UserViewModel.class);

        // Navigate to the calendar activity
        ImageButton gotoCalendarBtn = findViewById(R.id.calendar_icon);
        gotoCalendarBtn.setOnClickListener(calendarView -> {
            Intent gotoCalendar = new Intent(getApplicationContext(), CalendarActivity.class);
            startActivity(gotoCalendar);
        });


        // set current date
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        TextView textViewDate = findViewById(R.id.text_date);
        textViewDate.setText(currentDate);

        // Initialize the database
        db = new DatabaseHandler(this);
        db.openDatabase();

        // Initialize the task list
        taskList = new ArrayList<>(); // init taskList

        // for the recycler view: RecyclerView makes it easy to efficiently display large sets of data. You supply the data and define how each item looks, and the RecyclerView library dynamically creates the elements when they're needed.
        // As the name implies, RecyclerView recycles those individual elements. When an item scrolls off the screen, RecyclerView doesn't destroy its view. Instead, RecyclerView reuses the view for new items that have scrolled onscreen.
        // RecyclerView improves performance and your app's responsiveness, and it reduces power consumption.
        taskRecyclerView = findViewById(R.id.tasksRecyclerView);
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(this)); // define linear layout manager
        tasksAdapter = new ToDoAdapter(db, this);
        taskRecyclerView.setAdapter(tasksAdapter);

        // Floating action button. For the button in the corner of the screen to create a new task
        fab = findViewById(R.id.fab);

        // Updates task list
        taskList = db.getAllTasks();
        Collections.reverse(taskList); // reverse elements in an array
        tasksAdapter.setTaskList(taskList); // add task to recycler view



        // This is a utility class to add swipe to dismiss and drag &drop support to RecyclerView.
        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(new RecyclerItemTouchHelper(tasksAdapter));
        itemTouchHelper.attachToRecyclerView(taskRecyclerView);


        fab.setOnClickListener(
                v -> AddNewTask.newInstance().show(getSupportFragmentManager(), AddNewTask.TAG)
        );

    }

    @Override
    public void handleDialogClose(DialogInterface dialog){
        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        tasksAdapter.setTaskList(taskList);
        tasksAdapter.notifyDataSetChanged();
    }

    public UserViewModel getUserViewModel() {
        return userViewModel;
    }
}