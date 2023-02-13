package com.example.todoetprototype;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todoetprototype.planner.PlannerItem;

import java.util.ArrayList;

public class PlannerActivity extends AppCompatActivity {

    private ArrayList<PlannerItem> items;
    private ArrayAdapter<PlannerItem> itemsAdapter;
    private ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TaskCreationActivity.updateActivity(this);
        setContentView(R.layout.activity_planner);

        // ADD HERE
        lvItems = findViewById(R.id.lvItems);
        items = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);

        // Read From SharedPreferences
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(PlannerActivity.this);
        String taskName = sharedPref.getString(getString(R.string.taskName), "Default Task");
        String taskDifficulty = sharedPref.getString(getString(R.string.taskDifficulty), "Default Task Difficulty");
        Toast.makeText(this, "Task name is " + taskName + ": " + taskDifficulty, Toast.LENGTH_SHORT).show();

        setupListViewListener();
    }

    @Override
    public void onPause() {
        super.onPause();

        // Write To SharedPreferences
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(PlannerActivity.this);
                //getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        // Write if there are items to write
        if (!items.isEmpty()) {
            editor.putString(getString(R.string.taskName), items.get(0).name);
            editor.putString(getString(R.string.taskDifficulty), items.get(0).difficulty);
        }

        editor.apply();
    }

    // Attaches a long click listener to the listview
    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(
                (adapter, item, pos, id) -> {
                    // Remove the item within array at position
                    items.remove(pos);
                    // Refresh the adapter
                    itemsAdapter.notifyDataSetChanged();
                    // Return true consumes the long click event (marks it handled)
                    return true;
                });
    }

    public void addPlannerItem(String name, String difficulty) {
        itemsAdapter.add(new PlannerItem(name, difficulty));
    }

    public void onAddItem(View v) {
        //EditText etNewItem = findViewById(R.id.etNewItem);
        //String itemText = etNewItem.getText().toString();
        //itemsAdapter.add(itemText);
        //etNewItem.setText("");

        Intent changeActivities = new Intent(this, TaskCreationActivity.class);
        startActivity(changeActivities);
        //this.finish();
    }


}