/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stateless;

import entity.Service;
import entity.ServiceDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Vish
 */
@Stateless
public class ServiceFacade extends AbstractFacade<Service> implements stateless.ServiceFacadeRemote {

    @PersistenceContext(unitName = "EJ-HSVEventsServiceManager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServiceFacade() {
        super(Service.class);
    }

    @Override
    public boolean addService(ServiceDTO service) {
        boolean result = false;
        if(service != null){
            Service serv = this.find(service.getServiceId());
            if(serv == null){
                serv = new Service(service.getServiceId());
                serv.setServicename(service.getServiceName());
                serv.setDuration(service.getDuration());
                serv.setDescription(service.getDescription());
                serv.setCost(service.getCost());
                serv.setAvailableslots(service.getAvailableSlots());
                this.create(serv);
                result = true;
            }
        }
        return result;
    }

    @Override
    public ServiceDTO findServicebyId(String serviceId) {
        ServiceDTO service = null;
        if(serviceId != null){
            Service serv = this.find(serviceId);
            if(serv != null){
                service = new ServiceDTO();
                service.setServiceId(serv.getServiceid());
                service.setServiceName(serv.getServicename());
                service.setDuration(serv.getDuration());
                service.setDescription(serv.getDescription());
                service.setCost(serv.getCost());
                service.setAvailableSlots(serv.getAvailableslots());
            }
        }
        return service;
    }

    @Override
    public List<ServiceDTO> getAllServices() {
        List<Service> availableServices = this.findAll();
        List<ServiceDTO> services = new ArrayList<ServiceDTO>();
        if(!availableServices.isEmpty()){
            for(Service serv : availableServices){
                ServiceDTO service = new ServiceDTO();
                service.setServiceId(serv.getServiceid());
                service.setServiceName(serv.getServicename());
                service.setDuration(serv.getDuration());
                service.setDescription(serv.getDescription());
                service.setCost(serv.getCost());
                service.setAvailableSlots(serv.getAvailableslots());
                services.add(service);
            }
        }
        return services;
    }

    @Override
    public boolean editService(ServiceDTO service) {
        boolean result = false;
        if(service != null){
            Service serv = this.find(service.getServiceId());
            if(serv != null){
                serv.setServicename(service.getServiceName());
                serv.setDuration(service.getDuration());
                serv.setDescription(service.getDescription());
                serv.setCost(service.getCost());
                serv.setAvailableslots(service.getAvailableSlots());
                this.edit(serv);
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean deleteService(String serviceId) {
        boolean result = false;
        if(serviceId != null){
            Service serv = this.find(serviceId);
            if(serv != null){
                this.remove(serv);
                result = true;
            }
        }
        return result;
    }
}
