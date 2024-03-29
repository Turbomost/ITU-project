/*
 * CustomAdapter.java
 * Author     : xzimme03
 * Adapter for Recycle view
 */
package com.example.wis.ui.home;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wis.Data.DataBaseHelper;
import com.example.wis.Data.SharedPref;
import com.example.wis.R;
import com.example.wis.ui.home.Excercise.ExcerciseActivity;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList subject;
    RecyclerView recyclerView;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView subjecttxt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
           // item = (LinearLayout)itemView.findViewById(R.id.home_item_id);
            subjecttxt = itemView.findViewById(R.id.subjecttxt);

        }
    }

    public CustomAdapter(Context context, ArrayList subject, RecyclerView recyclerView){
        this.context = context;
        this.subject = subject;
        this.recyclerView = recyclerView;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.myrow,parent,false);
        final MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.subjecttxt.setText(subject.get(position).toString());

        holder.subjecttxt.setOnClickListener(new OnClickListener() {
            // Transfering to a new tab with execise when clicking on subject with data stored in intent variable
            @Override
            public void onClick(View viewIn) {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(context.getApplicationContext());
                CharSequence c = holder.subjecttxt.getText().toString();
                int subject_id = dataBaseHelper.getSubjectId(String.valueOf(c));
                Integer user_ID= Integer.valueOf((SharedPref.readSharedSetting(context, "UserID", "-1")));
                if(dataBaseHelper.getAllUserLecturesCount( user_ID, subject_id)){
                    Intent intent = new Intent(context, ExcerciseActivity.class);
                    intent.putExtra("subject_name", c);
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, "Předmět nemá cvičení", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return subject.size();
    }


}
