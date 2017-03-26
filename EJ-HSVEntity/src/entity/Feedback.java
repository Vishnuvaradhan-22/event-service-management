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
@Table(name = "FEEDBACK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Feedback.findAll", query = "SELECT f FROM Feedback f"),
    @NamedQuery(name = "Feedback.findByFeedbackid", query = "SELECT f FROM Feedback f WHERE f.feedbackid = :feedbackid"),
    @NamedQuery(name = "Feedback.findByUserid", query = "SELECT f FROM Feedback f WHERE f.userid = :userid"),
    @NamedQuery(name = "Feedback.findByServiceid", query = "SELECT f FROM Feedback f WHERE f.serviceid = :serviceid"),
    @NamedQuery(name = "Feedback.findByEventid", query = "SELECT f FROM Feedback f WHERE f.eventid = :eventid"),
    @NamedQuery(name = "Feedback.findByFeedback", query = "SELECT f FROM Feedback f WHERE f.feedback = :feedback")})
public class Feedback implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "FEEDBACKID")
    private String feedbackid;
    @Column(name = "USERID")
    private String userid;
    @Column(name = "SERVICEID")
    private String serviceid;
    @Column(name = "EVENTID")
    private String eventid;
    @Basic(optional = false)
    @Column(name = "FEEDBACK")
    private String feedback;

    public Feedback() {
    }

    public Feedback(String feedbackid) {
        this.feedbackid = feedbackid;
    }

    public Feedback(String feedbackid, String feedback) {
        this.feedbackid = feedbackid;
        this.feedback = feedback;
    }

    public String getFeedbackid() {
        return feedbackid;
    }

    public void setFeedbackid(String feedbackid) {
        this.feedbackid = feedbackid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getServiceid() {
        return serviceid;
    }

    public void setServiceid(String serviceid) {
        this.serviceid = serviceid;
    }

    public String getEventid() {
        return eventid;
    }

    public void setEventid(String eventid) {
        this.eventid = eventid;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (feedbackid != null ? feedbackid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Feedback)) {
            return false;
        }
        Feedback other = (Feedback) object;
        if ((this.feedbackid == null && other.feedbackid != null) || (this.feedbackid != null && !this.feedbackid.equals(other.feedbackid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Feedback[ feedbackid=" + feedbackid + " ]";
    }
    
}
