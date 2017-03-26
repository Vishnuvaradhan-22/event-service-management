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
public class EventRegistryDTO implements Serializable{
    private int id;
    private String eventId;
    private String userId;
    private int completionStatus;
    
    public EventRegistryDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getCompletionStatus() {
        return completionStatus;
    }

    public void setCompletionStatus(int completionStatus) {
        this.completionStatus = completionStatus;
    }

    @Override
    public String toString() {
        return "EventRegistryDTO{" + "id=" + id + ", eventId=" + eventId + ", userId=" + userId + ", completionStatus=" + completionStatus + '}';
    }
        
}
