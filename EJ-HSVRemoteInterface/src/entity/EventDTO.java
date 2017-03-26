/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author Vish
 */
public class EventDTO implements Serializable {
    private String  eventid;
    private String  eventname;
    private String  date;
    private String  description;
    private int  availableslots;
    private String  duration;
    private int cost;
    public EventDTO(){        
        //Default Constructor
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getEventid() {
        return eventid;
    }

    public void setEventid(String eventid) {
        this.eventid = eventid;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAvailableslots() {
        return availableslots;
    }

    public void setAvailableslots(int availableslots) {
        this.availableslots = availableslots;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "EventDTO{" + "eventid=" + eventid + ", eventname=" + eventname + ", date=" + date + ", description=" + description + ", availableslots=" + availableslots + ", duration=" + duration + '}';
    }
    
    
}
