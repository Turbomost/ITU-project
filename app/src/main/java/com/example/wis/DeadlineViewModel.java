package com.example.wis;

public class DeadlineViewModel {
    private String deadline_name;
    private String deadline_time;
    private String subject_name;

    public DeadlineViewModel(String deadline_name, String deadline_time, String subject_name) {
        this.deadline_name = deadline_name;
        this.deadline_time = deadline_time;
        this.subject_name = subject_name;
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
}
