package com.example.wis;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList login;

    TextView logintxt;

    public CustomAdapter(Context context, ArrayList login){
        this.context = context;
        this.login = login;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.myrow,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        logintxt.setText(login.get(position).toString());
        logintxt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View viewIn) {
                Toast.makeText(context,logintxt.getText(),Toast.LENGTH_SHORT).show();
                return;
            }
        });
    }

    @Override
    public int getItemCount() {
        return login.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        //TextView logintxt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            logintxt = itemView.findViewById(R.id.logintxt);
        }

    }
}
