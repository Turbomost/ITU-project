package com.example.wis;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList login;
    RecyclerView recyclerView;
    //final View.OnClickListener onClickListener = new MyOnClickListener();


    public class MyViewHolder extends RecyclerView.ViewHolder {
       // private LinearLayout item;
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
    /*    holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"text click " + String.valueOf(holder.getAdapterPosition()),Toast.LENGTH_SHORT).show();

            }
        });

     */
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.logintxt.setText(login.get(position).toString());

        holder.logintxt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View viewIn) {
                Toast.makeText(context,holder.logintxt.getText(),Toast.LENGTH_SHORT).show();
                return;
            }
        });


    }

    @Override
    public int getItemCount() {
        return login.size();
    }

/*
    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int itemPosition = recyclerView.getChildAdapterPosition(v);
            String item = login.get(itemPosition).toString();
            Toast.makeText(context, item, Toast.LENGTH_SHORT).show();
        }
    }
    */

}
