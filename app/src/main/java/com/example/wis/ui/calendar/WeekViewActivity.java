/*
 * WeekViewActivity.java
 * Author     : xvalen29
 * Activity for creating new event
 */

package com.example.wis.ui.calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.wis.Data.DataBaseHelper;
import com.example.wis.Data.SharedPref;
import com.example.wis.Models.LectureViewModel;
import com.example.wis.R;
import com.github.tlaabs.timetableview.Schedule;
import com.github.tlaabs.timetableview.Time;
import com.github.tlaabs.timetableview.TimetableView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

//
// Using OpenSource resource:
// com.github.tlaabs:TimetableView:1.0.3-fx1'
//
public class WeekViewActivity extends Fragment {

    View view;
    private TimetableView timetable;
    private LocalTime time;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Inflate view from fragment
        view = inflater.inflate(R.layout.fragment_week_view, container, false);
        timetable = view.findViewById(R.id.timetable);
        CalendarModel.selectedDate = LocalDate.now();

        // Load info from database
        Integer user_ID = Integer.valueOf((SharedPref.readSharedSetting(getContext(), "UserID", "-1")));
        DataBaseHelper db = new DataBaseHelper(getContext());
        List<LectureViewModel> list = new ArrayList<>();

        // Get list of lectures for given user
        list = db.getAllUserLecturesP(user_ID);
        if (list.size() > 0) {
            viewTimetable(list, timetable);
        }

        // Get list of exercises for given user
        list = db.getAllUserLecturesC(user_ID);
        if (list.size() > 0) {
            viewTimetable(list, timetable);
        }
        return view;
    }

    /**
     *
     * Fill timetable with data from given list
     * Whole list is considered as one subject hence it has the same color
     *
     * @param list      input list of lectures
     * @param timetable output timetable
     */
    private void viewTimetable(List<LectureViewModel> list, TimetableView timetable) {

        // Create new list for subject
        ArrayList<Schedule> schedules = new ArrayList<Schedule>();
        for (int i = 0; i < list.size(); i++) {
            Schedule schedule = new Schedule();
            LectureViewModel Item = list.get(i);

            // DAY
            schedule.setDay(WeekViewModel.dayToInt(Item.getLecture_day()));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");

            // TYPE
            schedule.setClassTitle(Item.getSubject_shortcut());
            if (Item.getLecture_type().equals("c")) {
                schedule.setClassPlace("Cvičení");
            } else {
                schedule.setClassPlace("Přednáška");
            }

            // TIME START
            time = LocalTime.parse(Item.getLecture_start(), formatter);
            schedule.setStartTime(new Time(time.getHour(), time.getMinute()));

            // TIME END
            time = LocalTime.parse(Item.getLecture_end(), formatter);
            schedule.setEndTime(new Time(time.getHour(), time.getMinute()));

            schedules.add(schedule);
        }
        timetable.add(schedules);
    }

}