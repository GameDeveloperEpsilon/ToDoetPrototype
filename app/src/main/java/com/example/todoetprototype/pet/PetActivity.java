package com.example.todoetprototype.pet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todoetprototype.R;

import java.io.Serializable;

public class PetActivity extends AppCompatActivity implements Serializable {
    private static final long serialVersionUID = 1L;

    private ImageView imageView;
    private PetSpecies petspecies;

//    private ImageButton statusButton;
//    private ImageButton playButton;
//    private ImageButton optionsButton;
//    private ImageButton cleanButton;
//    private ImageButton healButton;
//    private ImageButton feedButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);

        TextView txtname = findViewById(R.id.textView2);

        // randomized the petspecies

        PetSpecies.species[] Speciesp = generateRandomEgg(3);
        for (PetSpecies.species Species : Speciesp){
            System.out.println(Species);
        }
        //ImageView img = findViewById(R.id.imageaxo);
        ImageView imageaxo = (ImageView) findViewById(R.id.imageaxo);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            imageaxo.setImageIcon(Icon.createWithResource(this, R.drawable.axoeggtest));
            //imageaxo.setImageIcon(Icon.createWithResource(this, R.drawable.axostageonetest));
        }

        // Set On Click Listeners
        Button feedBtn = findViewById(R.id.feedBtn);
        Button cleanBtn = findViewById(R.id.cleanBtn);
        Button petBtn = findViewById(R.id.petBtn);

        feedBtn.setOnClickListener((l) -> {
            Context c = getApplicationContext();
            Toast.makeText(c, "Feeding Pet", Toast.LENGTH_SHORT).show();
        });
        cleanBtn.setOnClickListener((l) -> {
            Context c = getApplicationContext();
            Toast.makeText(c, "Cleaning Pet", Toast.LENGTH_SHORT).show();
        });
        petBtn.setOnClickListener((l) -> {
            Context c = getApplicationContext();
            Toast.makeText(c, "Petting Pet", Toast.LENGTH_SHORT).show();
        });

    }

    private PetSpecies.species[] generateRandomEgg(int i) {
        return PetSpecies.species.values();
    }
}
