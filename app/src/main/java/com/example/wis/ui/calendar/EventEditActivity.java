package com.example.wis.ui.calendar;

import static com.example.wis.ui.calendar.CalendarUtils.selectedDate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.wis.Data.DataBaseHelper;
import com.example.wis.Data.SharedPref;
import com.example.wis.Models.DeadlineModel;
import com.example.wis.R;
import com.example.wis.ui.login.LoginActivity;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

// Adding new value
public class EventEditActivity extends AppCompatActivity {
    private EditText eventNameET;
    private TextView eventDateTV, eventTimeTV, SubjectNameET;
    private String subject;
    private LocalTime time;
    private DeadlineModel dModel;

    // Set basic values
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);
        initWidgets();
        time = LocalTime.now();
        eventDateTV.setText(CalendarUtils.formattedDate(selectedDate));
        //eventTimeTV.setText(CalendarUtils.formattedTime(time));

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
        SubjectNameET = findViewById(R.id.SubjectET);
        eventDateTV = findViewById(R.id.eventDateTV);
    }

    // Save data
    public void saveEventAction(View view) {
        String eventName = eventNameET.getText().toString();
        String subjectName = SubjectNameET.getText().toString().toUpperCase(Locale.ROOT);

        // Try catch correct time format
        /*DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm");
        try {
            time = LocalTime.parse(eventTimeTV.getText(), formatter);
        } catch (Exception a) {
            try {
                time = LocalTime.parse(eventTimeTV.getText(), formatter2);
            } catch (Exception b) {
                // return;
                time = LocalTime.parse("00:00");
            }
        }*/

        // Check if name isn't empty
        if (eventName.equals("")) {
            Toast.makeText(this, "Jméno termínu je prázdné!", Toast.LENGTH_SHORT).show();
            return;
        }

        DataBaseHelper db = new DataBaseHelper(this);
        Integer user_ID = Integer.valueOf((SharedPref.readSharedSetting(this, "UserID", "-1")));

        //Toast.makeText(this, subject_ID, Toast.LENGTH_SHORT).show();

        Integer subject_ID = db.CheckSubjectShortcut(subjectName, user_ID);
        if (subject_ID == -1) {
            Toast.makeText(this, "Zkratka předmětu neexistuje!", Toast.LENGTH_SHORT).show();
            return;
        }

        dModel = new DeadlineModel();
        dModel.setSubject_id(subject_ID);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedString = selectedDate.format(formatter);
        dModel.setDeadline_time(formattedString);

        dModel.setDeadline_name(eventName);
        db.insertDeadline(dModel);

        // Store data into list
        //Event newEvent = new Event(eventName, selectedDate, time, subjectName);
        //Event.eventsList.add(newEvent);

        // Sort data
        /*Collections.sort(Event.eventsList, new Comparator<Event>() {
            @Override
            public int compare(Event o1, Event o2) {
                return o1.getTime().compareTo(o2.getTime());
            }
        });*/
        finish();
    }
}