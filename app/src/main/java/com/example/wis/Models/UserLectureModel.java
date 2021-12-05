/* UserLectureModel.java
 * Author     : xbella01
 * Model for handling data for user lecture connections
 */

package com.example.wis.Models;

public class UserLectureModel {
    private int user_lecture_id;
    private int user_id;
    private int lecture_id;
    private int user_lecture_status;

    public UserLectureModel(int user_lecture_id, int user_id, int lecture_id, int user_lecture_status) {
        this.user_lecture_id = user_lecture_id;
        this.user_id = user_id;
        this.lecture_id = lecture_id;
        this.user_lecture_status = user_lecture_status;
    }

    public UserLectureModel() {
    }

    public int getUser_lecture_id() {
        return user_lecture_id;
    }

    public void setUser_lecture_id(int user_lecture_id) {
        this.user_lecture_id = user_lecture_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getLecture_id() {
        return lecture_id;
    }

    public void setLecture_id(int lecture_id) {
        this.lecture_id = lecture_id;
    }

    public int getUser_lecture_status() {
        return user_lecture_status;
    }

    public void setUser_lecture_status(int user_lecture_status) {
        this.user_lecture_status = user_lecture_status;
    }
}
