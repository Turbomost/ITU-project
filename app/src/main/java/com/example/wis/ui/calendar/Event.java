package com.example.wis.ui.calendar;

import static com.example.wis.ui.calendar.CalendarUtils.selectedDate;

import com.example.wis.Data.DataBaseHelper;
import com.example.wis.Data.SharedPref;
import com.example.wis.Models.DeadlineModel;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// Functions for events
public class Event {

    // Array for events
    public static ArrayList<Event> eventsList = new ArrayList<>();
    private String name;
    private LocalDate date;
    private LocalTime time;
    private String subject;

    public Event(String name, LocalDate date, LocalTime time, String subject) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.subject = subject;
    }

    // Return list of events for given date

    public static ArrayList<Event> eventsForDate(LocalDate date,DataBaseHelper db, Integer user_ID){
        ArrayList<Event> events = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedString = date.format(formatter);
        List<DeadlineModel> dList;
        dList = db.getUserDeadlinesList(user_ID, formattedString);

        for (Event event : eventsList) {
            if (event.getDate().equals(date))
                events.add(event);
        }

        for (DeadlineModel model : dList) {
            if (db.CheckDeadlineActivity(model.getDeadline_id()) &&
                    LocalDate.parse(model.getDeadline_time(), formatter).isAfter(LocalDate.now())) {
                Event event = new Event(
                        model.getDeadline_name(),
                        LocalDate.parse(model.getDeadline_time(), formatter),
                        LocalTime.MIDNIGHT,
                        db.getSubjectName(model.getSubject_id()));
                events.add(event);
            }
        }

        return events;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
