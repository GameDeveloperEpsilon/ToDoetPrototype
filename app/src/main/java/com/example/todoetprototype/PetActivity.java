package com.example.todoetprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class PetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);

        todopet pet = new todopet("Pet", false, false, 50, 100, 20, 0, 0);

        Toast.makeText(this, "New Pet: " + pet.getName(), Toast.LENGTH_SHORT).show();
    }
}