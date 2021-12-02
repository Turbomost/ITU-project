package com.example.wis;

public class LectureModel {
    private int lecture_id;
    private String lecture_start;
    private String lecture_end;
    private String lecture_type; //Cviko/Prednaska
    private int subject_id;

    public LectureModel(int lecture_id, String lecture_start, String lecture_end, String lecture_type, int subject_id) {
        this.lecture_id = lecture_id;
        this.lecture_start = lecture_start;
        this.lecture_end = lecture_end;
        this.lecture_type = lecture_type;
        this.subject_id = subject_id;
    }

    public LectureModel() {
    }

    @Override
    public String toString() {
        return "LectureModel{" +
                "lecture_id=" + lecture_id +
                ", lecture_start='" + lecture_start + '\'' +
                ", lecture_end='" + lecture_end + '\'' +
                ", lecture_type='" + lecture_type + '\'' +
                ", subject_id=" + subject_id +
                '}';
    }

    public int getLecture_id() {
        return lecture_id;
    }

    public void setLecture_id(int lecture_id) {
        this.lecture_id = lecture_id;
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

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public String getLecture_type() {
        return lecture_type;
    }

    public void setLecture_type(String lecture_type) {
        this.lecture_type = lecture_type;
    }
}
