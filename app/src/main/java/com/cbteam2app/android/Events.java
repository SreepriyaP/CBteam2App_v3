package com.cbteam2app.android;

/**
 * Created by Sreepriya on 3/25/2017.
 */
public class Events {
    int eventId;
    String eventName;
    String organizer;
    String time;
    public Events(int id, String name, String organizer, String time){
        this.eventId = id;
        this.eventName = name;
        this.organizer = organizer;
        this.time = time;
    }
    public Events(){

    }

    //public void getEvent(){}
    // public void createEvent(){}
    public String getEventName(){
        return this.eventName;
    }
    public String getOrganizer(){
        return this.organizer;
    }
    public String getTime(){
        return this.time;
    }
    public void setId(int id){
        this.eventId = id;
    }
    public void setEventName(String name){
        this.eventName = name;
    }
    public void setOrganizer(String organizer){
        this.organizer = organizer;
    }
    public void setTime(String time){
        this.time = time;
    }


}
