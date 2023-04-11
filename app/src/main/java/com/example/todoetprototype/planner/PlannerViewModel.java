package com.example.todoetprototype.planner;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PlannerViewModel extends ViewModel {

    private final MutableLiveData<PlannerModel> plannerData = new MutableLiveData<>(PlannerModel.getInstance());

    public PlannerViewModel() {

        System.out.println("Planner View Model Created");
    }

    public void addPlannerItem(PlannerItem item) {
        PlannerModel.getInstance().addPlannerItemToList(item);
        plannerData.setValue(PlannerModel.getInstance());
    }

    public LiveData<PlannerModel> getPlannerData() {
        return plannerData;
    }

}

