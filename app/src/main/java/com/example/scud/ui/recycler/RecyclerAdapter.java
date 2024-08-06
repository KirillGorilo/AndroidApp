package com.example.scud.ui.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scud.R;
import com.example.scud.model.UsersList;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private List<UsersList> dataList;

    public RecyclerAdapter(List<UsersList> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        UsersList data = dataList.get(position);
        holder.textView.setText(data.getUsername());
        holder.textViewEmail.setText(data.getEmail());
        holder.description.setText(data.getDescription());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void setData(List<UsersList> dataList) {
        this.dataList.clear();
        this.dataList.addAll(dataList);
        notifyItemRangeChanged(0, dataList.size());
    }

    public void addData(UsersList newData) {
        this.dataList.add(newData);
        notifyItemInserted(dataList.size() - 1);
    }

    public void updateData(int position, UsersList updatedData) {
        if (position >= 0 && position < dataList.size()) {
            this.dataList.set(position, updatedData);
            notifyItemChanged(position);
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textViewEmail;
        TextView description;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewLogin);
            textViewEmail = itemView.findViewById(R.id.textViewEmail);
            description = itemView.findViewById(R.id.textViewDescription);
        }
    }
}
