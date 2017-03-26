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
@Table(name = "SERVICEREGISTRY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Serviceregistry.findAll", query = "SELECT s FROM Serviceregistry s"),
    @NamedQuery(name = "Serviceregistry.findById", query = "SELECT s FROM Serviceregistry s WHERE s.id = :id"),
    @NamedQuery(name = "Serviceregistry.findByServiceid", query = "SELECT s FROM Serviceregistry s WHERE s.serviceid = :serviceid"),
    @NamedQuery(name = "Serviceregistry.findByUserid", query = "SELECT s FROM Serviceregistry s WHERE s.userid = :userid"),
    @NamedQuery(name = "Serviceregistry.findByDatebooked", query = "SELECT s FROM Serviceregistry s WHERE s.datebooked = :datebooked"),
    @NamedQuery(name = "Serviceregistry.findBySlotbooked", query = "SELECT s FROM Serviceregistry s WHERE s.slotbooked = :slotbooked"),
    @NamedQuery(name = "Serviceregistry.findByCompletionstatus", query = "SELECT s FROM Serviceregistry s WHERE s.completionstatus = :completionstatus")})
public class Serviceregistry implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "SERVICEID")
    private String serviceid;
    @Basic(optional = false)
    @Column(name = "USERID")
    private String userid;
    @Basic(optional = false)
    @Column(name = "DATEBOOKED")
    private String datebooked;
    @Basic(optional = false)
    @Column(name = "SLOTBOOKED")
    private String slotbooked;
    @Basic(optional = false)
    @Column(name = "COMPLETIONSTATUS")
    private int completionstatus;

    public Serviceregistry() {
    }

    public Serviceregistry(Integer id) {
        this.id = id;
    }

    public Serviceregistry(Integer id, String serviceid, String userid, String datebooked, String slotbooked, int completionstatus) {
        this.id = id;
        this.serviceid = serviceid;
        this.userid = userid;
        this.datebooked = datebooked;
        this.slotbooked = slotbooked;
        this.completionstatus = completionstatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServiceid() {
        return serviceid;
    }

    public void setServiceid(String serviceid) {
        this.serviceid = serviceid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getDatebooked() {
        return datebooked;
    }

    public void setDatebooked(String datebooked) {
        this.datebooked = datebooked;
    }

    public String getSlotbooked() {
        return slotbooked;
    }

    public void setSlotbooked(String slotbooked) {
        this.slotbooked = slotbooked;
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
        if (!(object instanceof Serviceregistry)) {
            return false;
        }
        Serviceregistry other = (Serviceregistry) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Serviceregistry[ id=" + id + " ]";
    }
    
}
