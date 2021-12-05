/*
 * Author     : xbella01
 */
package com.example.wis.Models;

public class UserSubjectModel {

    private int user_subject_id;
    private int user_id;
    private int subject_id;

    public UserSubjectModel(int user_subject_id, int user_id, int subject_id) {
        this.user_subject_id = user_subject_id;
        this.user_id = user_id;
        this.subject_id = subject_id;
    }

    public UserSubjectModel() {
    }

    @Override
    public String toString() {
        return "UserSubjectModel{" +
                "user_subject_id=" + user_subject_id +
                ", user_id=" + user_id +
                ", lecture_id=" + subject_id +
                '}';
    }

    public int getUser_subject_id() {
        return user_subject_id;
    }

    public void setUser_subject_id(int user_subject_id) {
        this.user_subject_id = user_subject_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }
}
