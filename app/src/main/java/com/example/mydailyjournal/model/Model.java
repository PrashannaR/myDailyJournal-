package com.example.mydailyjournal.model;

public class Model {
    long ID;
    String title;
    String body;
    String date;
    String time;

    //constructor
    public Model(String title, String body, String date, String time) {
        this.title = title;
        this.body = body;
        this.date = date;
        this.time = time;
    }

    public Model(long id, String title, String body, String date, String time){
        this.ID = id;
        this.title = title;
        this.body = body;
        this.date = date;
        this.time = time;
    }


    //empty constructor
    public Model(){
    }

    //getters and setters
    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
