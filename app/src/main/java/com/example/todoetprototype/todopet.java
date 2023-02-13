package com.example.todoetprototype;

import java.io.Serializable;

public class todopet implements Serializable {
    private static final long serialVersionUID = 1L;


    //out of 50

    private String name;
    private boolean hygiene;
    private boolean death;
    private int happiness;
    private int hunger;
    private int affection;
    private int passedTime;
    private int age;
    private int radompet = 4;


    public todopet(String name, boolean hygiene, boolean death, int happiness, int hunger, int affection, int passedTime, int age) {

        // out of 100


        this.name = name;
        this.hygiene = hygiene;
        this.death = death;
        this.happiness = 50;
        this.hunger = 100;
        this.affection = 20;
        this.age = age;



        int hours = passedTime / 60;

        //decrementAfterTime(hours);
    }


    // @return A basic creature object


// GETTER AND SETTER



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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



