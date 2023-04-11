//package com.example.todoetprototype.planner;
//
//import com.example.todoetprototype.utils.DatabaseHandler;
//
//import java.util.LinkedList;
//import java.util.List;
//
//public class PlannerModel {
//
//    private static PlannerModel instance = null;
//
//    public static PlannerModel getInstance() {
//        if (instance == null)
//            instance = new PlannerModel();
//        return instance;
//    }
//
//    private final List<PlannerItem> plannerItems;
//
//    private PlannerModel() {
//        this.plannerItems = new LinkedList<>();
//    }
//
//    public void loadData(PlannerActivity context) {
//        try (DatabaseHandler databaseHandler = new DatabaseHandler(context)) {
//            databaseHandler.loadAllTasks();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public List<PlannerItem> getPlannerItems() {
//        return plannerItems;
//    }
//
//    public void addPlannerItemToList(PlannerItem item) {
//        plannerItems.add(item);
//    }
//
//    public void removePlannerItemFromList(PlannerItem item) {
//        plannerItems.remove(item);
//    }
//}
