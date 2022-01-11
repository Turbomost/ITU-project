/*
 * CalendarAdapter.java
 * Author     : xvalen29
 * Adapter for calendar
 * Setting view params and colors
 */

package com.example.wis.ui.calendar;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wis.Data.DataBaseHelper;
import com.example.wis.Data.SharedPref;
import com.example.wis.Models.CalendarModel;
import com.example.wis.Models.EventModel;
import com.example.wis.R;

import java.time.LocalDate;
import java.util.ArrayList;

// Adapter for calendar
public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> {
    private final ArrayList<LocalDate> days;
    private final OnItemListener onItemListener;
    private Context context;

    public CalendarAdapter(ArrayList<LocalDate> days, OnItemListener onItemListener) {
        this.days = days;
        this.onItemListener = onItemListener;
    }

    // Set layout size
    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_cell, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight() * 0.166);
        context = parent.getContext();
        return new CalendarViewHolder(view, onItemListener, days);
    }

    // Set colors for dates with event and selected date
    // Also check for dates outside of current month
    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {

        Integer user_ID = Integer.valueOf((SharedPref.readSharedSetting(context.getApplicationContext(), "UserID", "-1")));
        DataBaseHelper db = new DataBaseHelper(context.getApplicationContext());

        final LocalDate date = days.get(position);
        ArrayList<EventModel> dailyEvents = EventModel.eventsForDate(date, db, user_ID);
        holder.dayOfMonth.setText(String.valueOf(date.getDayOfMonth()));

        // Background color of selected date
        if (date.equals(CalendarModel.selectedDate))
            holder.parentView.setBackgroundColor(Color.LTGRAY);

        // Text color of days in current month
        if (date.getMonth().equals(CalendarModel.selectedDate.getMonth()))
            holder.dayOfMonth.setTextColor(Color.BLACK);

        // Text color of days outside of the current month
        else
            holder.dayOfMonth.setTextColor(Color.LTGRAY);

        // Text color of dates with event
        if (!dailyEvents.isEmpty())
            holder.dayOfMonth.setTextColor(Color.parseColor("#FF2222"));

        // Text color of dates with event in current month
        if (!dailyEvents.isEmpty() && !(date.getMonth().equals(CalendarModel.selectedDate.getMonth())))
            holder.dayOfMonth.setTextColor(Color.parseColor("#FFBBBB"));
    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    // Returns position and selected date on click
    public interface OnItemListener {
        void onItemClick(int position, LocalDate date);
    }
}
