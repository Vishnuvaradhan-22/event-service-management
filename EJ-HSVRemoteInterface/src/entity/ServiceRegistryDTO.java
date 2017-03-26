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
public class ServiceRegistryDTO implements Serializable{
        private Integer id;
    
    private String serviceId;
    private String userId;
    private String dateBooked;
    private String slotBooked;
    private int completionStatus;

    public ServiceRegistryDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDateBooked() {
        return dateBooked;
    }

    public void setDateBooked(String dateBooked) {
        this.dateBooked = dateBooked;
    }

    public String getSlotBooked() {
        return slotBooked;
    }

    public void setSlotBooked(String slotBooked) {
        this.slotBooked = slotBooked;
    }

    public int getCompletionStatus() {
        return completionStatus;
    }

    public void setCompletionStatus(int completionStatus) {
        this.completionStatus = completionStatus;
    }

    @Override
    public String toString() {
        return "ServiceRegistryDTO{" + "id=" + id + ", serviceId=" + serviceId + ", userId=" + userId + ", dateBooked=" + dateBooked + ", slotBooked=" + slotBooked + ", completionStatus=" + completionStatus + '}';
    }
    
}
