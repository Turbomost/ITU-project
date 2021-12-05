/* DeadlineViewModel.java
 * Author     : xbella01
 * Model for handling data for deadlines displayed in list
 */


package com.example.wis.Models;

import android.content.Context;
import android.database.Cursor;

import com.example.wis.Data.DataBaseHelper;
import com.example.wis.Data.SharedPref;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class DeadlineViewModel {
    private int deadline_id;
    private String deadline_name;
    private String deadline_time;
    private String subject_name;
    private int deadline_status;


    public DeadlineViewModel(int deadline_id, String deadline_name, String deadline_time, String subject_name, int deadline_status) {
        this.deadline_id = deadline_id;
        this.deadline_name = deadline_name;
        this.deadline_time = deadline_time;
        this.subject_name = subject_name;
        this.deadline_status = deadline_status;
    }

    public DeadlineViewModel() {
    }

    /**
     * Time Comparator of deadlines
     */
    public static Comparator<DeadlineViewModel> DeadlineDateComparator = new Comparator<DeadlineViewModel>() {
        @Override
        public int compare(DeadlineViewModel o1, DeadlineViewModel o2) {
            String date1 = o1.deadline_time;
            String date2 = o2.deadline_time;
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            try {
                Date final_date1 = format.parse(date1);
                Date final_date2 = format.parse(date2);
                return final_date1.compareTo(final_date2);

            } catch (ParseException e) {
                e.printStackTrace();
            }
            return 1;
        }
    };

    /**
     * Filling list for display
     * @param context
     * @return list of DeadlineViewModel
     */
    public List<DeadlineViewModel> displayData(Context context){
        Integer user_ID= Integer.valueOf((SharedPref.readSharedSetting(context, "UserID", "-1")));
        DataBaseHelper databaseHelper = new DataBaseHelper(context);
        List<DeadlineViewModel> deadlinelist = new ArrayList<DeadlineViewModel>();
        Cursor cursor = databaseHelper.getUserDeadlines(user_ID);

        while (cursor.moveToNext()){
            DeadlineViewModel newdeadline = new DeadlineViewModel(cursor.getInt(0), cursor.getString(1),cursor.getString(2), databaseHelper.getSubjectName(cursor.getInt(3)),databaseHelper.getUserDeadlineStatus(user_ID,cursor.getInt(0)));
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

            try {
                Date date = format.parse(newdeadline.getDeadline_time());
                String str_current_date = format.format(new Date());
                Date current_date = format.parse(str_current_date);
                if (!(current_date.after(date))){
                    deadlinelist.add(newdeadline);
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return deadlinelist;

    }

    public String getDeadline_name() {
        return deadline_name;
    }

    public void setDeadline_name(String deadline_name) {
        this.deadline_name = deadline_name;
    }

    public String getDeadline_time() {
        return deadline_time;
    }

    public void setDeadline_time(String deadline_time) {
        this.deadline_time = deadline_time;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public int getDeadline_status() {
        return deadline_status;
    }

    public void setDeadline_status(int deadline_status) {
        this.deadline_status = deadline_status;
    }

    public int getDeadline_id() {
        return deadline_id;
    }

    public void setDeadline_id(int deadline_id) {
        this.deadline_id = deadline_id;
    }
}
