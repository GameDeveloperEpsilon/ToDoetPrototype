package com.example.todoetprototype.pet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.todoetprototype.R;
import com.example.todoetprototype.databinding.ActivityPetBinding;

import java.io.Serializable;

public class PetActivity extends AppCompatActivity implements Serializable {

    private static final long serialVersionUID = 1L;

    private ActivityPetBinding binding;
    private TodopetViewModel petViewModel;

    // private ImageButton statusButton;
    // private ImageButton playButton;
    // private ImageButton optionsButton;
    // private ImageButton cleanButton;
    // private ImageButton healButton;
    // private ImageButton feedButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //TextView txtname = findViewById(R.id.textView2);

        // randomized the petspecies

        PetModel.PetStages[] petStages = generateRandomEgg(3);
        for (PetModel.PetStages petStage : petStages){
            System.out.println(petStage);
        }

        ImageView imageView = findViewById(R.id.imageView);

        // Set On Click Listeners
        Button feedBtn = findViewById(R.id.feedBtn);
        Button cleanBtn = findViewById(R.id.cleanBtn);
        Button petBtn = findViewById(R.id.petBtn);

        feedBtn.setOnClickListener((l) -> {
            Context c = getApplicationContext();
            Toast.makeText(c, "Feeding Pet", Toast.LENGTH_SHORT).show();
            PetModel.getInstance().fed = true;
        });
        cleanBtn.setOnClickListener((l) -> {
            Context c = getApplicationContext();
            Toast.makeText(c, "Cleaning Pet", Toast.LENGTH_SHORT).show();
            PetModel.getInstance().cleaned = true;
        });
        petBtn.setOnClickListener((l) -> {
            Context c = getApplicationContext();
            Toast.makeText(c, "Petting Pet", Toast.LENGTH_SHORT).show();
            PetModel.getInstance().petted = true;
        });

        petViewModel = new ViewModelProvider(this).get(TodopetViewModel.class);

        petViewModel.getPetData().observe(this, pet -> {

            if (pet.isHygiene() > 20) {
                Toast.makeText(this, "Your pet is clean!", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Your pet is dirty!", Toast.LENGTH_SHORT).show();
            }

            if (pet.getHunger() > 20) {
                Toast.makeText(this, "Hunger: " + pet.getHunger(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Your pet is hungry!", Toast.LENGTH_SHORT).show();
            }

//            if (pet.getHappiness() < 5) {
//                Toast.makeText(this, "Your pet is unhappy!", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(this, "Happiness: " + pet.getHappiness(), Toast.LENGTH_SHORT).show();
//            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                binding.imageView.setImageDrawable(
                        ResourcesCompat.getDrawable(getResources(), pet.currentStage.getDrawable(), null)
                );
                //binding.textView2.setText("Updated");
            }

            // Handle Data Progess Bars
            ProgressBar hungerBar = findViewById(R.id.hungerbar);
            hungerBar.setProgress(PetModel.getInstance().hunger);

            ProgressBar hygieneBar = findViewById(R.id.hygienebar);
            hygieneBar.setProgress(PetModel.getInstance().hygiene);

            ProgressBar affectionBar = findViewById(R.id.happinessbar);
            affectionBar.setProgress(PetModel.getInstance().affection);

            //ProgressBar levelBar = findViewById(R.id.hungerbar);
            //hungerBar.setProgress(PetModel.getInstance().level);
        });

        petViewModel.init();
    }

    private PetModel.PetStages[] generateRandomEgg(int i) {
        return PetModel.PetStages.values();
    }

    // SharedPreferences sharedPref = this.getSharedPreferences("petsavedstate", Context.MODE_PRIVATE);
}

