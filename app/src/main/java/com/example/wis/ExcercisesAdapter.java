package com.example.wis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ExcercisesAdapter extends RecyclerView.Adapter<ExcercisesAdapter.ViewHolder> {

    Context context;
    List<ExcerciseViewModel> excerciselist;


    public ExcercisesAdapter(Context context, List<ExcerciseViewModel> excerciselist){
        this.context = context;
        this.excerciselist = excerciselist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.excercise_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataBaseHelper db = new DataBaseHelper(context.getApplicationContext());
        Integer user_ID= Integer.valueOf((SharedPref.readSharedSetting(context, "UserID", "-1")));
        ExcerciseViewModel item = excerciselist.get(position);
        holder.colStart.setText(String.valueOf(item.getExcercise_start()));
        holder.colEnd.setText(String.valueOf(item.getExcercise_end()));
        holder.colName.setText(String.valueOf(item.getExcercise_name()));
        holder.cbDone.setChecked(toBoolean(item.getExcercise_status()));
        holder.cbDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    db.updateUserLectureStatus( user_ID, item.getExcercise_id(), 1);

                } else {
                    db.updateUserLectureStatus(user_ID ,item.getExcercise_id(),0);
                }

            }
        });
    }

    private boolean toBoolean(int n){
        return n!=0;
    }

    @Override
    public int getItemCount() {
        return excerciselist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView colStart;
        TextView colEnd;
        TextView colName;
        CheckBox cbDone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            colStart = itemView.findViewById(R.id.starttxt);
            colEnd = itemView.findViewById(R.id.endtxt);
            colName = itemView.findViewById(R.id.nametxt);
            cbDone = itemView.findViewById(R.id.cb_done);
        }
    }

}