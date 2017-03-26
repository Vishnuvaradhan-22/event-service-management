/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Vish
 */
@Entity
@Table(name = "EVENTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Events.findAll", query = "SELECT e FROM Events e"),
    @NamedQuery(name = "Events.findByEventid", query = "SELECT e FROM Events e WHERE e.eventid = :eventid"),
    @NamedQuery(name = "Events.findByEventname", query = "SELECT e FROM Events e WHERE e.eventname = :eventname"),
    @NamedQuery(name = "Events.findByDate", query = "SELECT e FROM Events e WHERE e.date = :date"),
    @NamedQuery(name = "Events.findByDescription", query = "SELECT e FROM Events e WHERE e.description = :description"),
    @NamedQuery(name = "Events.findByAvailableslots", query = "SELECT e FROM Events e WHERE e.availableslots = :availableslots"),
    @NamedQuery(name = "Events.findByCost", query = "SELECT e FROM Events e WHERE e.cost = :cost"),
    @NamedQuery(name = "Events.findByDuration", query = "SELECT e FROM Events e WHERE e.duration = :duration")})
public class Events implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "EVENTID")
    private String eventid;
    @Basic(optional = false)
    @Column(name = "EVENTNAME")
    private String eventname;
    @Basic(optional = false)
    @Column(name = "DATE")
    private String date;
    @Basic(optional = false)
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @Column(name = "AVAILABLESLOTS")
    private int availableslots;
    @Column(name = "COST")
    private Integer cost;
    @Basic(optional = false)
    @Column(name = "DURATION")
    private String duration;

    public Events() {
    }

    public Events(String eventid) {
        this.eventid = eventid;
    }

    public Events(String eventid, String eventname, String date, String description, int availableslots, String duration) {
        this.eventid = eventid;
        this.eventname = eventname;
        this.date = date;
        this.description = description;
        this.availableslots = availableslots;
        this.duration = duration;
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

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (eventid != null ? eventid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Events)) {
            return false;
        }
        Events other = (Events) object;
        if ((this.eventid == null && other.eventid != null) || (this.eventid != null && !this.eventid.equals(other.eventid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Events[ eventid=" + eventid + " ]";
    }
    
}
