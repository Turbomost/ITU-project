package com.example.wis.ui.home;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View.OnClickListener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wis.R;
import com.example.wis.ui.home.Excercise.ExcerciseActivity;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList login;
    RecyclerView recyclerView;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView logintxt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
           // item = (LinearLayout)itemView.findViewById(R.id.home_item_id);
            logintxt = itemView.findViewById(R.id.logintxt);

        }
    }

    public CustomAdapter(Context context, ArrayList login, RecyclerView recyclerView){
        this.context = context;
        this.login = login;
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
        holder.logintxt.setText(login.get(position).toString());

        holder.logintxt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View viewIn) {

                CharSequence c = holder.logintxt.getText().toString();
                Intent intent = new Intent(context, ExcerciseActivity.class);
                intent.putExtra("subject_name",c);
                context.startActivity(intent);

                return;
            }
        });


    }

    @Override
    public int getItemCount() {
        return login.size();
    }


}
