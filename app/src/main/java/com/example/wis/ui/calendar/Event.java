package com.example.wis.ui.calendar;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// Functions for events
public class Event {

    // Array for events
    public static ArrayList<Event> eventsList = new ArrayList<>();
    private String name;
    private LocalDate date;
    private LocalTime time;
    private LocalTime time2;

    public Event(String name, LocalDate date, LocalTime time, LocalTime time2) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.time2 = time2;
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

    public static ArrayList<Event> eventsForDateAndTime(LocalDate date, LocalTime time)
    {
        ArrayList<Event> events = new ArrayList<>();

        for(Event event : eventsList) {
            int eventHour = event.time.getHour();
            int eventHour2 = event.time2.getHour();
            int cellHour = time.getHour();
            if (event.getDate().equals(date) && eventHour == cellHour)
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public LocalTime getTime2() {
        return time2;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setTime2(LocalTime time2) {
        this.time2 = time2;
    }
}
