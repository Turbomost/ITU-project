/*
 * EventAdapter.java
 * Author     : xvalen29
 * Adapter for events
 * Printing events in calendar
 */

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

import com.example.wis.Models.EventModel;
import com.example.wis.R;

import java.util.List;

// Adapter for events
public class EventAdapter extends ArrayAdapter<EventModel> {
    public EventAdapter(@NonNull Context context, List<EventModel> events) {
        super(context, 0, events);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        EventModel event = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_cell, parent, false);

        TextView eventCellTV = convertView.findViewById(R.id.eventCellTV);
        TextView subjectCellTV = convertView.findViewById(R.id.SubjectCellTV);

        // Print subject and name
        subjectCellTV.setText(event.getSubject());
        String eventTitle = " \t <font color=#00A9E0><b>" + event.getName() + "</b></font>";

        eventCellTV.setText(Html.fromHtml(eventTitle));
        return convertView;
    }
}
