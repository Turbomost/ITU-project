package com.example.wis.ui.calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.wis.R;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HourAdapter extends ArrayAdapter<HourEvent> {

    public HourAdapter(@NonNull Context context, List<HourEvent> hourEvents) {
        super(context, 0, hourEvents);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        HourEvent event = getItem(position);

        if (convertView == null) {
        }
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.hour_cell, parent, false);

        setHour(convertView, event.time);
        setEvents(convertView, event.events);

        return convertView;
    }

    private void setHour(View convertView, LocalTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        TextView timeTV = convertView.findViewById(R.id.timeTV);
        timeTV.setText(time.format(formatter));
        getContext();
    }

    private void setEvents(View convertView, ArrayList<Event> events) {
        TextView event1 = convertView.findViewById(R.id.event1);
        TextView event2 = convertView.findViewById(R.id.event2);

        if (events.size() == 0) {
            hideEvent(event1);
            hideEvent(event2);
        } else if (events.size() == 1) {
            hideEvent(event1);
            setEvent(event2, events.get(0));
        } else {
            setEvent(event1, events.get(0));
            setEvent(event2, events.get(1));
        }
    }

    private void setEvent(TextView textView, Event event) {
        textView.setText(event.getName());
        textView.setVisibility(View.VISIBLE);
    }

    private void hideEvent(TextView tv) {
        tv.setVisibility(View.INVISIBLE);
    }
}
