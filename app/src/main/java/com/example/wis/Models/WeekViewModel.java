package com.example.wis.Models;

public class WeekViewModel {

    /**
     * Returns day in work week from the given string
     * If the string does not match day in the work week returns 7
     * @param lecture_day Mon, Tue, Wed, Thu, Fri
     * @return integer representing day in week
     */
    public static int dayToInt(String lecture_day) {
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
