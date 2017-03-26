/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stateful;

import entity.Feedback;
import entity.FeedbackDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Vish
 */
@Stateful
public class FeedbackFacade extends AbstractFacade<Feedback> implements FeedbackFacadeLocal, stateful.FeedbackFacadeRemote {
    
    private final ArrayList<FeedbackDTO> userFeedback;
    @PersistenceContext(unitName = "EJ-HSVEventsServiceManager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FeedbackFacade() {
        super(Feedback.class);
        userFeedback = null;
    }

    @Override
    public boolean addFeedback(FeedbackDTO feedback) {
        boolean result = false;
        if(feedback != null){
            Random rand = new Random();
            Feedback feed = new Feedback(rand.nextInt(100000) + feedback.getUserId());
            feed.setEventid(feedback.getEventId());
            feed.setFeedback(feedback.getFeedback());
            feed.setServiceid(feedback.getServiceId());
            this.create(feed);
            result = true;
        }
        return result;
    }

    @Override
    public boolean addFeedbackToList(FeedbackDTO feedback) {
        boolean result = false;
        if(feedback != null && this.userFeedback.contains(feedback)){
           this.userFeedback.add(feedback);
           result = true;
        }
        return result;
    }

    @Override
    public ArrayList<FeedbackDTO> getAllFeedback() {
        return this.userFeedback;
    }

    @Override
    public List<FeedbackDTO> getFeedbackByEvent(String eventId) {
        List<FeedbackDTO> feedbackList = null;
        List<Feedback> feedback = null;
        if(eventId != null){
            Query query = this.em.createNativeQuery("SELECT * FROM FEEDBACK WHERE EVENTID=?",Feedback.class);
            query.setParameter(1, eventId);
            feedback = query.getResultList();
            if(feedback != null){
                for(Feedback feed : feedback){
                    FeedbackDTO f = new FeedbackDTO();
                    f.setFeedbackId(feed.getFeedbackid());
                    f.setServiceId(feed.getServiceid());
                    f.setEventId(feed.getEventid());
                    f.setUserId(f.getUserId());
                    feedbackList.add(f);
                }
            }
        }
        return feedbackList;
    }

    @Override
    public List<FeedbackDTO> getFeedbackByService(String serviceId) {
        List<FeedbackDTO> feedbackList = null;
        List<Feedback> feedback = null;
        if(serviceId != null){
            Query query = this.em.createNativeQuery("SELECT * FROM FEEDBACK WHERE SERVICEID=?",Feedback.class);
            query.setParameter(1, serviceId);
            feedback = query.getResultList();
            if(feedback != null){
                for(Feedback feed : feedback){
                    FeedbackDTO f = new FeedbackDTO();
                    f.setFeedbackId(feed.getFeedbackid());
                    f.setServiceId(feed.getServiceid());
                    f.setEventId(feed.getEventid());
                    f.setUserId(f.getUserId());
                    feedbackList.add(f);
                }
            }
        }
        return feedbackList;
    }

    @Override
    public boolean submitFeedback() {
        boolean result = false;
        if(!this.userFeedback.isEmpty()){
            for(FeedbackDTO feed : this.userFeedback){
                this.addFeedback(feed);
            }
            result = true;
            this.userFeedback.clear();
        }
        return result;
    }     
    @Remove
    public void remove(){
        this.userFeedback.clear();
    }

}