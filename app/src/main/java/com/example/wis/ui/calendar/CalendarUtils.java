/*
 * CalendarAdapter.java
 * Author     : xvalen29
 * Fragment showing monthly calendar
 * Showing unfinished events for selected date and highlight dates with events
 */

package com.example.wis.ui.calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Locale;

// Calendar functions
public class CalendarUtils {
    public static LocalDate selectedDate;

    /**
     * Format given date to 'dd MMMM yyyy'
     * @param date input date in LocalDate
     * @return formatted date in String
     */
    public static String formattedDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return date.format(formatter);
    }

    /**
     * Format given time to 'HH:mm'
     * @param time input time in LocalTime
     * @return formatted time in String
     */
    public static String formattedTime(LocalTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return time.format(formatter);
    }

    /**
     * Format given date to 'MMMM yyyy'
     * @param date input date in LocalTime
     * @return
     */
    public static String monthYearFromDate(LocalDate date) {
        DateTimeFormatterBuilder formatter = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("MMMM yyyy");
        DateTimeFormatter f = formatter.toFormatter(Locale.ENGLISH);
        return date.format(f);
    }

    /**
     * Returns list of dates in month
     * Return 42 days to fill in the grid
     * @param date actual date
     * @return ArrayList of LocalDates
     */
    public static ArrayList<LocalDate> daysInMonthArray(LocalDate date) {
        ArrayList<LocalDate> daysInMonthArray = new ArrayList<>();

        YearMonth yearMonth = YearMonth.from(selectedDate);
        int daysInMonth = yearMonth.lengthOfMonth();

        LocalDate prevMonth = selectedDate.minusMonths(1);
        LocalDate nextMonth = selectedDate.plusMonths(1);

        YearMonth prevYearMonth = YearMonth.from(prevMonth);
        int prevDaysInMonth = prevYearMonth.lengthOfMonth();

        LocalDate firstOfMonth = CalendarUtils.selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for (int i = 1; i <= 42; i++) {
            if (i <= dayOfWeek)
                daysInMonthArray.add(LocalDate.of(prevMonth.getYear(), prevMonth.getMonth(), prevDaysInMonth + i - dayOfWeek));
            else if (i > daysInMonth + dayOfWeek)
                daysInMonthArray.add(LocalDate.of(nextMonth.getYear(), nextMonth.getMonth(), i - dayOfWeek - daysInMonth));
            else
                daysInMonthArray.add(LocalDate.of(selectedDate.getYear(), selectedDate.getMonth(), i - dayOfWeek));
        }
        return daysInMonthArray;
    }

}
