package com.example.todoetprototype.pet;

import com.example.todoetprototype.R;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class PetModel implements Serializable {

    private static PetModel instance;

    public static PetModel getInstance() {
        if (instance == null)
            instance = new PetModel(0, "Tom", 5, false, 5, 6, 8, 7, 0, 0, 0, 0);
        return instance;
    }

    //out of 50

    public int ID;
    //public PetModel species;
    public String petName;
    public int hygiene;
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


    public int maximum_stat = 100;
    public int pet_death = -1;

    PetStages currentStage;
    PetStages egg;
    PetStages baby;
    PetStages adolescent;
    PetStages adult;
    PetStages ancient;

    private PetModel(int ID, String petName, int hygiene, boolean death, int happiness, int hunger, int affection, int passedTime, int age, int birthdate, long lastFedTimestamp, long lastPetTimestamp) {
        // out of 100

        this.ID = ID;
        //this.species = new PetModel();
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

        egg = PetStages.UNHATCH_SIORDON;
        baby = PetStages.BABY_SIORDON;
        adolescent = PetStages.ADOLESCENT_SIORDON;
        adult = PetStages.ADULT_SIORDON;
        ancient = PetStages.ANCIENT_SIORDON;
        currentStage = egg;
    }

    public enum PetStages {

        UNHATCH_SIORDON(1, "Siordon", 2, -1, R.drawable.axoeggtest),
        BABY_SIORDON(2, "Siordon", 3, 1, R.drawable.baby_siordon_image),
        ADOLESCENT_SIORDON(3, "Siordon", 4, 2, R.drawable.adolescent_siordon_image),
        ADULT_SIORDON(4, "Siordon", 5, 3, R.drawable.axostageonetest),
        ANCIENT_SIORDON(5, "Siordon", -1, 4, R.drawable.axostageonetest),

        UNHATCH_KITVIX(6, "Kitvix", 7, -1, R.drawable.foxeggtest),
        BABY_KITVIX(7, "Kitvix", 8, 6, R.drawable.foxspritetest),
        ADOLESCENT_KITVIX(8, "Kitvix", 9, 7, R.drawable.axostageonetest),
        ADULT_KITVIX(9, "Kitvix", 10, 8, R.drawable.axostageonetest),
        ANCIENT_KITVIX(11, "Kitvix", -1, 9, R.drawable.axostageonetest),

        UNHATCH_NYAKOLIS(12, "Nyakolis", 13, -1, R.drawable.categgtest),
        BABY_NYAKOLIS(13, "Nyakolis", 14, 12, R.drawable.axostageonetest),
        ADOLESCENT_NYAKOLIS(14, "Nyakolis", 15, 13, R.drawable.axostageonetest),
        ADULT_NYAKOLIS(15, "Nyakolis", 16, 14, R.drawable.axostageonetest),
        ANCIENT_NYAKOLIS(17, "Nyakolis", -1, 15, R.drawable.axostageonetest);


        private final int drawable;
        private final int uniqueID;
        private final String speciesName;
        private final int evolve_to;

        PetStages(int petID, String speciesName, int evolve_to, int evolve_from, int drawable) {

            this.drawable = drawable;
            this.uniqueID = petID;
            this.speciesName = speciesName;
            this.evolve_to = evolve_to;
        }

        public int getEvolve_to() {
            return evolve_to;
        }

        public int getDrawable() {
            return drawable;
        }

        public int getUniqueID() {
            return uniqueID;
        }

        public String getSpeciesName() {
            return speciesName;
        }

    }

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

    public int isHygiene() {
        return hygiene;
    }

    public void setHygiene(int hygiene) {
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

    public String setHunger(int increase) {
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

    public void setAffection(int increase) {
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


    //public PetModel getSpecies() {
    //    return species;
    //}

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

}