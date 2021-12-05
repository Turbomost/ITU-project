/*
 * CalendarViewHolder.java
 * Author     : xvalen29
 * Calendar ViewHolder
 * Handles OnClickListener
 */

package com.example.wis.ui.calendar;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wis.R;

import java.time.LocalDate;
import java.util.ArrayList;

// ViewHolder for calendar
public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public final View parentView;
    public final TextView dayOfMonth;
    private final ArrayList<LocalDate> days;
    private final CalendarAdapter.OnItemListener onItemListener;

    // ViewHolder for Calendar
    public CalendarViewHolder(@NonNull View itemView, CalendarAdapter.OnItemListener onItemListener, ArrayList<LocalDate> days) {

        super(itemView);
        parentView = itemView.findViewById(R.id.parentView);
        dayOfMonth = itemView.findViewById(R.id.cellDayText);

        this.days = days;
        this.onItemListener = onItemListener;

        itemView.setOnClickListener(this);
    }

    // Calls onItemClick function on actual position
    @Override
    public void onClick(View view) {
        onItemListener.onItemClick(getAdapterPosition(), days.get(getAdapterPosition()));
    }
}
