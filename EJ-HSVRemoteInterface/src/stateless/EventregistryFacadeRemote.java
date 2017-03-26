/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stateless;

import entity.EventRegistryDTO;
import entity.Eventregistry;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Vish
 */
@Remote
public interface EventregistryFacadeRemote {

    void create(Eventregistry eventregistry);

    void edit(Eventregistry eventregistry);

    void remove(Eventregistry eventregistry);

    Eventregistry find(Object id);

    List<Eventregistry> findAll();

    List<Eventregistry> findRange(int[] range);

    int count();
    
    boolean addEventSubscriber(EventRegistryDTO eventReg);
    boolean setEventCompletionStatus(String eventId);
    boolean cancelSubscription(EventRegistryDTO eventReg);
    List<String> getUserBookedEvent(String userId);
    List<EventRegistryDTO> getRegistryByEvent(String eventId);    
}
