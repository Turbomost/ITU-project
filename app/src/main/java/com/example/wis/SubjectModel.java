package com.example.wis;

import java.text.SimpleDateFormat;

public class SubjectModel {

    private int subject_id;
    private String subject_name;
    private String subject_shortcut;

    public SubjectModel(int subject_id, String subject_name, String subject_shortcut) {
        this.subject_id = subject_id;
        this.subject_name = subject_name;
        this.subject_shortcut = subject_shortcut;
    }

    public SubjectModel() {
    }

    @Override
    public String toString() {
        return "SubjectModel{" +
                "id=" + subject_id +
                ", subject_name='" + subject_name + '\'' +
                ", subject_shortcut='" + subject_shortcut + '\'' +
                '}';
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getSubject_shortcut() {
        return subject_shortcut;
    }

    public void setSubject_shortcut(String subject_shortcut) {
        this.subject_shortcut = subject_shortcut;
    }
}
