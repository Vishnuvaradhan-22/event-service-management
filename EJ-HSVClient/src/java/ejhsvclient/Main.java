/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejhsvclient;

import entity.EventRegistryDTO;
import entity.ServiceRegistryDTO;
import entity.UserAccountDTO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;

import stateless.AdministratorFacadeRemote;
import stateless.EventregistryFacadeRemote;
import stateless.ServiceFacadeRemote;
import stateful.ServiceregistryFacadeRemote;
import stateless.UseraccountFacadeRemote;

/**
 *
 * @author Vish
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    @EJB
    private static UseraccountFacadeRemote serviceRegistry;
    @EJB
    private static EventregistryFacadeRemote facade2;
    public static void main(String[] args) {
        // TODO code application logic here
        UserAccountDTO user = new UserAccountDTO();
        user.setUserid("U0002");
        user.setFirstname("Raj");
        user.setLastname("Ram");
        user.setEmailid("svaradhan92@gmail.com");
        user.setContactnumber("9823244");
        user.setAddress("10 Random Address");
        user.setAccounttype("Premium");
        user.setPassword("qwerty");
 
            System.out.println(serviceRegistry.findUserById("U0001"));
  
    }
    
}
