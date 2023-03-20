package com.example.todoetprototype.pet;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Date;
import java.util.Objects;

public class PetViewModel extends ViewModel {

    private MutableLiveData<TodopetModel> petData = new MutableLiveData<>();

    private int currentIndex = 0;
    private long delay = 20000L;

    public void init() {

        petData.setValue(TodopetModel.getInstance());
        myLoop();
    }

    public LiveData<TodopetModel> getPetData() {
        return petData;
    }

    private void myLoop() {

        Runnable jobToRun = () -> {
            System.out.println("Tick");
            updatePet();
            myLoop();
        };

        new Handler(Looper.getMainLooper()).postDelayed(jobToRun, delay);
    }

    private void updatePet() {

        TodopetModel pet = getPetData().getValue();

        if (pet == null) {
            System.err.println("Pet is null");
            return;
        }

        // If pet was not cleaned, make un-hygienic.
        if (pet.cleaned) {
            pet.setHygiene(true);
            pet.cleaned = false;
        } else {
            pet.setHygiene(false);
        }

        if (pet.fed) {
            pet.setHunger(10);
            pet.fed = false;
        } else {
            pet.setHunger(pet.getHunger() - 1);
        }

        if (pet.petted) {
            pet.setHappiness(10);
            pet.petted = false;
        } else {
            pet.setHappiness(pet.getHappiness() - 1);
        }

        // Update pet appearance based on stage
        if (pet.getSpecies().currentStage == pet.getSpecies().egg) {
            pet.getSpecies().currentStage = pet.getSpecies().baby;
        }
        else if (pet.getSpecies().currentStage == pet.getSpecies().baby) {
            pet.getSpecies().currentStage = pet.getSpecies().adolescent;
        }
        else if (pet.getSpecies().currentStage == pet.getSpecies().adolescent) {
            pet.getSpecies().currentStage = pet.getSpecies().adult;
        }
        else if (pet.getSpecies().currentStage == pet.getSpecies().adult) {
            pet.getSpecies().currentStage = pet.getSpecies().ancient;
        }

        petData.setValue(pet);
    }
}
