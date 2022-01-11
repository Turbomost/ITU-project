/*
 * EventEditActivity.java
 * Author     : xvalen29
 * Activity for creating new event
 */
package com.example.wis.ui.calendar;

import static com.example.wis.Models.CalendarModel.selectedDate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.wis.Data.SharedPref;
import com.example.wis.Models.CalendarModel;
import com.example.wis.Models.DeadlineModel;
import com.example.wis.Models.EventModel;
import com.example.wis.R;
import com.example.wis.ui.login.LoginActivity;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

// Adding new value
public class EventEditActivity extends AppCompatActivity {
    private EditText eventNameET;
    private TextView eventDateTV;
    private TextView SubjectNameET;
    private DeadlineModel dModel;

    // Set basic values
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);
        initWidgets();
        eventDateTV.setText(CalendarModel.formattedDate(selectedDate));

        // Set up top toolbar
        Toolbar toolbar = findViewById(R.id.topBar);
        setSupportActionBar(toolbar);

        // Logout event
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

    /**
     * Find TextEdits from view
     */
    private void initWidgets() {
        eventNameET = findViewById(R.id.eventNameET);
        SubjectNameET = findViewById(R.id.SubjectET);
        eventDateTV = findViewById(R.id.eventDateTV);
    }

    /**
     * Get input parameters and create new event
     * @param view View
     */
    public void saveEventAction(View view) {

        // Get parameters
        String eventName = eventNameET.getText().toString();
        String subjectName = SubjectNameET.getText().toString().toUpperCase(Locale.ROOT);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedString = selectedDate.format(formatter);

        String text = EventModel.saveEvent(eventName, formattedString, subjectName, this, dModel);
        if (text != "") {
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
            return;
        }

        finish();
    }
}