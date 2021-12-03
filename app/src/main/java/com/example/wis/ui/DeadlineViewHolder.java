package com.example.wis.ui;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wis.R;

public class DeadlineViewHolder  extends RecyclerView.ViewHolder {

    TextView colTime;
    TextView colName;
    TextView colSubject;
    public DeadlineViewHolder(@NonNull View itemView) {
        super(itemView);
        colTime = itemView.findViewById(R.id.item_time);
        colName = itemView.findViewById(R.id.item_name);
        colSubject = itemView.findViewById(R.id.item_subject);
    }

}
