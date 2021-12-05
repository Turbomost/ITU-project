/*
 * CalendarAdapter.java
 * Author     : xvalen29
 * Fragment showing monthly calendar
 * Showing unfinished events for selected date and highlight dates with events
 */

package com.example.wis.ui.calendar;

import static com.example.wis.ui.calendar.CalendarUtils.daysInMonthArray;
import static com.example.wis.ui.calendar.CalendarUtils.monthYearFromDate;
import static com.example.wis.ui.calendar.CalendarUtils.selectedDate;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wis.Data.DataBaseHelper;
import com.example.wis.Data.SharedPref;
import com.example.wis.R;
import com.example.wis.databinding.FragmentCalendarBinding;

import java.time.LocalDate;
import java.util.ArrayList;

// Fragment for monthly calendar
public class CalendarFragment extends Fragment implements CalendarAdapter.OnItemListener {

    View view;
    Button button_prev;
    Button button_next;
    Button button_new_event;

    private com.example.wis.ui.calendar.CalendarViewModel calendarViewModel;
    private FragmentCalendarBinding binding;
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private ListView eventListView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Inflater for work with fragment
        view = inflater.inflate(R.layout.fragment_calendar, container, false);

        // Get IDs
        calendarRecyclerView = (RecyclerView) view.findViewById(R.id.calendarRecyclerView);
        monthYearText = (TextView) view.findViewById(R.id.monthYearTV);
        eventListView = view.findViewById(R.id.eventListView);

        // Initialize the calendar
        CalendarUtils.selectedDate = LocalDate.now();
        super.onCreate(savedInstanceState);
        initWidgets();
        setMonthView();

        // Set up buttons clicks
        // PREVIOUS MONTH
        button_prev = (Button) view.findViewById(R.id.button_prev);
        button_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousMonthAction(v);
            }
        });

        // NEXT MONTH
        button_next = (Button) view.findViewById(R.id.button_next);
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextMonthAction(v);
            }
        });

        // Call create new event activity
        button_new_event = (Button) view.findViewById(R.id.button_new_event);
        button_new_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedDate.isBefore(LocalDate.now())) {
                    Toast.makeText(getContext(), "Nelze přidat termín do minulosti!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getActivity(), EventEditActivity.class);
                    startActivity(intent);
                }
            }
        });

        // Bottom navigation
        calendarViewModel = new ViewModelProvider(this).get(com.example.wis.ui.calendar.CalendarViewModel.class);
        binding = FragmentCalendarBinding.inflate(inflater, container, false);
        final TextView textView = binding.textCalendar;
        calendarViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        // Return new inflated view
        return view;
    }

    // Restart view after creating new event
    @Override
    public void onResume() {
        super.onResume();
        setEventAdapter();
        setMonthView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /**
     * Set up monthly calendar
     */
    private void initWidgets() {
        calendarRecyclerView = view.findViewById(R.id.calendarRecyclerView);
        monthYearText = view.findViewById(R.id.monthYearTV);
    }

    /**
     * Set GridLayoutManager and call adapter
     */
    private void setMonthView() {
        monthYearText.setText(monthYearFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> daysInMonth = daysInMonthArray(CalendarUtils.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 7);

        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);

        setEventAdapter();
    }

    /**
     * Go to previous month and reload view
     *
     * @param view current view
     */
    public void previousMonthAction(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusMonths(1);
        setMonthView();
    }

    /**
     * Go to next month and reload view
     *
     * @param view current view
     */
    public void nextMonthAction(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusMonths(1);
        setMonthView();
    }

    /**
     * Get events for current date and set adapter
     */
    private void setEventAdapter() {

        DataBaseHelper db = new DataBaseHelper(getContext());
        Integer user_ID = Integer.valueOf((SharedPref.readSharedSetting(getContext(), "UserID", "-1")));
        ArrayList<Event> dailyEvents = Event.eventsForDate(CalendarUtils.selectedDate, db, user_ID);
        EventAdapter eventAdapter = new EventAdapter(getActivity().getApplicationContext(), dailyEvents);
        eventListView.setAdapter(eventAdapter);
    }

    // Select date
    @Override
    public void onItemClick(int position, LocalDate date) {
        if (date != null) {
            CalendarUtils.selectedDate = date;
            setMonthView();
        }
    }
}


