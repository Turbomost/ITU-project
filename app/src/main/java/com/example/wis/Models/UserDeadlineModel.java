/* UserDeadlineModel.java
 * Author     : xbella01
 * Model for handling data for user deadline connections
 */

package com.example.wis.Models;

public class UserDeadlineModel {
    private int user_deadline_id;
    private int user_id;
    private int deadline_id;
    private int user_deadline_status;

    public UserDeadlineModel(int user_deadline_id, int user_id, int deadline_id, int user_deadline_status) {
        this.user_deadline_id = user_deadline_id;
        this.user_id = user_id;
        this.deadline_id = deadline_id;
        this.user_deadline_status = user_deadline_status;
    }

    public UserDeadlineModel() {
    }

    public int getUser_deadline_id() {
        return user_deadline_id;
    }

    public void setUser_deadline_id(int user_deadline_id) {
        this.user_deadline_id = user_deadline_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getDeadline_id() {
        return deadline_id;
    }

    public void setDeadline_id(int deadline_id) {
        this.deadline_id = deadline_id;
    }

    public int getUser_deadline_status() {
        return user_deadline_status;
    }

    public void setUser_deadline_status(int user_deadline_status) {
        this.user_deadline_status = user_deadline_status;
    }
}
