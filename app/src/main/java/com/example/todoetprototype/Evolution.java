package com.example.todoetprototype;

public abstract class Evolution {
     //Indicates which pet species will evolve
      protected Petspecies uniqueID;

    //gets the petspecies
        public Petspecies getUniqueID(){
        return this.uniqueID;
        }

        //sets the species the pet will evolve into
        public void setUniqueID (Petspecies uniqueID) {
            this.uniqueID = uniqueID;
        }


        }


