/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stateless;

import entity.Administrator;
import entity.AdministratorDTO;
import entity.UserAccountDTO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Queue;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *
 * @author Vish
 */
@Stateless
public class AdministratorFacade extends AbstractFacade<Administrator> implements AdministratorFacadeRemote {

    @Resource(mappedName = "jms/queue9")
    private Queue queue9;

    @Inject
    @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
    private JMSContext context;

    
    @PersistenceContext(unitName = "EJ-HSVEventsServiceManager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdministratorFacade() {
        super(Administrator.class);
    }

    @Override
    public boolean verifyAdmin(AdministratorDTO admin) {
        boolean result = false;
        if(admin.getAdminId() != null){
            Administrator adminObj = this.find(admin.getAdminId());
            if( adminObj != null && adminObj.getPassword().equals(admin.getPassword())){
                result = true;
            }
            else{
                result = false;
            }            
        }
        return result;
    }

    @Override
    public boolean verifySecretKey(AdministratorDTO admin) {
        boolean result = false;
        if(admin.getAdminId() != null){
            Administrator adminObj = this.find(admin.getAdminId());
            if(adminObj != null && adminObj.getSecretkey().equals(admin.getSecretKey())){
                result = true;                
                MessageData mData = new MessageData();
                mData.setAdminId(admin.getAdminId());
                mData.setPassword(admin.getPassword());
                try {
                    this.sendJMSMessageToQueue9(mData);
                } catch (JMSException ex) {
                    Logger.getLogger(AdministratorFacade.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                result = false;
            }
        }
        return result;
    }
    @Override
    public boolean editPassword(AdministratorDTO admin) {
        boolean result = false;
        if(admin.getAdminId() != null){
            Administrator adminObj = this.find(admin.getAdminId());
            if(adminObj != null){
                if(adminObj.getPassword().equals(admin.getPassword())){
                    result = false;
                }
                else{
                    adminObj.setPassword(admin.getPassword());
                    result = true;
                }
            }
        }
        return result;
    }

    @Override
    public boolean freezeUserAccount(UserAccountDTO user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AdministratorDTO finAdmin(String adminId) {
        AdministratorDTO admin =null;
        if(adminId != null){
            Administrator ad = this.find(adminId);
            if(ad != null){
                admin = new AdministratorDTO();
                admin.setAdminId(ad.getAdminid());
                admin.setAdminName(ad.getAdminname());
                admin.setPassword(ad.getPassword());
                admin.setSecretKey(ad.getSecretkey());
            }            
        }
        return admin;
    }

    private void sendJMSMessageToQueue9(MessageData messageData) throws JMSException {
        MapMessage userMessage = context.createMapMessage();
        userMessage.setString("adminId", messageData.getAdminId());
        userMessage.setString("password",messageData.getPassword());        
        context.createProducer().send(queue9, userMessage);
    }    
}
