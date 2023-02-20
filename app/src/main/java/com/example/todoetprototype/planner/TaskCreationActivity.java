package com.example.todoetprototype.planner;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todoetprototype.R;
import com.example.todoetprototype.planner.PlannerActivity;

import java.lang.ref.WeakReference;

public class TaskCreationActivity extends AppCompatActivity {

    private static WeakReference<PlannerActivity> mContext;

    public static void updateActivity(PlannerActivity context) {
        mContext = new WeakReference<>(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_creation);

        // Set up add task button
        Button addTaskBtn = findViewById(R.id.finishTaskCreation);
        addTaskBtn.setOnClickListener(view -> {

            // Get task name
            EditText taskNameTextEdit = findViewById(R.id.etNewItem);
            String taskName = String.valueOf(taskNameTextEdit.getText());

            // Get selected difficulty
            RadioGroup difficultySelector = findViewById(R.id.taskDifficultyRadioGroup);
            int difficultyID = difficultySelector.getCheckedRadioButtonId();
            RadioButton selectedDifficultyBtn = findViewById(difficultyID);
            String taskDifficulty = (String) selectedDifficultyBtn.getText();


            // Finish and return to Planner Activity
            mContext.get().addPlannerItem(taskName, taskDifficulty);
            Toast.makeText(this, "Task Created: " + taskName + ", " + taskDifficulty, Toast.LENGTH_SHORT).show();
            this.finish();
        });
    }
}