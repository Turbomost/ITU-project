package com.example.wis.ui.calendar;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

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
    public static ArrayList<Event> eventsForDate(LocalDate date) {
        ArrayList<Event> events = new ArrayList<>();

        for (Event event : eventsList) {
            if (event.getDate().equals(date))
                events.add(event);
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
