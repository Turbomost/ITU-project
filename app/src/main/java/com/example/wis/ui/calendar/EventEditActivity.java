package com.example.wis.ui.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.wis.ui.login.LoginActivity;
import com.example.wis.R;
import com.example.wis.Data.SharedPref;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;

// Adding new value
public class EventEditActivity extends AppCompatActivity {
    private EditText eventNameET;
    private TextView eventDateTV, eventTimeTV, eventTimeTV2;

    private LocalTime time;

    // Set basic values
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);
        initWidgets();
        time = LocalTime.now();
        eventDateTV.setText("Date: " + CalendarUtils.formattedDate(CalendarUtils.selectedDate));
        eventTimeTV.setText(CalendarUtils.formattedTime(time));

        Toolbar toolbar = findViewById(R.id.topBar);
        setSupportActionBar(toolbar);

        ImageButton btn_logout = (ImageButton) findViewById(R.id.imageButton2);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPref.saveSharedSetting(EventEditActivity.this, "UserID", Integer.toString(-1));
                Intent intent = new Intent(EventEditActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    // Link objects
    private void initWidgets() {
        eventNameET = findViewById(R.id.eventNameET);
        eventDateTV = findViewById(R.id.eventDateTV);
        eventTimeTV = findViewById(R.id.eventTimeTV);
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

        // Check if name isn't empty
        if (eventName.equals("")) {
            Toast.makeText(this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        // Store data into list
        Event newEvent = new Event(eventName, CalendarUtils.selectedDate, time);
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