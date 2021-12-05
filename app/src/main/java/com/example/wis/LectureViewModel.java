package com.example.wis;

public class LectureViewModel {
    private String lecture_day;
    private String lecture_start;
    private String lecture_end;
    private String subject_shortcut;
    private String lecture_type; // == "p" =>prendnaska, =="c" =>cvicenie

    public LectureViewModel(String lecture_day, String lecture_start, String lecture_end, String subject_shortcut, String lecture_type) {
        this.lecture_day = lecture_day;
        this.lecture_start = lecture_start;
        this.lecture_end = lecture_end;
        this.subject_shortcut = subject_shortcut;
        this.lecture_type = lecture_type;
    }

    public LectureViewModel() {
    }

    public String getLecture_day() {
        return lecture_day;
    }

    public void setLecture_day(String lecture_day) {
        this.lecture_day = lecture_day;
    }

    public String getLecture_start() {
        return lecture_start;
    }

    public void setLecture_start(String lecture_start) {
        this.lecture_start = lecture_start;
    }

    public String getLecture_end() {
        return lecture_end;
    }

    public void setLecture_end(String lecture_end) {
        this.lecture_end = lecture_end;
    }

    public String getSubject_shortcut() {
        return subject_shortcut;
    }

    public void setSubject_shortcut(String subject_shortcut) {
        this.subject_shortcut = subject_shortcut;
    }

    public String getLecture_type() {
        return lecture_type;
    }

    public void setLecture_type(String lecture_type) {
        this.lecture_type = lecture_type;
    }
}
