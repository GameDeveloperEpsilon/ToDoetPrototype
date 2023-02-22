package com.example.todoetprototype.pet;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todoetprototype.R;

import java.io.Serializable;

public class PetActivity extends AppCompatActivity implements Serializable {
    private static final long serialVersionUID = 1L;

    private ImageView imageView;
    private Petspecies petspecies;


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
        //ImageView img = findViewById(R.id.imageaxo);
        ImageView imageaxo = (ImageView) findViewById(R.id.imageaxo);

        // randomized the petspecies

        Petspecies.species[] Speciesp = generateRandomEgg(3);
        for (Petspecies.species Species : Speciesp){
            Toast.makeText(this, " " + Species.getSpeciesName(), Toast.LENGTH_SHORT).show();
        }



       // ImageView imageaxo = (ImageView) findViewById(R.id.imageaxo);
       // imageaxo.setImageResource(R.drawable.axoeggtest);



    }

    private Petspecies.species[] generateRandomEgg(int i) {
        return new Petspecies.species[0];
    }
}



