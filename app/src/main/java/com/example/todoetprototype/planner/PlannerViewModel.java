package com.example.todoetprototype.planner;

import androidx.lifecycle.ViewModel;

import java.util.LinkedList;

public class PlannerViewModel extends ViewModel {

    private static int counter = 0;

    private PlannerModel model;
    private final LinkedList<PlannerItem> plannerItems;

    public PlannerViewModel() {

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
