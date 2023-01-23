package com.example.todoet_prototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button a = findViewById(R.id.homeBtn);
        a.setOnClickListener(view -> {
            Toast.makeText(this, "Changing Activities", Toast.LENGTH_SHORT).show();
            Intent changeActivities = new Intent(this, Nexus.class);
            startActivity(changeActivities);
        });
    }
}