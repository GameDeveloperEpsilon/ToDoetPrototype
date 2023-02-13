package com.example.todoetprototype.planner;

public class PlannerItem {

    public final String name;
    public final String difficulty;

    public PlannerItem(String itemName, String difficulty) {
        name = itemName;
        this.difficulty = difficulty;

    }

    @Override
    public String toString() {
        return name + " : " + difficulty;
    }

}
