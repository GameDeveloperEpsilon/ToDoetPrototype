package com.example.todoetprototype.pet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.todoetprototype.Nexus;
import com.example.todoetprototype.R;
import com.example.todoetprototype.databinding.ActivityPetBinding;
import com.example.todoetprototype.inventory.InventoryActivity;
import com.example.todoetprototype.planner.PlannerActivity;
import com.example.todoetprototype.store.StoreActivity;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;

public class PetActivity extends AppCompatActivity implements Serializable {

    private static final long serialVersionUID = 1L;



    private ActivityPetBinding binding;
    private TodopetViewModel petViewModel;
    private static PetModel petModel;

    // private ImageButton statusButton;
    // private ImageButton playButton;
    // private ImageButton optionsButton;
    // private ImageButton cleanButton;
    // private ImageButton healButton;
    // private ImageButton feedButton;


    SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sp=this.getSharedPreferences("myPetPrefs",Context.MODE_PRIVATE);
        petModel = PetModel.getInstance(sp);
        petViewModel = new ViewModelProvider(this).get(TodopetViewModel.class);

        if (petViewModel == null) {
            throw new RuntimeException("Error: Pet View Model null!");
        }
        if (petModel == null) {
            throw new RuntimeException("Error: Pet Model null!");
        }

        //TextView txtname = findViewById(R.id.textView2);

        // randomized the petspecies

//        PetModel.PetStages[] petStages = generateRandomEgg(3);
//        for (PetModel.PetStages petStage : petStages){
//            System.out.println(petStage);
//        }

        ImageView imageView = findViewById(R.id.imageView);

        // Set On Click Listeners
        Button feedBtn = findViewById(R.id.feedBtn);
        Button cleanBtn = findViewById(R.id.cleanBtn);
        Button petBtn = findViewById(R.id.petBtn);

        feedBtn.setOnClickListener((l) -> {
            Context c = getApplicationContext();
            Toast.makeText(c, "Feeding Pet", Toast.LENGTH_SHORT).show();
            petViewModel.feed();
        });
        cleanBtn.setOnClickListener((l) -> {
            Context c = getApplicationContext();
            Toast.makeText(c, "Cleaning Pet", Toast.LENGTH_SHORT).show();
            petViewModel.clean();
        });
        petBtn.setOnClickListener((l) -> {
            Context c = getApplicationContext();
            Toast.makeText(c, "Petting Pet", Toast.LENGTH_SHORT).show();
            petViewModel.pet();
        });

        petViewModel.getPetData().observe(this, pet -> {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                binding.imageView.setImageDrawable(
                        ResourcesCompat.getDrawable(getResources(), pet.currentStage.getDrawable(), null)
                );
            }

            // Handle Data Progress Bars
            ProgressBar hygieneBar = findViewById(R.id.hygienebar);
            hygieneBar.setProgress(PetModel.getInstance().hygiene);

            ProgressBar hungerBar = findViewById(R.id.hungerbar);
            hungerBar.setProgress(PetModel.getInstance().hunger);

            ProgressBar affectionBar = findViewById(R.id.happinessbar);
            affectionBar.setProgress(PetModel.getInstance().affection);

            ProgressBar levelBar = findViewById(R.id.levelbar);
//            levelBar.setProgress(PetModel.getInstance().level);

        });

        petViewModel.init();

        setUpNavigationBar();
    }

    @Override
    protected void onResume() {
        super.onResume();

        petModel.loadPetParameters(sp);
    }

//    private PetModel.PetStages[] generateRandomEgg(int i) {
//        return PetModel.PetStages.values();
//    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor editor = sp.edit();

        editor.putInt("hygiene", petModel.getHygiene());
        editor.putInt("hunger", petModel.getHunger());  //
        editor.putInt("affection", petModel.getAffection());
        editor.apply(); //
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        SharedPreferences.Editor editor = sp.edit();

        editor.putInt("hygiene", petModel.getHygiene());
        editor.putInt("hunger", petModel.getHunger());  //
        editor.putInt("affection", petModel.getAffection());
        editor.commit(); //
    }

    private void setUpNavigationBar() {

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.getMenu().findItem(R.id.pets_nav_item).setChecked(true);

        BottomNavigationItemView todoListNavItem = bottomNavigationView.findViewById(R.id.todo_list_nav_item);
        todoListNavItem.setOnClickListener(v -> {
            Intent changeActivities = new Intent(this, PlannerActivity.class);
            startActivity(changeActivities);
        });

        BottomNavigationItemView userNavItem = bottomNavigationView.findViewById(R.id.user_nav_item);
        userNavItem.setOnClickListener(v -> {
            Intent changeActivities = new Intent(this, InventoryActivity.class);
            startActivity(changeActivities);
        });

        BottomNavigationItemView homeNavItem = bottomNavigationView.findViewById(R.id.home_nav_item);
        homeNavItem.setOnClickListener(v -> {
            Intent changeActivities = new Intent(this, Nexus.class);
            startActivity(changeActivities);
        });

        BottomNavigationItemView petsNavItem = bottomNavigationView.findViewById(R.id.pets_nav_item);
        petsNavItem.setOnClickListener(v -> {
            Intent changeActivities = new Intent(this, PetActivity.class);
            startActivity(changeActivities);
        });

        BottomNavigationItemView storeNavItem = bottomNavigationView.findViewById(R.id.store_nav_item);
        storeNavItem.setOnClickListener(v -> {
            Intent changeActivities = new Intent(this, StoreActivity.class);
            startActivity(changeActivities);
        });
    }
}