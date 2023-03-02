package com.example.todoetprototype.pet;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Objects;

public class PetViewModel extends ViewModel {

    private MutableLiveData<PetSpecies> petData = new MutableLiveData<>();
    //private CityDataProvider cities = new CityDataProvider();

    private int currentIndex = 0;
    private long delay = 5000L;

    public void init() {
        //cities.buildCities();
        petData.setValue(new PetSpecies());
        myLoop();
    }

    public LiveData<PetSpecies> getPetData() {
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

        PetSpecies pet = getPetData().getValue();

        if (pet == null) {
            System.err.println("Pet is null");
            return;
        }
        pet.currentStage = pet.baby;
        petData.setValue(pet);
    }
}
