/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package message;

/**
 *
 * @author Vish
 */
public class MessageData {
    
    private String adminId;
    private String password;
    
    public MessageData(){       
    }
    
    public String getAdminId(){
        return this.adminId;
    }
    public String getPassword(){
        return this.password;
    }
    
    public void setAdminId(String adminId){
        this.adminId = adminId;       
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String toString(){
        return "MessageData { userId = " + adminId + " ; password = "+ password+" }";
    }
}
