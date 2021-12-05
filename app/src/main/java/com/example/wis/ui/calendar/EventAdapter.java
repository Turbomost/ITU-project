package com.example.wis.ui.calendar;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.wis.R;

import java.util.List;

// Adapter for events
public class EventAdapter extends ArrayAdapter<Event> {
    public EventAdapter(@NonNull Context context, List<Event> events) {
        super(context, 0, events);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Event event = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_cell, parent, false);

        TextView eventCellTV = convertView.findViewById(R.id.eventCellTV);
        TextView subjectCellTV = convertView.findViewById(R.id.SubjectCellTV);

        String part1 = "";
        // Printing format for events
        if (!event.getSubject().equals("")) {
            part1 = event.getSubject();
        }
        subjectCellTV.setText(part1);
        String eventTitle =
                "<font color=#000000>" + CalendarUtils.formattedTime(event.getTime()) + " - \t</font>" +
                "<font color=#00A9E0><b>" + event.getName() + "</b></font>";

        eventCellTV.setText(Html.fromHtml(eventTitle));
        return convertView;
    }
}
