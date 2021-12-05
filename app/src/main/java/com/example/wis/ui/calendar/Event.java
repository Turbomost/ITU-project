/*
 * Event.java
 * Author     : xvalen29
 * Set up Event class
 */

package com.example.wis.ui.calendar;

import com.example.wis.Data.DataBaseHelper;
import com.example.wis.Models.DeadlineModel;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// Functions for events
public class Event {

    // ArrayList for events
    public static ArrayList<Event> eventsList = new ArrayList<>();
    private String name;
    private LocalDate date;
    private LocalTime time;
    private String subject;

    // Time is no longer being used
    public Event(String name, LocalDate date, LocalTime time, String subject) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.subject = subject;
    }

    /**
     * Returns ArrayList of events for given day
     * @param date    selected date
     * @param db      database
     * @param user_ID actual user ID
     * @return ArrayList
     */
    public static ArrayList<Event> eventsForDate(LocalDate date, DataBaseHelper db, Integer user_ID) {
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
                    !(LocalDate.parse(model.getDeadline_time(), formatter).isBefore(LocalDate.now()))) {
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

    // Functions for returning and settings parameters

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
