/*
 * Event.java
 * Author     : xvalen29
 * Set up Event class
 */

package com.example.wis.Models;

import android.app.Activity;

import com.example.wis.Data.DataBaseHelper;
import com.example.wis.Data.SharedPref;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// Functions for events
public class EventModel {

    // ArrayList for events
    public static ArrayList<EventModel> eventsList = new ArrayList<>();
    private String name;
    private LocalDate date;
    private LocalTime time;
    private String subject;

    // Time is no longer being used
    public EventModel(String name, LocalDate date, LocalTime time, String subject) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.subject = subject;
    }

    /**
     * Returns ArrayList of events for given day
     *
     * @param date    selected date
     * @param db      database
     * @param user_ID actual user ID
     * @return ArrayList
     */
    public static ArrayList<EventModel> eventsForDate(LocalDate date, DataBaseHelper db, Integer user_ID) {
        ArrayList<EventModel> events = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedString = date.format(formatter);
        List<DeadlineModel> dList;
        dList = db.getUserDeadlinesList(user_ID, formattedString);

        for (EventModel event : eventsList) {
            if (event.getDate().equals(date))
                events.add(event);
        }

        for (DeadlineModel model : dList) {
            if (db.CheckDeadlineActivity(model.getDeadline_id()) &&
                    !(LocalDate.parse(model.getDeadline_time(), formatter).isBefore(LocalDate.now()))) {
                EventModel event = new EventModel(
                        model.getDeadline_name(),
                        LocalDate.parse(model.getDeadline_time(), formatter),
                        LocalTime.MIDNIGHT,
                        db.getSubjectName(model.getSubject_id()));
                events.add(event);
            }
        }

        return events;
    }

    /**
     * Get input parameters and create new event
     */
    public static String saveEvent(String eventName, String formattedString,
                                   String subjectName, Activity current, DeadlineModel dModel) {

        // Check if name isn't empty
        if (eventName.equals("")) {
            return "Jméno termínu je prázdné!";
        }

        // Connect to database
        DataBaseHelper db = new DataBaseHelper(current);
        Integer user_ID = Integer.valueOf((SharedPref.readSharedSetting(current, "UserID", "-1")));
        Integer subject_ID = db.CheckSubjectShortcut(subjectName, user_ID);

        // Check if subject exists
        if (subject_ID == -1) {
            return "Zkratka předmětu neexistuje!";
        }

        // Set up new DeadlineModel
        dModel = new DeadlineModel();
        dModel.setSubject_id(subject_ID);
        dModel.setDeadline_time(formattedString);
        dModel.setDeadline_name(eventName);
        db.insertDeadline(dModel);

        return "";
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
