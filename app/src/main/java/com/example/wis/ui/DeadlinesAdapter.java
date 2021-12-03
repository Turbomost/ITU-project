package com.example.wis.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wis.CustomAdapter;
import com.example.wis.DeadlineModel;
import com.example.wis.DeadlineViewModel;
import com.example.wis.R;

import java.util.ArrayList;
import java.util.List;

public class DeadlinesAdapter extends RecyclerView.Adapter<DeadlinesAdapter.ViewHolder> {

    Context context;
    List<DeadlineViewModel> deadlinelist;


    public DeadlinesAdapter(Context context, List<DeadlineViewModel> deadlinelist){
        this.context = context;
        this.deadlinelist = deadlinelist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_single_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.colTime.setText(String.valueOf(deadlinelist.get(position).getDeadline_time()));
        holder.colName.setText(String.valueOf(deadlinelist.get(position).getDeadline_name()));
        holder.colSubject.setText(String.valueOf(deadlinelist.get(position).getSubject_name()));
    }


    @Override
    public int getItemCount() {
        return deadlinelist.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView colTime;
        TextView colName;
        TextView colSubject;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            colName = itemView.findViewById(R.id.nametxt);
            colTime = itemView.findViewById(R.id.timetxt);
            colSubject = itemView.findViewById(R.id.subjecttxt);
        }
    }

}
