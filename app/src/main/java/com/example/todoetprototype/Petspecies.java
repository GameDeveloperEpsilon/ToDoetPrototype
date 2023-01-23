package com.example.todoetprototype;

import java.io.ObjectInputStream;
import java.io.Serializable;

import android.content.Intent;

import java.io.Serializable;
import java.util.Random;

public class Petspecies implements Serializable {


        public enum species {


            UNHATCH_SIORDON(1, "Siordon", 2, -1, R.drawable.axoeggtest),
            BABY_SIORDON(2, "Siordon", 3, 1, R.drawable.axostageonetest),
            ADOLESCENT_SIORDON(3, "Siordon", 4, 2, R.drawable.axostageonetest),
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

            species(int uniqueID, String speciesName, int evolve_to, int evolve_from, int drawable) {
                this.drawable = drawable;
                this.uniqueID = uniqueID;
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

    // possible animal values from enum Animal. Randomizes the pet species
    // This array will be of size 7.

        species [] generateRandomEgg(int n){

            species[] petOptions = species.values();

            Random random = new Random();

            species[] Speciesp = new species[n];

            for (int i = 0; i < n; i++){
                int index = random.nextInt(petOptions.length);
                Speciesp[i] = petOptions[index];
            }
            return Speciesp;
        }


    }

