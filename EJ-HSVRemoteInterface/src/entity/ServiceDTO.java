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
public class ServiceDTO implements Serializable{
    
    private String serviceId;
    private String serviceName;
    private String description;
    private double cost;
    private String duration;
    private String availableSlots;

    public ServiceDTO() {
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(String availableSlots) {
        this.availableSlots = availableSlots;
    }

    @Override
    public String toString() {
        return "ServiceDTO{" + "serviceId=" + serviceId + ", serviceName=" + serviceName + ", description=" + description + ", cost=" + cost + ", duration=" + duration + ", availableSlots=" + availableSlots + '}';
    }    
    
}
