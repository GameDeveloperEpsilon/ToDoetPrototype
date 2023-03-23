package com.example.todoetprototype.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoetprototype.R;
import com.example.todoetprototype.store.StoreActivity;
import com.example.todoetprototype.store.StoreModel;
import com.example.todoetprototype.utils.DatabaseHandler;

import java.util.List;

public class StoreItemAdapter extends RecyclerView.Adapter<StoreItemAdapter.ViewHolder> {

    private List<StoreModel> storeItemList;
    private StoreActivity activity;
    private DatabaseHandler db;
    //private UserViewModel userViewModel;

    public StoreItemAdapter(DatabaseHandler db, StoreActivity activity) {
        this.db = db;
        this.activity = activity;
    }

    public void setStoreItemList(List<StoreModel> storeItemList) {
        this.storeItemList = storeItemList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.store_item_layout, parent, false);
        return new StoreItemAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StoreModel item = storeItemList.get(position); // in the todolist you get the item
        holder.storeItemName.setText(item.getItemName()); // set the task from the item position
    }

    @Override
    public int getItemCount() {
        return storeItemList.size();
    }

    /**
     * This class represents the store_item_layout layout. This is how a store item appears to the
     * user.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        // Item name
        TextView storeItemName;
        // Item cost

        ViewHolder(View view){
            super(view);
            storeItemName = view.findViewById(R.id.storeItemName);
        }
    }
}
