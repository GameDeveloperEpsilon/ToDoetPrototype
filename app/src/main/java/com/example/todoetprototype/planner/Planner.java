package com.example.todoetprototype.planner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.LinkedList;

public class Planner {

    private static int counter = 0;

    private final AppCompatActivity context;

    private final LinkedList<PlannerItem> plannerItems;

    public Planner(AppCompatActivity context) {
        this.context = context;

        plannerItems = new LinkedList<>();
    }

    public PlannerItem makePlannerItem() {
        PlannerItem newItem = new PlannerItem("Test Task " + ++counter, "Easy");
        addPlannerItem(newItem);

        return newItem;
    }

    public void addPlannerItem(PlannerItem plannerItem) {
        plannerItems.add(plannerItem);
    }

    public PlannerItem getPlannerItem(String itemName) {
        for (PlannerItem item: plannerItems) {
            if (item.name.equals(itemName)) {
                return item;
            }
        }
        return null;
    }

}
