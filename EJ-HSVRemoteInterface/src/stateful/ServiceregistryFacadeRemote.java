/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stateful;

import entity.ServiceRegistryDTO;
import entity.Serviceregistry;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Vish
 */
@Remote
public interface ServiceregistryFacadeRemote {

    void create(Serviceregistry serviceregistry);

    void edit(Serviceregistry serviceregistry);

    void remove(Serviceregistry serviceregistry);

    Serviceregistry find(Object id);

    List<Serviceregistry> findAll();

    List<Serviceregistry> findRange(int[] range);

    int count();
    boolean addServiceTOList(ServiceRegistryDTO service);
    boolean updateBookedService(ServiceRegistryDTO service);
    boolean cancelBooking(ServiceRegistryDTO service);
    boolean setCompletionStatus(ServiceRegistryDTO service);
    List<ServiceRegistryDTO> getServiceByUser(String userId);
    List<ServiceRegistryDTO> getListbyServiceId(String serviceId);
    boolean updateServiceFromList(ServiceRegistryDTO service);
    boolean removeServiceFromList(ServiceRegistryDTO service);
    ArrayList<ServiceRegistryDTO> getAllServicesFromList();
    ServiceRegistryDTO getSeriveFromList(String ServiceId);
    void completeServiceList();
}
