/*
 * Author     : xbella01
 */
package com.example.wis.ui.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wis.Data.DataBaseHelper;
import com.example.wis.Models.DeadlineViewModel;
import com.example.wis.R;
import com.example.wis.Data.SharedPref;

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
        DataBaseHelper db = new DataBaseHelper(context.getApplicationContext());
        Integer user_ID= Integer.valueOf((SharedPref.readSharedSetting(context, "UserID", "-1")));
        DeadlineViewModel item = deadlinelist.get(position);
        holder.colTime.setText(String.valueOf(item.getDeadline_time()));
        holder.colName.setText(String.valueOf(item.getDeadline_name()));
        holder.colSubject.setText(String.valueOf(item.getSubject_name()));
        holder.cbDone.setChecked(toBoolean(item.getDeadline_status()));
        holder.cbDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    db.updateUserDeadlineStatus(user_ID ,item.getDeadline_id(),1);
                } else {
                    db.updateUserDeadlineStatus(user_ID ,item.getDeadline_id(),0);
                }

            }
        });
    }

    private boolean toBoolean(int n){
        return n!=0;
    }

    @Override
    public int getItemCount() {
        return deadlinelist.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView colTime;
        TextView colName;
        TextView colSubject;
        CheckBox cbDone;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            colName = itemView.findViewById(R.id.nametxt);
            colTime = itemView.findViewById(R.id.timetxt);
            colSubject = itemView.findViewById(R.id.subjecttxt);
            cbDone = itemView.findViewById(R.id.cb_done);
        }
    }

}
