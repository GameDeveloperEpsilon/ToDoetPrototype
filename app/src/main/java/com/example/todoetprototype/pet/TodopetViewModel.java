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
    private MutableLiveData<PetModel> petData = new MutableLiveData<>();

    private int currentIndex = 0;
    private long delay = 5000L;


    public void init() {

        petData.setValue(PetModel.getInstance());
        updatePet();
    }

    public LiveData<PetModel> getPetData() {
        return petData;
    }


    private static final long serialVersionUID = 1L;


    // @return A basic creature object




    //feeding

    public void feed() {
        updateAffection(petData.getValue().getAffection() + 1);
        this.petData.getValue().lastFedTimestamp = new Date().getTime();
    }

    public void pet() {
        this.petData.getValue().lastPetTimestamp = new Date().getTime();
    }



    // affection updates

    public void updateAffection(int newAffection) {
        this.petData.getValue().affection = newAffection;

        if (this.petData.getValue().affection > this.petData.getValue().maximum_stat) {
            this.petData.getValue().affection = this.petData.getValue().maximum_stat;
        }

        if (this.petData.getValue().affection <= 0) {
            this.petData.getValue().affection = 0;
        }

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

        // If pet was not cleaned, make un-hygienic.
        if (pet.cleaned) {
            pet.setHygiene(10);
            pet.cleaned = false;
        } else {
            pet.setHygiene(-5);
        }

        if (pet.fed) {
            petData.getValue().setHunger(10);
            pet.fed = false;
        }
        else
            petData.getValue().setHunger(-5);

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