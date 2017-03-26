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
@Table(name = "EVENTREGISTRY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Eventregistry.findAll", query = "SELECT e FROM Eventregistry e"),
    @NamedQuery(name = "Eventregistry.findById", query = "SELECT e FROM Eventregistry e WHERE e.id = :id"),
    @NamedQuery(name = "Eventregistry.findByEventid", query = "SELECT e FROM Eventregistry e WHERE e.eventid = :eventid"),
    @NamedQuery(name = "Eventregistry.findByUserid", query = "SELECT e FROM Eventregistry e WHERE e.userid = :userid"),
    @NamedQuery(name = "Eventregistry.findByCompletionstatus", query = "SELECT e FROM Eventregistry e WHERE e.completionstatus = :completionstatus")})
public class Eventregistry implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "EVENTID")
    private String eventid;
    @Basic(optional = false)
    @Column(name = "USERID")
    private String userid;
    @Basic(optional = false)
    @Column(name = "COMPLETIONSTATUS")
    private int completionstatus;

    public Eventregistry() {
    }

    public Eventregistry(Integer id) {
        this.id = id;
    }

    public Eventregistry(Integer id, String eventid, String userid, int completionstatus) {
        this.id = id;
        this.eventid = eventid;
        this.userid = userid;
        this.completionstatus = completionstatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEventid() {
        return eventid;
    }

    public void setEventid(String eventid) {
        this.eventid = eventid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getCompletionstatus() {
        return completionstatus;
    }

    public void setCompletionstatus(int completionstatus) {
        this.completionstatus = completionstatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Eventregistry)) {
            return false;
        }
        Eventregistry other = (Eventregistry) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Eventregistry[ id=" + id + " ]";
    }
    
}
