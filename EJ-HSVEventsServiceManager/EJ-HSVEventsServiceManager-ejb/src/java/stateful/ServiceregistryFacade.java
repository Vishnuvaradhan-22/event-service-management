/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stateful;

import entity.ServiceRegistryDTO;
import entity.Serviceregistry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import utils.SendEmail;

/**
 *
 * @author Vish
 */
@Stateful
public class ServiceregistryFacade extends AbstractFacade<Serviceregistry> implements ServiceregistryFacadeLocal, stateful.ServiceregistryFacadeRemote {
    private final ArrayList<ServiceRegistryDTO> servicesBooked;
    @PersistenceContext(unitName = "EJ-HSVEventsServiceManager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServiceregistryFacade() {
        super(Serviceregistry.class);
        servicesBooked = new ArrayList<ServiceRegistryDTO>();
    }

    @Override
    public boolean addServiceTORegister(ServiceRegistryDTO service) {
        boolean result = false;
        if(service != null){
            Random rand = new Random();
            Serviceregistry serReg = new Serviceregistry(rand.nextInt(100000));
            serReg.setUserid(service.getUserId());
            serReg.setServiceid(service.getServiceId());
            serReg.setSlotbooked(service.getSlotBooked());
            serReg.setDatebooked(service.getDateBooked());
            serReg.setCompletionstatus(service.getCompletionStatus());
            this.create(serReg);
            Query query = this.em.createNativeQuery("SELECT EMAILID FROM USERACCOUNT WHERE USERID=?");
            query.setParameter(1, serReg.getUserid());
            String emailId = (String)query.getSingleResult();
            SendEmail email = new SendEmail();
            email.sendEmail(emailId, "Service Booking Successful! "+serReg.toString(),"HSV Service Subsription");
            
            result = true;
        }
        return result;
    }

    @Override
    public boolean updateBookedService(ServiceRegistryDTO service) {
        boolean result = false;
        if(service != null){
            int update = this.em.createNativeQuery("UPDATE SERVICEREGISTRY SET DATEBOOKED = '"+service.getDateBooked()+"', SLOTBOOKED = '"+service.getSlotBooked()+"' WHERE USERID='"+service.getUserId()+"' AND SERVICEID = '"+service.getServiceId()+"' AND COMPLETIONSTATUS="+service.getCompletionStatus()).executeUpdate();
            if(update > 0){
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean cancelBooking(ServiceRegistryDTO service) {
        boolean result = false;
        if(service != null){
            int update = this.em.createNativeQuery("DELETE FROM SERVICEREGISTRY  WHERE USERID='"+service.getUserId()+"' AND SERVICEID = '"+service.getServiceId()+"' AND DATEBOOKED='"+service.getDateBooked()+"' AND SLOTBOOKED='"+service.getSlotBooked()+"'").executeUpdate();
            if(update > 0){
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean setCompletionStatus(ServiceRegistryDTO service) {
        boolean result = false;
        if(service != null){
            int update = this.em.createNativeQuery("UPDATE SERVICEREGISTRY SET COMPLETIONSTATUS ="+service.getCompletionStatus()+" WHERE USERID='"+service.getUserId()+"' AND SERVICEID = '"+service.getServiceId()+"' AND DATEBOOKED='"+service.getDateBooked()+"' AND SLOTBOOKED='"+service.getSlotBooked()+"'").executeUpdate();
            if(update > 0){
                result = true;
            }
        }
        return result;        
    }

    @Override
    public List<ServiceRegistryDTO> getServiceByUser(String userId) {
        List<ServiceRegistryDTO> services = new ArrayList<ServiceRegistryDTO>();
        List<Serviceregistry> service;
        if(userId != null){
            Query query = this.em.createNativeQuery("SELECT ID,SERVICEID,USERID,DATEBOOKED,SLOTBOOKED,COMPLETIONSTATUS FROM SERVICEREGISTRY WHERE USERID =?",Serviceregistry.class);
            query.setParameter(1, userId);
            service = query.getResultList();
            if(!service.isEmpty()){
                for(Serviceregistry serv : service){
                    ServiceRegistryDTO tempService = new ServiceRegistryDTO();
                    tempService.setServiceId(serv.getServiceid());
                    tempService.setUserId(serv.getUserid());
                    tempService.setSlotBooked(serv.getSlotbooked());
                    tempService.setDateBooked(serv.getDatebooked());
                    tempService.setCompletionStatus(serv.getCompletionstatus());
                    services.add(tempService);
                }
            }
        }
        return services;
    }

    @Override
    public List<ServiceRegistryDTO> getListbyServiceId(String serviceId) {
        List<ServiceRegistryDTO> services = null;
        List<Serviceregistry> service;
        if(serviceId != null){
            Query query = this.em.createNativeQuery("SELECT ID,SERVICEID,USERID,DATEBOOKED,SLOTBOOKED,COMPLETIONSTATUS FROM SERVICEREGISTRY WHERE SERVICEID =?",Serviceregistry.class);
            query.setParameter(1, serviceId);
            service = query.getResultList();
            if(!service.isEmpty()){
                for(Serviceregistry serv : service){
                    ServiceRegistryDTO tempService = new ServiceRegistryDTO();
                    tempService.setServiceId(serv.getServiceid());
                    tempService.setUserId(serv.getUserid());
                    tempService.setSlotBooked(serv.getSlotbooked());
                    tempService.setDateBooked(serv.getDatebooked());
                    tempService.setCompletionStatus(serv.getCompletionstatus());
                    services.add(tempService);
                }
            }
        }
        return services;
    }

    @Override
    public boolean addServiceTOList(ServiceRegistryDTO service) {
        boolean result = false;
        if(service != null){
            if(!servicesBooked.contains(service)){
                this.servicesBooked.add(service);
                result = true;
            }            
        }
        return result;
    }

    @Override
    public boolean updateServiceFromList(ServiceRegistryDTO service) {
        boolean result = false;
        if(service != null){
            if(servicesBooked.contains(service)){
              for(ServiceRegistryDTO serv : servicesBooked){
                  if(serv.getServiceId().equals(service.getServiceId())){
                      serv.setCompletionStatus(service.getCompletionStatus());
                      serv.setDateBooked(service.getDateBooked());
                      serv.setSlotBooked(service.getSlotBooked());                      
                      result = true;
                      break;
                  }
              }  
            }            
        }
        return result;
    }

    @Override
    public boolean removeServiceFromList(ServiceRegistryDTO service) {
        boolean result = false;
        if(service != null){
            Iterator<ServiceRegistryDTO> iter = servicesBooked.iterator();
            while (iter.hasNext()) {
                ServiceRegistryDTO serv = iter.next();
                if (serv.getServiceId().equals(service.getServiceId()) && serv.getDateBooked().equals(service.getDateBooked())){
                    iter.remove();
                    result = true;
                }
            }
        }
        return result;
    }

    @Override
    public ArrayList<ServiceRegistryDTO> getAllServicesFromList() {
        if(!servicesBooked.isEmpty())
            return this.servicesBooked;
        else
            return null;
    }

    @Override
    public ServiceRegistryDTO getSeriveFromList(String serviceId) {
        ServiceRegistryDTO result = null;
        if(!servicesBooked.isEmpty()){
            for(ServiceRegistryDTO serv : servicesBooked){
                if(serv.getServiceId().equals(serviceId))
                    result = serv;
            }
        }
        return result;
    }

    @Override
    public void completeServiceList() {
        if(!servicesBooked.isEmpty()){
            for(ServiceRegistryDTO serv : servicesBooked){
                this.addServiceTORegister(serv);
            }
            servicesBooked.clear();
        }
    }
        @Remove
    public void remove(){
        servicesBooked.clear();
    }    
}
