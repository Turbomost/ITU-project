package com.example.wis.ui.calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wis.Data.DataBaseHelper;
import com.example.wis.Data.SharedPref;
import com.example.wis.Models.LectureViewModel;
import com.example.wis.R;
import com.example.wis.databinding.FragmentWeekViewBinding;
import com.github.tlaabs.timetableview.Schedule;
import com.github.tlaabs.timetableview.Time;
import com.github.tlaabs.timetableview.TimetableView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class WeekViewActivity extends Fragment {

    Button button_prev;
    Button button_next;
    Button button_new_event;
    View view;

    private TimetableView timetable;
    private androidx.gridlayout.widget.GridLayout hourListView;
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private ListView eventListView;
    private com.example.wis.ui.calendar.CalendarViewModel WeekViewModel;
    private FragmentWeekViewBinding binding;
    private LocalTime time;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Get it of calendar items
        view = inflater.inflate(R.layout.fragment_week_view, container, false);
        CalendarUtils.selectedDate = LocalDate.now();
        timetable = view.findViewById(R.id.timetable);

        Integer user_ID = Integer.valueOf((SharedPref.readSharedSetting(getContext(), "UserID", "-1")));
        DataBaseHelper db = new DataBaseHelper(getContext());

        List<LectureViewModel> list = new ArrayList<>();

        list = db.getAllUserLecturesP(user_ID);
        if (list.size() > 0) {
            viewTimetable(list, timetable);
        }

        list = db.getAllUserLecturesC(user_ID);

        if (list.size() > 0) {
            viewTimetable(list, timetable);
        }
        return view;
    }

    private void viewTimetable(List<LectureViewModel> list, TimetableView timetable) {
        ArrayList<Schedule> schedules = new ArrayList<Schedule>();
        for (int i = 0; i < list.size(); i++) {
            Schedule schedule = new Schedule();
            LectureViewModel Item = list.get(i);

            schedule.setDay(dayToInt(Item.getLecture_day()));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");

            schedule.setClassTitle(Item.getSubject_shortcut());
            if (Item.getLecture_type().equals("c")) {
                schedule.setClassPlace("Cvičení");
            }
            else{
                schedule.setClassPlace("Přednáška");
            }

            time = LocalTime.parse(Item.getLecture_start(), formatter);
            schedule.setStartTime(new Time(time.getHour(), time.getMinute()));

            time = LocalTime.parse(Item.getLecture_end(), formatter);
            schedule.setEndTime(new Time(time.getHour(), time.getMinute()));

            schedules.add(schedule);
        }
        timetable.add(schedules);
    }

    private int dayToInt(String lecture_day) {
        switch (lecture_day) {
            case "Mon":
                return 0;
            case "Tue":
                return 1;
            case "Wed":
                return 2;
            case "Thu":
                return 3;
            case "Fri":
                return 4;
            default:
                return 7;
        }
    }
}