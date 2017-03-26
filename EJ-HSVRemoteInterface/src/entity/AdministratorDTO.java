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
public class AdministratorDTO implements Serializable{
    
    private String adminId;
    private String adminName;
    private String password;
    private String secretKey;
    
    public AdministratorDTO() {
    }

    public AdministratorDTO(String adminid) {
        this.adminId = adminid;
    }

    public String getAdminId() {
        return adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public String getPassword() {
        return password;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public String toString() {
        return "AdministratorDTO{" + "adminId=" + adminId + ", adminName=" + adminName + ", password=" + password + ", secretKey=" + secretKey + '}';
    }
    
}
