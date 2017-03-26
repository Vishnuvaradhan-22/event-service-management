/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package message;

import entity.AdministratorDTO;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import stateless.AdministratorFacadeRemote;

/**
 *
 * @author Vish
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/queue9"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class AdminMessages implements MessageListener {
    
    public AdminMessages() {
    }
    @EJB
    private AdministratorFacadeRemote adminFacade;
    @Resource
    private MessageDrivenContext msgDrvnCtx;

    @Override
    public void onMessage(Message message) {
                if (message instanceof MapMessage) {
            try {
                MapMessage mm = (MapMessage) message;
                String userId = mm.getString("adminId");
                String password = mm.getString("password");
                if (userId == null) {
                    System.err.println("Error: Invalid information - MapMessage needs a \"adminname\" entry");
                    msgDrvnCtx.setRollbackOnly();
                } else {                    
                    AdministratorDTO user = new AdministratorDTO(userId);
                    user.setPassword(password);
                    adminFacade.editPassword(user);
                }
            } catch (JMSException e) {
                System.err.println("Error : Invalid message format - need a MapMessage");
                msgDrvnCtx.setRollbackOnly();
            }
                }

    }
    
}
