/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stateless;

import entity.Service;
import entity.ServiceDTO;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Vish
 */
@Remote
public interface ServiceFacadeRemote {

    void create(Service service);

    void edit(Service service);

    void remove(Service service);

    Service find(Object id);

    List<Service> findAll();

    List<Service> findRange(int[] range);

    int count();
    boolean addService(ServiceDTO service);
    ServiceDTO findServicebyId(String serviceId);
    List<ServiceDTO> getAllServices();
    boolean editService(ServiceDTO service);
    boolean deleteService(String serviceId);
}
