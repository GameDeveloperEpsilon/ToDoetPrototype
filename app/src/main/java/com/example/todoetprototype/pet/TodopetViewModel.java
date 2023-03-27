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
    private MutableLiveData<TodopetViewModel> petData = new MutableLiveData<>();

    private int currentIndex = 0;
    private long delay = 20000L;


    public void init() {

        petData.setValue(TodopetViewModel.getInstance());
        myLoop();
    }

    public LiveData<TodopetViewModel> getPetData() {
        return petData;
    }


    private static final long serialVersionUID = 1L;

    private static TodopetViewModel instance;

    public static TodopetViewModel getInstance() {
        if (instance == null)
            instance = new TodopetViewModel(0, "Tom", true, false, 5, 6, 8, 7, 0, 0, 0, 0);
        return instance;
    }

    //out of 50

    public int ID;
    public PetModel species;
    public String petName;
    public boolean hygiene;
    public boolean cleaned;
    public boolean death;
    public int happiness;
    public boolean petted;
    public int hunger;
    public boolean fed;
    public int affection;
    public int passedTime;
    public int age; // set to birthdate
    public int birthdate; // to replace age
    public int randomPet = 4;
    long lastFedTimestamp;
    long lastPetTimestamp;


    private int maximum_stat = 100;
    private int pet_death = -1;


    private TodopetViewModel(int ID, String petName, boolean hygiene, boolean death, int happiness, int hunger, int affection, int passedTime, int age, int birthdate, long lastFedTimestamp, long lastPetTimestamp) {
        // out of 100

        this.ID = ID;
        this.species = new PetModel();
        this.petName = petName;
        this.hygiene = hygiene;
        this.death = death;
        //this.happiness = happiness;
        this.hunger = 50;
        this.affection = 50;
        this.age = age;
        this.birthdate = birthdate;


        this.lastFedTimestamp = lastFedTimestamp;
        this.lastPetTimestamp = lastPetTimestamp;
        //int hours = passedTime / 60;

        //decrementAfterTime(hours);
    }


    // @return A basic creature object


    // GETTER AND SETTER

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public boolean isHygiene() {
        return hygiene;
    }

    public void setHygiene(boolean hygiene) {
        this.hygiene = hygiene;
    }

    public boolean isDeath() {
        return this.affection <= 0;
    }

    public void setDeath(boolean death) {
        this.death = death;
    }

    public int getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(int birthdate) {
        this.birthdate = birthdate;
    }


//    public int getHappiness() {
//        return happiness;
//    }
//
//    public void setHappiness(int happiness) {
//        this.happiness = happiness;
//    }

    public int getHunger() {
        return hunger;
    }

    private String setHunger(int increase) {
        String response;

        if (this.hunger + increase > maximum_stat) {
            response = "I'm full!";
            this.hunger = maximum_stat;
        } else {
            response = "Yum";
            this.hunger += increase;

        }

        return response;

    }


    public int getAffection() {
        return affection;
    }

    private void setAffection(int increase) {
        if (this.affection + increase > maximum_stat)
            this.affection = maximum_stat;
        else
            this.affection += increase;
    }


    public int getPassedTime() {
        return passedTime;
    }

    public void setPassedTime(int passedTime) {
        this.passedTime = passedTime;
    }

    public int getAge() {
        return this.age;
    }


    public void increaseAge() {
        this.age += 1;
    }

    private void setAge(int passedTime) {
        this.age += (passedTime / 24);
    }


    public PetModel getSpecies() {
        return species;
    }

    /**
     * Called when pet stats change as a whole, even when the application is closed
     * methods needed to increase/decrease pets needs
     */

    public boolean statChanges(int amount) {

        affection -= amount;
        hunger -= amount;
        age += amount;


        if (affection > maximum_stat) affection = maximum_stat;

        if (affection < pet_death)
            return true;
        else
            return false;

    }

    public void affection() {
        affection = 50;
        if (hunger >= 10)
            affection -= 5;


    }

    // Birthdate

    public int getDaysOld() {
        // Convert birthday string into timestamp
        try {
            Long millis = new SimpleDateFormat("MM/dd/yyyy").parse(String.valueOf(this.birthdate)).getTime();

            // Get system clock time now
            Long now = new Date().getTime();

            // Find the difference
            Long diff = now - millis;

            // Convert into days
            int days = (int) (diff / 1000 / 60 / 60 / 24);

            return days;


        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;

    }

    public long getLastFedTimestamp() {
        return this.lastFedTimestamp;
    }

    public long getLastPetTimestamp() {
        return this.lastPetTimestamp;
    }

    public int getHoursSinceLastFed() {
        long lastFedTimestamp = this.getLastFedTimestamp();
        long now = new Date().getTime();
        long diff = now - lastFedTimestamp;
        int hours = (int) (diff / 1000 / 60 / 60);

        return hours;
    }

    public int getHoursSinceLastPet() {
        long lastPetTimestamp = this.getLastPetTimestamp();
        long now = new Date().getTime();
        long diff = now - lastPetTimestamp;
        int hours = (int) (diff / 1000 / 60 / 60);

        return hours;
    }

    //agg if it's an egg

    public boolean isEgg() {
        return (this.getDaysOld() == 0);
    }

    //feeding

    public void feed() {
        updateAffection(getAffection() + 1);
        this.lastFedTimestamp = new Date().getTime();
    }

    public void pet() {
        this.lastPetTimestamp = new Date().getTime();
    }



    // affection updates

    public void updateAffection(int newAffection) {
        this.affection = newAffection;

        if (this.affection > this.maximum_stat) {
            this.affection = this.maximum_stat;
        }

        if (this.affection <= 0) {
            this.affection = 0;
        }

    }



    // clock

    private void myLoop() {

        Runnable jobToRun = () -> {
            System.out.println("Tick");
            updatePet();
            myLoop();
        };

        new Handler(Looper.getMainLooper()).postDelayed(jobToRun, delay);
    }

    private void updatePet() {

        TodopetViewModel pet = getPetData().getValue();

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





//        if (pet.petted) {
//            pet.setHappiness(10);
//            pet.petted = false;
//        } else {
//            pet.setHappiness(pet.getHappiness() - 1);
//        }



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












/*


    public String name();{

    }

    public String species();{


    }

    public void happiness();{

    }

    public void hunger();{
        hunger -= 25;
        if(hunger < 0 ){
            hunger = 0;
        }

    }

    public void affection();{
        affection = 10;
        if(hunger >= 10)
        affection -= 5;

    }

    public void hygiene();{

    }

    public boolean death();{

    }

    public void passTime();{

    }*/



