/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stateful;

import entity.EventDTO;
import entity.Events;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Vish
 */
@Remote
public interface EventsFacadeRemote {

    void create(Events events);

    void edit(Events events);

    void remove(Events events);

    Events find(Object id);

    List<Events> findAll();

    List<Events> findRange(int[] range);

    int count();
    boolean addEventToList(EventDTO event);
    EventDTO findEventbyId(String eventId);
    List<EventDTO> getAllEvents();
    boolean editEvent(EventDTO event);
    boolean deleteEvent(String eventId);
    boolean editEventInList(EventDTO event);
    boolean removeEventFromList(String eventId);
    EventDTO getEventFromList(String eventId);
    ArrayList<EventDTO> getAllEventFromList();
    void completeAddEvents();
    void reduceCount(EventDTO event);
    void increaseCount(EventDTO event);
}
