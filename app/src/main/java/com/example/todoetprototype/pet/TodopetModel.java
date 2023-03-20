package com.example.todoetprototype.pet;

import java.io.Serializable;

public class TodopetModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private static TodopetModel instance;

    public static TodopetModel getInstance() {
        if (instance == null)
            instance = new TodopetModel("Tom", true, false, 5, 6, 8, 7, 0);
        return instance;
    }

    //out of 50

    private PetSpecies species;
    private String petName;
    private boolean hygiene;  public boolean cleaned;
    private boolean death;
    private int happiness;  public boolean petted;
    private int hunger;  public boolean fed;
    private int affection;
    private int passedTime;
    private int age;
    private int randomPet = 4;

    private TodopetModel(String petName, boolean hygiene, boolean death, int happiness, int hunger, int affection, int passedTime, int age) {

        // out of 100

        this.species = new PetSpecies();
        this.petName = petName;
        this.hygiene = hygiene;
        this.death = death;
        this.happiness = happiness;
        this.hunger = hunger;
        this.affection = 20;
        this.age = age;

        int hours = passedTime / 60;

        //decrementAfterTime(hours);
    }


    // @return A basic creature object


    // GETTER AND SETTER

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
        return this.happiness <= 0 && this.hunger <= 0;
    }

    public void setDeath(boolean death) {
        this.death = death;
    }

    public int getHappiness() {
        return happiness;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public int getAffection() {
        return affection;
    }

    public void setAffection(int affection) {
        this.affection = affection;
    }

    public int getPassedTime() {
        return passedTime;
    }

    public void setPassedTime(int passedTime) {
        this.passedTime = passedTime;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public PetSpecies getSpecies() {
        return species;
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



