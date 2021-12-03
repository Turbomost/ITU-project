package com.example.wis.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wis.DeadlineModel;
import com.example.wis.R;

import java.util.List;

public class DeadlinesAdapter extends RecyclerView.Adapter<DeadlineViewHolder>{

    Context context;
    List<DeadlineModel> deadlineList;


    public DeadlinesAdapter(Context context, List<DeadlineModel> deadlineList){
        this.context = context;
        this.deadlineList = deadlineList;
    }
    @Override
    public int getItemViewType(final int position) {
        return R.layout.list_single_item;
    }
    @NonNull
    @Override
    public DeadlineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(i, parent, false);
        DeadlineViewHolder viewHolder = new DeadlineViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DeadlineViewHolder viewHolder, int position) {
        DeadlineModel deadline = deadlineList.get(position);
        viewHolder.colTime.setText(deadline.getDeadline_time());
        viewHolder.colName.setText(deadline.getDeadline_name());
        viewHolder.colSubject.setText(deadline.getSubject_id());
    }

    @Override
    public int getItemCount() {
        return deadlineList.size();
    }

}
