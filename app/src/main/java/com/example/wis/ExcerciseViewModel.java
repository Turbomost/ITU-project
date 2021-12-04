package com.example.wis;

import android.content.Context;
import android.database.Cursor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class ExcerciseViewModel {
    private int excercise_id;
    private String excercise_start;
    private String excercise_end;
    private String excercise_name;
    private int excercise_status;

    public int getExcercise_id() {
        return excercise_id;
    }

    public void setExcercise_id(int excercise_id) {
        this.excercise_id = excercise_id;
    }

    public String getExcercise_start() {
        return excercise_start;
    }

    public void setExcercise_start(String excercise_start) {
        this.excercise_start = excercise_start;
    }

    public String getExcercise_end() {
        return excercise_end;
    }

    public void setExcercise_end(String excercise_end) {
        this.excercise_end = excercise_end;
    }

    public String getExcercise_name() {
        return excercise_name;
    }

    public void setExcercise_name(String excercise_name) {
        this.excercise_name = excercise_name;
    }

    public int getExcercise_status() {
        return excercise_status;
    }

    public void setExcercise_status(int excercise_status) {
        this.excercise_status = excercise_status;
    }

    public ExcerciseViewModel() {
    }

    public ExcerciseViewModel(int excercise_id, String excercise_start, String excercise_end, String excercise_name, int excercise_status) {
        this.excercise_id = excercise_id;
        this.excercise_start = excercise_start;
        this.excercise_end = excercise_end;
        this.excercise_name = excercise_name;
        this.excercise_status = excercise_status;
    }

    public List<ExcerciseViewModel> displayData(Context context, int subject_id) {
        Integer user_ID = Integer.valueOf((SharedPref.readSharedSetting(context, "UserID", "-1")));
        DataBaseHelper databaseHelper = new DataBaseHelper(context);
        List<ExcerciseViewModel> excerciseList = new ArrayList<ExcerciseViewModel>();
        String exercise_meno = "cvičení";
        Cursor cursor = databaseHelper.getAllUserLectures(user_ID, subject_id);

        while (cursor.moveToNext()) {
            if(cursor.getString(3).equals("c")){
                ExcerciseViewModel newExcercise = new ExcerciseViewModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2),exercise_meno, databaseHelper.getUserExcerciseStatus(user_ID, cursor.getInt(0)));
                excerciseList.add(newExcercise);
            }
        }
        return excerciseList;
    }

    public static Comparator<ExcerciseViewModel> ExcerciseTimeComparator = new Comparator<ExcerciseViewModel>() {
        @Override
        public int compare(ExcerciseViewModel o1, ExcerciseViewModel o2) {
            String time1 = o1.getExcercise_start();
            String time2 = o2.getExcercise_start();
            SimpleDateFormat format = new SimpleDateFormat("EEE HH:mm ");
            try {
                Date final_time1 = format.parse(time1);
                Date final_time2 = format.parse(time2);
                if(final_time1.after(final_time2)){
                    return 1;
                }
                else {
                    return -1;
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
            return 1;
        }
    };
}
