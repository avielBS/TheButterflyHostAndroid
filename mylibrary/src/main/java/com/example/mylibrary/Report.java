package com.example.mylibrary;

public class Report {

    private String name;
    private String wayContact;
    private String day;
    private String message;

    public Report(String name, String way , String date , String msg) {
        this.name  = name;
        this.wayContact = way;
        this.day = date;
        this.message = msg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWayContact() {
        return wayContact;
    }

    public void setWayContact(String wayContact) {
        this.wayContact = wayContact;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
