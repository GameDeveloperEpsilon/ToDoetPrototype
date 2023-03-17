package com.example.todoetprototype.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.todoetprototype.AddNewTask;
import com.example.todoetprototype.PlannerActivity;
import com.example.todoetprototype.Model.ToDoModel;
import com.example.todoetprototype.R;
import com.example.todoetprototype.Utils.DatabaseHandler;
import com.example.todoetprototype.inventory.UserModel;
import com.example.todoetprototype.inventory.UserViewModel;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {

    private List<ToDoModel> todoList;
    private PlannerActivity activity;
    private DatabaseHandler db;
    private UserViewModel userViewModel;

    public ToDoAdapter(DatabaseHandler db, PlannerActivity activity){
        this.db = db;
        this.activity = activity;
        this.userViewModel = activity.getUserViewModel();
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_layout, parent, false);
        return new ViewHolder(itemView);
    }

    // standard implementation of recycler view adaptor

    public void onBindViewHolder(ViewHolder holder, int position){
        db.openDatabase(); // open database
        ToDoModel item = todoList.get(position); // in the todolist you get the item
        holder.task.setText(item.getTask()); // set the task from the item position
        holder.task.setChecked(toBoolean(item.getStatus())); // checks the status of the item if it is checked or not
        holder.task.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                db.updateStatus(item.getId(),1);
                UserModel userModel = userViewModel.getUserData().getValue();
                if (userModel != null)
                    userViewModel.updateUser(userModel.getCoins() + 1);
                else
                    System.err.println("ToDoAdapter.onCheckedChanged : userModel is null!");
            }
            else{
                db.updateStatus(item.getId(),0);
            }
        });
        holder.dueDate.setText(item.getDate());  // Set the due date of the task.
    }

    // Sets the getItem count, this will let it know how many items it needs to print

    public int getItemCount(){
        return todoList.size();
    }

    // Since the checkmark is boolean type, need to convert to boolean
    private boolean toBoolean(int n){
        return n!=0;
    }

    // For dummy data

    public void setTask(List<ToDoModel> todoList){
        this.todoList = todoList;
        notifyDataSetChanged(); // so recycler view is updated
    }

    public Context getContext(){
        return activity;
    }

    // Delete item from activity
    public void deleteItem(int position) {
        ToDoModel item = todoList.get(position);
        db.deleteTask(item.getId()); // id of item being deleted
        todoList.remove(position); //remove from the item
        notifyItemRemoved(position); // notifies that the item will be removed and will automatically update view
    }

    // Update edited items
    public void editItem(int position){
        ToDoModel item = todoList.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("id",item.getId());
        bundle.putString("task", item.getTask());
        AddNewTask fragment = new AddNewTask();
        fragment.setArguments(bundle);
        fragment.show(activity.getSupportFragmentManager(), AddNewTask.TAG);
    }

    // This class represents the task_layout layout. This is how a task appears to the user.
    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox task;
        TextView dueDate;

        ViewHolder(View view){
            super(view);
            task = view.findViewById(R.id.todoCheckBox);
            dueDate = view.findViewById(R.id.dueDate);
        }
    }
}

