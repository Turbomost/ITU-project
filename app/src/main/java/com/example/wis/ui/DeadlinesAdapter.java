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

import java.util.List;

public class DeadlinesAdapter extends RecyclerView.Adapter<DeadlinesAdapter.ViewHolder> {

    Context context;
    List<DeadlineViewModel> deadlineList;

    TextView colTime;
    TextView colName;
    TextView colSubject;

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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.myrow,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DeadlineViewModel deadline = deadlineList.get(position);
        colTime.setText(String.valueOf(deadline.getDeadline_time()));
        colName.setText(String.valueOf(deadline.getDeadline_name()));
        colSubject.setText(String.valueOf(deadline.getSubject_name()));
    }


    @Override
    public int getItemCount() {
        return deadlineList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            colTime = itemView.findViewById(R.id.item_time);
            colName = itemView.findViewById(R.id.item_name);
            colSubject = itemView.findViewById(R.id.item_subject);
        }
    }

}
