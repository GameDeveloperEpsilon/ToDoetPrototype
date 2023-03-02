package com.example.todoetprototype.pet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todoetprototype.R;
import com.example.todoetprototype.databinding.ActivityPetBinding;

import java.io.Serializable;

public class PetActivity extends AppCompatActivity implements Serializable {

    private static final long serialVersionUID = 1L;

    private ActivityPetBinding binding;
    private PetViewModel mMainActivityViewModel;

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

        TextView txtname = findViewById(R.id.textView2);

        // randomized the petspecies

        PetSpecies.PetStages[] petStages = generateRandomEgg(3);
        for (PetSpecies.PetStages petStage : petStages){
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
        });
        cleanBtn.setOnClickListener((l) -> {
            Context c = getApplicationContext();
            Toast.makeText(c, "Cleaning Pet", Toast.LENGTH_SHORT).show();
        });
        petBtn.setOnClickListener((l) -> {
            Context c = getApplicationContext();
            Toast.makeText(c, "Petting Pet", Toast.LENGTH_SHORT).show();
        });

        //myLoop();

        mMainActivityViewModel = new ViewModelProvider(this).get(PetViewModel.class);

        mMainActivityViewModel.getPetData().observe(this, pet -> {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                binding.imageView.setImageDrawable(
                        ResourcesCompat.getDrawable(getResources(), pet.currentStage.getDrawable(), null)
                );
                //binding.textView2.setText("Updated");
            }
        });

        mMainActivityViewModel.init();
    }

    private PetSpecies.PetStages[] generateRandomEgg(int i) {
        return PetSpecies.PetStages.values();
    }
}
