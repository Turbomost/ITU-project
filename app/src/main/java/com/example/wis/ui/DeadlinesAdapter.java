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
    List<DeadlineViewModel> deadlineList;
    private ArrayList time,name,subject;
    TextView colTime;
    TextView colName;
    TextView colSubject;

    public DeadlinesAdapter(Context context, ArrayList time, ArrayList name, ArrayList subject){
        this.context = context;
        this.time = time;
        this.name = name;
        this.subject = subject;
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

        colTime.setText(String.valueOf(time.get(position)));
        colName.setText(String.valueOf(name.get(position)));
        colSubject.setText(String.valueOf(subject.get(position)));
    }


    @Override
    public int getItemCount() {
        return time.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            colTime = itemView.findViewById(R.id.nametxt);
            colName = itemView.findViewById(R.id.timetxt);
            colSubject = itemView.findViewById(R.id.subjecttxt);
        }
    }

}
