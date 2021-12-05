package com.example.wis.Models;

public class UserModel {
    private int user_id;
    private String user_login;
    private String user_password;
    private String user_name;
    private String user_class;

    //constructors

    public UserModel(int user_id, String user_login, String user_password, String user_name, String user_class) {
        this.user_id = user_id;
        this.user_login = user_login;
        this.user_password = user_password;
        this.user_name = user_name;
        this.user_class = user_class;
    }

    public UserModel() {
    }
//toString

    @Override
    public String toString() {
        return "UserModel{" +
                "user_id=" + user_id +
                ", user_login='" + user_login + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_class='" + user_class + '\'' +
                '}';
    }

    //getters and setters

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_login() {
        return user_login;
    }

    public void setUser_login(String user_login) {
        this.user_login = user_login;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_class() {
        return user_class;
    }

    public void setUser_class(String user_class) {
        this.user_class = user_class;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }
}
