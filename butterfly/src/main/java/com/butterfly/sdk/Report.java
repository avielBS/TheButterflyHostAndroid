package com.butterfly.sdk;

public class Report {

    private String comments;
    private String wayContact;
    private String fakePlace;
    private String message;
        private String country;

    public Report(String comments, String way , String date , String msg) {
        this.comments  = comments;
        this.wayContact = way;
        this.fakePlace = date;
        this.message = msg;
    }

    public String getName() {
        return comments;
    }

    public void setName(String comments) {
        this.comments = comments;
    }

    public String getWayContact() {
        return wayContact;
    }

    public void setWayContact(String wayContact) {
        this.wayContact = wayContact;
    }

    public String getFakePlace() {
        return fakePlace;
    }

    public void setFakePlace(String fakePlace) {
        this.fakePlace = fakePlace;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
