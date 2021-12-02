package com.example.wis;


public class DeadlineModel {
    private int deadline_id;
    private String deadline_name;
    private String deadline_time;
    private int subject_id;

    public DeadlineModel(int deadline_id, String deadline_name, String deadline_time, int subject_id) {
        this.deadline_id = deadline_id;
        this.deadline_name = deadline_name;
        this.deadline_time = deadline_time;
        this.subject_id = subject_id;
    }

    public DeadlineModel() {
    }

    @Override
    public String toString() {
        return "DeadlineModel{" +
                "deadline_id=" + deadline_id +
                ", deadline_name='" + deadline_name + '\'' +
                ", deadline_time=" + deadline_time +
                ", subject_id=" + subject_id +
                '}';
    }

    public int getDeadline_id() {
        return deadline_id;
    }

    public void setDeadline_id(int deadline_id) {
        this.deadline_id = deadline_id;
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

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }
}
