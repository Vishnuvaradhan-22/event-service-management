/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stateful;

import entity.EventDTO;
import entity.Events;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
public class EventsFacade extends AbstractFacade<Events> implements EventsFacadeLocal, EventsFacadeRemote {

    private ArrayList<EventDTO> newEvents;
    @PersistenceContext(unitName = "EJ-HSVEventsServiceManager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EventsFacade() {        
        super(Events.class);
        newEvents = new ArrayList<EventDTO>();
    }

    @Override
    public boolean addEvent(EventDTO event) {
        boolean result = false;
        if(event != null){
            Events ev = this.find(event.getEventid());
            if(ev == null){
                ev = new Events(event.getEventid());
                ev.setEventname(event.getEventname());
                ev.setDescription(event.getDescription());
                ev.setDate(event.getDate());
                ev.setCost(event.getCost());
                ev.setDuration(event.getDuration());
                ev.setAvailableslots(event.getAvailableslots());
                this.create(ev);
                result = true;
            }                
        }
        return result;
    }

    @Override
    public EventDTO findEventbyId(String eventId) {
        EventDTO event = null;
        if(eventId != null){
            Events ev = this.find(eventId);
            if(ev != null){
                event = new EventDTO();
                event.setEventid(ev.getEventid());
                event.setEventname(ev.getEventname());
                event.setDuration(ev.getDuration());
                event.setDescription(ev.getDescription());
                event.setDate(ev.getDate());
                event.setCost(ev.getCost());
                event.setAvailableslots(ev.getAvailableslots());
            }
        }
        return event;
    }

    @Override
    public List<EventDTO> getAllEvents() {
        Query query = em.createNativeQuery("SELECT * FROM EVENTS", Events.class);        
        List<Events> availableEvents = query.getResultList();
        List<EventDTO> events = new ArrayList<EventDTO>();
        if(!availableEvents.isEmpty()){
            for(Events ev : availableEvents){
                EventDTO event = new EventDTO();
                event.setEventid(ev.getEventid());
                event.setEventname(ev.getEventname());
                event.setDuration(ev.getDuration());
                event.setDescription(ev.getDescription());
                event.setDate(ev.getDate());
                event.setCost(ev.getCost());
                event.setAvailableslots(ev.getAvailableslots());
                events.add(event);
            }
        }
        return events;
    }

    @Override
    public boolean editEvent(EventDTO event) {
        boolean result = false;
        if(event != null){
            Events ev = this.find(event.getEventid());
            if(ev != null){
                ev.setEventname(event.getEventname());
                ev.setDescription(event.getDescription());
                ev.setDate(event.getDate());
                ev.setCost(event.getCost());
                ev.setDuration(event.getDuration());
                ev.setAvailableslots(event.getAvailableslots());
                this.edit(ev);
                result = true;
            }                
        }
        return result;
    }

    @Override
    public boolean deleteEvent(String eventId) {
        boolean result = false;
        if(eventId != null){
            Events ev = this.find(eventId);
            if(ev != null){
                this.remove(ev);
                result = true;
            }                
        }
        return result;
    }    

    @Override
    public boolean addEventToList(EventDTO event) {
        boolean result = false;
        if(event != null){
            if(!newEvents.contains(event)){
                this.newEvents.add(event);
                result = true;
            }            
        }
        return result;
    }

    @Override
    public boolean editEventInList(EventDTO event) {
        boolean result = false;
        if(event != null){
            if(newEvents.contains(event)){
              for(EventDTO ev : newEvents){
                  if(ev.getEventid().equals(event.getEventid())){
                      ev.setEventname(event.getEventname());
                      ev.setDuration(event.getDuration());
                      ev.setDate(event.getDate());
                      ev.setDescription(event.getDescription());
                      ev.setCost(event.getCost());
                      ev.setAvailableslots(event.getAvailableslots());
                      result = true;
                      break;
                  }
              }  
            }            
        }
        return result;
    }

    @Override
    public boolean removeEventFromList(String eventId) {
        boolean result = false;
        if(eventId != null){
            Iterator<EventDTO> iter = newEvents.iterator();
            while (iter.hasNext()) {
                EventDTO ev = iter.next();
                if (ev.getEventid().equals(eventId)){
                iter.remove();
                result = true;
                }
            }
        }
        return result;
    }   

    @Override
    public EventDTO getEventFromList(String eventId) {
        EventDTO event = null;
        for(EventDTO ev : newEvents){
            if(ev.getEventid().equals(eventId)){
                event = ev;
                break;
            }
        }
        return event;
    }

    @Override
    public ArrayList<EventDTO> getAllEventFromList() {
        if(!newEvents.isEmpty())
            return newEvents;
        else
            return null;
    }
    @Override
    public void completeAddEvents(){
        if(!newEvents.isEmpty()){
            for(EventDTO ev : newEvents){
                this.addEvent(ev);
            }
            newEvents.clear();
        }        
    }
    @Remove
    public void remove(){
        newEvents.clear();
    }

    @Override
    public void reduceCount(EventDTO event) {
        Events ev = this.find(event.getEventid());
        int slot = ev.getAvailableslots();
        slot = slot - 1;
        ev.setAvailableslots(slot);
    }
    @Override
    public void increaseCount(EventDTO event) {
        Events ev = this.find(event.getEventid());
        int slot = ev.getAvailableslots();
        slot = slot + 1;
        ev.setAvailableslots(slot);
    }
}