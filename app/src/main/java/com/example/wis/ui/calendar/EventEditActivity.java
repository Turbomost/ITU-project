package com.example.wis.ui.calendar;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wis.R;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;

// Adding new value
public class EventEditActivity extends AppCompatActivity {
    private EditText eventNameET;
    private TextView eventDateTV, eventTimeTV, eventTimeTV2;

    private LocalTime time;
    private LocalTime time2;

    // Set basic values
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);
        initWidgets();
        time = LocalTime.now();
        time2 = LocalTime.now().plusHours(1);
        eventDateTV.setText("Date: " + CalendarUtils.formattedDate(CalendarUtils.selectedDate));
        eventTimeTV.setText(CalendarUtils.formattedTime(time));
        eventTimeTV2.setText(CalendarUtils.formattedTime(time2));
    }

    // Link objects
    private void initWidgets() {
        eventNameET = findViewById(R.id.eventNameET);
        eventDateTV = findViewById(R.id.eventDateTV);
        eventTimeTV = findViewById(R.id.eventTimeTV);
        eventTimeTV2 = findViewById(R.id.eventTimeTV2);
    }

    // Save data
    public void saveEventAction(View view) {
        String eventName = eventNameET.getText().toString();

        // Try catch correct time format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm");
        try {
            time = LocalTime.parse(eventTimeTV.getText(), formatter);
        } catch (Exception a) {
            try {
                time = LocalTime.parse(eventTimeTV.getText(), formatter2);
            } catch (Exception b) {
                Toast.makeText(this, "Invalid time format.", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        try {
            time2 = LocalTime.parse(eventTimeTV2.getText(), formatter);
        } catch (Exception a) {
            try {
                time2 = LocalTime.parse(eventTimeTV2.getText(), formatter2);
            } catch (Exception b) {
                Toast.makeText(this, "Invalid time format.", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        // Check correct time area
        if (time.compareTo(time2) > 0) {
            Toast.makeText(this, "Event is within more than one day", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if name isn't empty
        if (eventName.equals("")) {
            Toast.makeText(this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        // Store data into list
        Event newEvent = new Event(eventName, CalendarUtils.selectedDate, time, time2);
        Event.eventsList.add(newEvent);

        // Sort data
        Collections.sort(Event.eventsList, new Comparator<Event>() {
            @Override
            public int compare(Event o1, Event o2) {
                return o1.getTime().compareTo(o2.getTime());
            }
        });
        finish();
    }
}