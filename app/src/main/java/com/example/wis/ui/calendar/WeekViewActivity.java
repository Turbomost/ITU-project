package com.example.wis.ui.calendar;

import static com.example.wis.ui.calendar.CalendarUtils.daysInWeekArray;
import static com.example.wis.ui.calendar.CalendarUtils.monthYearFromDate;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wis.R;
import com.example.wis.databinding.FragmentWeekViewBinding;

import java.time.LocalDate;
import java.util.ArrayList;

public class WeekViewActivity extends Fragment implements CalendarAdapter.OnItemListener {

    Button button_prev;
    Button button_next;
    Button button_new_event;
    View view;

    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private ListView eventListView;
    private com.example.wis.ui.calendar.CalendarViewModel WeekViewModel;
    private FragmentWeekViewBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Get it of calendar items
        view = inflater.inflate(R.layout.fragment_week_view, container, false);
        calendarRecyclerView = (RecyclerView) view.findViewById(R.id.calendarRecyclerView);
        monthYearText = (TextView) view.findViewById(R.id.monthYearTV);
        CalendarUtils.selectedDate = LocalDate.now();
        eventListView =view.findViewById(R.id.eventListView);

        // Initialize the calendar
        super.onCreate(savedInstanceState);
        //tContentView(R.layout.fragment_week_view);
        initWidgets();
        setWeekView();

        button_prev = (Button) view.findViewById(R.id.button_prev);
        button_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousWeekAction(v);
            }
        });

        button_next = (Button) view.findViewById(R.id.button_next);
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextWeekAction(v);
            }
        });

        button_new_event = (Button) view.findViewById(R.id.button_new_event);
        button_new_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EventEditActivity.class);
                startActivity(intent);
            }
        });

        // Bottom navigation
        WeekViewModel =
                new ViewModelProvider(this).get(com.example.wis.ui.calendar.CalendarViewModel.class);

        binding = FragmentWeekViewBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textCalendar;
        WeekViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }

        });

        return view;
    }

    private void initWidgets() {
        calendarRecyclerView = view.findViewById(R.id.calendarRecyclerView);
        monthYearText = view.findViewById(R.id.monthYearTV);
        eventListView =view.findViewById(R.id.eventListView);
    }

    private void setWeekView() {
        monthYearText.setText(monthYearFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> days = daysInWeekArray(CalendarUtils.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(days, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
        setEventAdapter();
    }

    public void previousWeekAction(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusWeeks(1);
        setWeekView();
    }

    public void nextWeekAction(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusWeeks(1);
        setWeekView();
    }

    @Override
    public void onItemClick(int position, LocalDate date) {
        CalendarUtils.selectedDate = date;
        setWeekView();
    }

    @Override
    public void onResume() {
        super.onResume();
        setEventAdapter();
    }

    private void setEventAdapter() {
        ArrayList<Event> dailyEvents = Event.eventsForDate(CalendarUtils.selectedDate);
        EventAdapter eventAdapter = new EventAdapter(getActivity().getApplicationContext(), dailyEvents);
        eventListView.setAdapter(eventAdapter);
    }
}