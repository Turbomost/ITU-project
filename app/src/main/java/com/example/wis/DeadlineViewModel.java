package com.example.wis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class DeadlineViewModel {
    private String deadline_name;
    private String deadline_time;
    private String subject_name;

    public DeadlineViewModel(String deadline_name, String deadline_time, String subject_name) {
        this.deadline_name = deadline_name;
        this.deadline_time = deadline_time;
        this.subject_name = subject_name;
    }

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
}
