/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stateless;

import entity.EventDTO;
import entity.EventRegistryDTO;
import entity.Eventregistry;
import entity.Useraccount;
import java.util.List;
import java.util.Random;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import stateful.EventsFacadeRemote;
import utils.SendEmail;

/**
 *
 * @author Vish
 */
@Stateless
public class EventregistryFacade extends AbstractFacade<Eventregistry> implements stateless.EventregistryFacadeRemote {

    @PersistenceContext(unitName = "EJ-HSVEventsServiceManager-ejbPU")
    private EntityManager em;

    @EJB
    private EventsFacadeRemote eve;
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EventregistryFacade() {
        super(Eventregistry.class);
    }

    @Override
    public boolean addEventSubscriber(EventRegistryDTO eventReg) {
        boolean result = false;
        if(eventReg != null){
            Random rand = new Random();
            Eventregistry event = new Eventregistry();
            event.setId(rand.nextInt(100000));
            event.setEventid(eventReg.getEventId());
            event.setUserid(eventReg.getUserId());
            event.setCompletionstatus(1);
            this.create(event);
            EventDTO ev = new EventDTO();
            ev.setEventid(event.getEventid());
            this.eve.reduceCount(ev);
            Query query = this.em.createNativeQuery("SELECT EMAILID FROM USERACCOUNT WHERE USERID=?");
            query.setParameter(1, eventReg.getUserId());
            String emailId = (String)query.getSingleResult();
            SendEmail email = new SendEmail();
            email.sendEmail(emailId, "Event Booking Successful! "+eventReg.toString(),"HSV Event Subsription");
            result = true;
        }
        return result;
    }

    @Override
    public boolean setEventCompletionStatus(String eventId) {
        boolean result = false;
        if(eventId != null){            
            int n = this.em.createNativeQuery("UPDATE EVENTREGISTRY SET COMPLETIONSTATUS = 0 WHERE EVENTID = '"+eventId+"'").executeUpdate();
            if(n>0){
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean cancelSubscription(EventRegistryDTO eventReg) {
        boolean result = false;
        if(eventReg != null){            
            int n = this.em.createNativeQuery("UPDATE EVENTREGISTRY SET COMPLETIONSTATUS = 0 WHERE EVENTID = '"+eventReg.getEventId()+"' AND USERID='"+eventReg.getUserId()+"'").executeUpdate();
            if(n>0){
                result = true;                
                EventDTO ev = new EventDTO();
            ev.setEventid(eventReg.getEventId());
            this.eve.increaseCount(ev);
                Query query = this.em.createNativeQuery("SELECT EMAILID FROM USERACCOUNT WHERE USERID=?");
                query.setParameter(1, eventReg.getUserId());
                String emailId = (String)query.getSingleResult();
                SendEmail email = new SendEmail();
                email.sendEmail(emailId,"Event details Updated! "+eventReg.toString(),"HSV Change in Event Details ");
            }
        }
        return result;
    }

    @Override
    public List<String> getUserBookedEvent(String userId) {
        List<String> events = null;
        if(userId != null){
            Query query = this.em.createNativeQuery("SELECT EVENTID FROM EVENTREGISTRY WHERE USERID = ?");
            query.setParameter(1, userId);
            events = query.getResultList();
        }
        return events;
    }

    @Override
    public List<EventRegistryDTO> getRegistryByEvent(String eventId) {
        List<Eventregistry> events = null;
        List<EventRegistryDTO> eventList = null;
        if(eventId != null){
            Query query = this.em.createNativeQuery("SELECT * FROM EVENTREGISTRY WHERE EVENTID = ?",Eventregistry.class);
            query.setParameter(1, eventId);
            events = query.getResultList();
        }
        if(events != null){
            for(Eventregistry ev : events){
                EventRegistryDTO tempRegister = new EventRegistryDTO();
                tempRegister.setEventId(ev.getEventid());
                tempRegister.setUserId(ev.getEventid());
                tempRegister.setCompletionStatus(ev.getCompletionstatus());
                eventList.add(tempRegister);
            }
        }                    
        return eventList;
    }    
}
