/* LectureModel.java
 * Author     : xbella01
 * Model for handling data for lectures/excercises
 */

package com.example.wis.Models;

public class LectureModel {
    private int lecture_id;
    private String lecture_day;
    private String lecture_start;
    private String lecture_end;
    private String lecture_name;
    private String lecture_type; //Cviko/Prednaska
    private int subject_id;

    public LectureModel(int lecture_id, String lecture_day, String lecture_start, String lecture_end, String lecture_name, String lecture_type, int subject_id) {
        this.lecture_id = lecture_id;
        this.lecture_day = lecture_day;
        this.lecture_start = lecture_start;
        this.lecture_end = lecture_end;
        this.lecture_name = lecture_name;
        this.lecture_type = lecture_type;
        this.subject_id = subject_id;
    }

    public LectureModel() {
    }

    public String getLecture_day() {
        return lecture_day;
    }

    public void setLecture_day(String lecture_day) {
        this.lecture_day = lecture_day;
    }

    public String getLecture_name() {
        return lecture_name;
    }

    public void setLecture_name(String lecture_name) {
        this.lecture_name = lecture_name;
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
