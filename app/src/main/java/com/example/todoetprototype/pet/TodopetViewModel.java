package com.example.todoetprototype.pet;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TodopetViewModel extends ViewModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private MutableLiveData<PetModel> petData = new MutableLiveData<>();

    private long delay = 5000L;


    public void init() {

        petData.setValue(PetModel.getInstance());
        updatePet();
    }

    public LiveData<PetModel> getPetData() {
        return petData;
    }

    //feeding

    public void feed() {
        petData.getValue().setAffection(10);
        this.petData.getValue().lastFedTimestamp = new Date().getTime();
    }

    public void pet() {
        this.petData.getValue().lastPetTimestamp = new Date().getTime();
    }

    // clock

    private void updatePet() {

        Runnable tickPetStats = () -> {
            System.out.println("Tick");
            tickPetStats();
            updatePet();
        };

        new Handler(Looper.getMainLooper()).postDelayed(tickPetStats, delay);
    }

    private void tickPetStats() {

        PetModel pet = getPetData().getValue();

        if (pet == null) {
            System.err.println("Pet is null");
            return;
        }

        // If pet was not cleaned, reduce hygiene.
        if (pet.cleaned) {
            pet.setHygiene(10);
            pet.cleaned = false;
        } else {
            pet.setHygiene(-5);
        }

        if (pet.fed) {
            pet.setHunger(10);
            pet.fed = false;
        }
        else
            pet.setHunger(-5);

        if (pet.petted) {
            pet.setAffection(10);
            pet.petted = false;
        } else {
            pet.setAffection(-5);
        }



        // Update pet appearance based on stage
        if (pet.currentStage == pet.egg) {
            pet.currentStage = pet.baby;
        }
        else if (pet.currentStage == pet.baby) {
            pet.currentStage = pet.adolescent;
        }
        else if (pet.currentStage == pet.adolescent) {
            pet.currentStage = pet.adult;
        }
        else if (pet.currentStage == pet.adult) {
            pet.currentStage = pet.ancient;
        }

        petData.setValue(pet);
    }
}