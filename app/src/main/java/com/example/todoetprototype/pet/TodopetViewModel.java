package com.example.todoetprototype.pet;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.todoetprototype.inventory.UserModel;
import com.example.todoetprototype.store.StoreItem;

import java.io.Serializable;

public class TodopetViewModel extends ViewModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private final MutableLiveData<PetModel> petData = new MutableLiveData<>(PetModel.getInstance());

    private long tickRate = 1000L;


    public void init() {
        updatePet();
    }

    public LiveData<PetModel> getPetData() {
        return petData;
    }

    public boolean useItem(String itemCategory) {
        for (StoreItem item: UserModel.getInstance().getInventory()) {
            if (item.getItemCategory().equals(itemCategory)) {
                UserModel.getInstance().removeItemFromInventory(item);
                return true;  // item found
            }
        }
        return false;  // no items of type category
    }

    public void clean() {
        if (useItem("CLEANER")) {
            PetModel pet = PetModel.getInstance();
            pet.setHygiene(10);
            petData.setValue(pet);
        }
    }

    public void feed() {
        if (useItem("FOOD")) {
            PetModel pet = PetModel.getInstance();
            pet.setHunger(10);
            //petData.getValue().setAffection(10);
            //this.petData.getValue().lastFedTimestamp = new Date().getTime();
            petData.setValue(pet);
        }
    }

    public void pet() {
        PetModel pet = PetModel.getInstance();
        pet.setAffection(10);
        //this.petData.getValue().lastPetTimestamp = new Date().getTime();
        petData.setValue(pet);
    }

    // clock
    int lastHygieneTick = 0;
    int lastHungerTick = 0;
    int lastAffectionTick = 0;
    int lastPetLevelTick = 0;

    final int ticksPerHygieneUpdate = 10;
    final int ticksPerHungerUpdate = 5;
    final int ticksPerAffectionUpdate = 10;
    final int ticksPerPetLevelUpdate = 10;

    private void updatePet() {

        Runnable tickPetStats = () -> {
            System.out.println("Tick");
            tickPetStats();
            updatePet();
        };

        new Handler(Looper.getMainLooper()).postDelayed(tickPetStats, tickRate);
    }

    private void tickPetStats() {

        PetModel pet = getPetData().getValue();

        if (pet == null) {
            System.err.println("Pet is null");
            return;
        }

        lastHygieneTick = (lastHygieneTick + 1) % ticksPerHygieneUpdate;
        lastHungerTick = (lastHungerTick + 1) % ticksPerHungerUpdate;
        lastAffectionTick = (lastAffectionTick + 1) % ticksPerAffectionUpdate;
        lastPetLevelTick = (lastPetLevelTick + 1) % ticksPerPetLevelUpdate;

        // If pet reaches tick threshold, reduce hygiene.
        if (lastHygieneTick % ticksPerHygieneUpdate == 0) {
            pet.setHygiene(-5);
        }

        // If pet reaches tick threshold, reduce hunger stat.
        if (lastHungerTick % ticksPerHungerUpdate == 0) {
            pet.setHunger(-5);
        }

        // If pet reaches tick threshold, reduce affection stat.
        if (lastAffectionTick % ticksPerAffectionUpdate == 0) {
            pet.setAffection(-5);
        }

        if (lastPetLevelTick % ticksPerPetLevelUpdate == 0) {
            // Update pet appearance based on stage
            if (pet.currentStage == pet.egg) {
                pet.currentStage = pet.baby;
            } else if (pet.currentStage == pet.baby) {
                pet.currentStage = pet.adolescent;
            } else if (pet.currentStage == pet.adolescent) {
                pet.currentStage = pet.adult;
            } else if (pet.currentStage == pet.adult) {
                pet.currentStage = pet.ancient;
            }
        }

        petData.setValue(pet);
    }
}