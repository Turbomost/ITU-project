package com.example.wis.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wis.DeadlineModel;
import com.example.wis.DeadlineViewModel;
import com.example.wis.R;

import java.util.List;

public class DeadlinesAdapter extends RecyclerView.Adapter<DeadlineViewHolder>{

    Context context;
    List<DeadlineViewModel> deadlineList;


    public DeadlinesAdapter(Context context, List<DeadlineViewModel> deadlineList){
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
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.myrow,parent,false);
        DeadlineViewHolder viewHolder = new DeadlineViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DeadlineViewHolder viewHolder, int position) {
        DeadlineViewModel deadline = deadlineList.get(position);
        viewHolder.colTime.setText(String.valueOf(deadline.getDeadline_time()));
        viewHolder.colName.setText(String.valueOf(deadline.getDeadline_name()));
        viewHolder.colSubject.setText(String.valueOf(deadline.getSubject_name()));
    }

    @Override
    public int getItemCount() {
        return deadlineList.size();
    }

}
