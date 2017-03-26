/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stateless;

import entity.UserAccountDTO;
import entity.Useraccount;
import java.sql.SQLException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import utils.SendEmail;

/**
 *
 * @author Vish
 */
@Stateless
public class UseraccountFacade extends AbstractFacade<Useraccount> implements stateless.UseraccountFacadeRemote {

    @PersistenceContext(unitName = "EJ-HSVEventsServiceManager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UseraccountFacade() {
        super(Useraccount.class);
    }

    @Override
    public boolean addUser(UserAccountDTO user){
        boolean result = false;
        if(user != null){
            Useraccount newUser = this.find(user.getUserid());
            if(newUser == null){
                newUser = new Useraccount(user.getUserid());
                newUser.setFirstname(user.getFirstname());
                newUser.setLastname(user.getLastname());
                newUser.setEmailid(user.getEmailid());
                newUser.setPassword(user.getPassword());
                newUser.setAddress(user.getAddress());
                newUser.setContactnumber(user.getContactnumber());
                newUser.setAccounttype(user.getAccounttype());
                this.create(newUser);
                SendEmail email = new SendEmail();
                email.sendEmail(newUser.getEmailid(),"User account created! "+user.toString(),"Welcome to HSV");
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean updateUser(UserAccountDTO user) {
        boolean result = false;
        if(user != null){
            Useraccount newUser = this.find(user.getUserid());
            if(newUser != null){
                newUser.setFirstname(user.getFirstname());
                newUser.setLastname(user.getLastname());
                newUser.setEmailid(user.getEmailid());
                newUser.setPassword(user.getPassword());
                newUser.setAddress(user.getAddress());
                newUser.setContactnumber(user.getContactnumber());
                newUser.setAccounttype(user.getAccounttype());
                this.edit(newUser);
                SendEmail email = new SendEmail();
                email.sendEmail(newUser.getEmailid(),"User details updated! "+user.toString(),"RE: HSV User Account update");
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean verifyUser(UserAccountDTO user) {
        boolean result = false;
        if(user != null){
            Useraccount verifyUser = this.find(user.getUserid());
            if(verifyUser != null){
                if(verifyUser.getPassword().equals(user.getPassword())){
                    result = true;
                }
            }
        }
        return result;
    }

    @Override
    public UserAccountDTO findUserById(String userId) {
        UserAccountDTO user = null;
        Useraccount findUser = this.find(userId);
        if(userId != null){
            if(findUser != null){
                user = new UserAccountDTO();
                user.setUserid(findUser.getUserid());
                user.setFirstname(findUser.getFirstname());
                user.setLastname(user.getLastname());
                user.setEmailid(findUser.getEmailid());
                user.setContactnumber(findUser.getContactnumber());
                user.setPassword(findUser.getPassword());
                user.setAddress(findUser.getAddress());
                user.setAccounttype(findUser.getAccounttype());            
            }
        }
        return user;
    }
    
}