/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed;

import entity.AdministratorDTO;
import entity.EventDTO;
import entity.EventRegistryDTO;
import entity.ServiceDTO;
import entity.ServiceRegistryDTO;
import entity.UserAccountDTO;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import stateful.EventsFacadeRemote;
import stateful.ServiceregistryFacadeRemote;
import stateless.AdministratorFacadeRemote;
import stateless.EventregistryFacadeRemote;
import stateless.ServiceFacadeRemote;
import stateless.UseraccountFacadeRemote;

/**
 *
 * @author Vish
 */
@Named(value = "hsvBean")
@SessionScoped
public class HsvManagedBean implements Serializable {

    @EJB
    private UseraccountFacadeRemote useraccountFacade;
    @EJB
    private AdministratorFacadeRemote adminFacade;
    @EJB
    private EventsFacadeRemote eventFacade;
    @EJB
    private ServiceFacadeRemote serviceFacade;
    @EJB
    private EventregistryFacadeRemote eventregistryFacade;
    @EJB
    private ServiceregistryFacadeRemote serviceregistryFacade;
    
    private String userId;
    private String adminId;
    private String password;
    private String userName;
    private String secretKey;
    private String eventId;
    private String updateId;
    private String eventName;
    private String serviceId;
    private String serviceName;
    private String date;
    private String cost;
    private String description;
    private String availableSlots;
    private String duration;
    private String selectedService;
    private String selectedSlot;
    
    private String firstName;
    private String lastName;
    private String address;
    private String contact;
    private String account;
    private String email;
    
    private AdministratorDTO loggedinAdmin;
    private UserAccountDTO loggedinUser;
    
    private EventDTO[] eventList;
    private ServiceDTO[] servicesList;
    private ServiceRegistryDTO[] servicesCart;
    
    private String selectedValue;
    private String newData;
    private String displayMessage;
    private String displayMessageSuccess;
    private String pageLink;
    
    private final String SUCCESS = "success";
    private final String FAILURE = "failure";   
    private final String LOGOUT = "logout";
    /**
     * Creates a new instance of HsvManagedBean
     */
    public HsvManagedBean() {
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAdminId() {
        return adminId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getSelectedSlot() {
        return selectedSlot;
    }

    public void setSelectedSlot(String selectedSlot) {
        this.selectedSlot = selectedSlot;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public ServiceRegistryDTO[] getServicesCart() {
        return servicesCart;
    }

    public void setServicesCart(ServiceRegistryDTO[] servicesCart) {
        this.servicesCart = servicesCart;
    }

    public String getEventName() {
        return eventName;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getSelectedService() {
        return selectedService;
    }

    public void setSelectedService(String selectedService) {
        this.selectedService = selectedService;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(String availableSlots) {
        this.availableSlots = availableSlots;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getDisplayMessageSuccess() {
        return displayMessageSuccess;
    }

    public void setDisplayMessageSuccess(String displayMessageSuccess) {
        this.displayMessageSuccess = displayMessageSuccess;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

    public void setDisplayMessage(String adminMessage) {
        this.displayMessage = adminMessage;
    }

    public String getPageLink() {
        return pageLink;
    }

    public void setPageLink(String pageLink) {
        this.pageLink = pageLink;
    }

    public String getSelectedValue() {
        return selectedValue;
    }

    public void setSelectedValue(String selectedValue) {
        this.selectedValue = selectedValue;
    }

    public String getNewData() {
        return newData;
    }

    public void setNewData(String newData) {
        this.newData = newData;
    }

    
    public EventDTO[] getEventsInList() throws IOException {
        ArrayList<EventDTO> events = eventFacade.getAllEventFromList(); 
        if(events != null){
            eventList = new EventDTO[events.size()];
            for(int i=0;i<events.size();i++){
                eventList[i] = events.get(i);
            }
        }
        else{
            FacesContext.getCurrentInstance().getExternalContext().redirect("emptyList.xhtml");
            FacesContext.getCurrentInstance().responseComplete();
        }
        return eventList;

    }
    
    public EventDTO[] getAllEventsList() throws IOException {
        List<EventDTO> events = eventFacade.getAllEvents(); 
        if(events != null){
            eventList = new EventDTO[events.size()];
            for(int i=0;i<events.size();i++){                
                eventList[i] = events.get(i);
            }
        }
        else{
            FacesContext.getCurrentInstance().getExternalContext().redirect("emptyList.xhtml");
            FacesContext.getCurrentInstance().responseComplete();
        }
        return eventList;

    }
    
    public ServiceDTO[] getServicesList() throws IOException {
        List<ServiceDTO> services = serviceFacade.getAllServices();
        if(services != null){
            servicesList = new ServiceDTO[services.size()];
            for(int i=0;i<services.size();i++){                
                servicesList[i] = services.get(i);
            }
        }
        else{
            FacesContext.getCurrentInstance().getExternalContext().redirect("emptyList.xhtml");
            FacesContext.getCurrentInstance().responseComplete();
        }
        return servicesList;        
    }
    public ServiceRegistryDTO[] getServicesInCartList() throws IOException{
        ArrayList<ServiceRegistryDTO> services = serviceregistryFacade.getAllServicesFromList();
        if(services != null){
            servicesCart = new ServiceRegistryDTO[services.size()];
            for(int i=0;i<services.size();i++){                
                servicesCart[i] = services.get(i);
            }
        }
        else{
            FacesContext.getCurrentInstance().getExternalContext().redirect("emptyList.xhtml");
            FacesContext.getCurrentInstance().responseComplete();
        }
        return servicesCart;                
    }
    public String loginUser() throws IOException{
        String result = FAILURE;
        if(this.userId != null && this.password != null){
            UserAccountDTO user = new UserAccountDTO();
            user.setUserid(this.userId);
            user.setPassword(this.password);
            if(useraccountFacade.verifyUser(user)){
                result = SUCCESS;
                this.loggedinUser = useraccountFacade.findUserById(userId);
                this.userName = loggedinUser.getFirstname() + loggedinUser.getLastname();
                FacesContext.getCurrentInstance().getExternalContext().redirect("userHome.xhtml");
                FacesContext.getCurrentInstance().responseComplete();
            }
            else{
                this.displayMessage = "User Id or Password Wrong, Retry Login";
                this.pageLink = "faces/index.xhtml";
            }
        }
        else{
            this.displayMessage = "User Id or Password Cannot be Null, Retry Login";
            this.pageLink = "faces/index.xhtml";
        }
        return result;
    }
    public String loginAdmin() throws IOException{
        String result = FAILURE;
        if(this.adminId != null && this.password != null){
            AdministratorDTO admin = new AdministratorDTO();
            admin.setAdminId(this.adminId);
            admin.setPassword(this.password);
            if(this.adminFacade.verifyAdmin(admin)){
                result = SUCCESS;
                this.loggedinAdmin = adminFacade.finAdmin(adminId);
                this.userName = loggedinAdmin.getAdminName();
                FacesContext.getCurrentInstance().getExternalContext().redirect("adminHome.xhtml");
                FacesContext.getCurrentInstance().responseComplete();
            }
            else{
                this.displayMessage = "Admin Id or Password Wrong, Retry Login";
                this.pageLink = "faces/index.xhtml";
            }
        }
        else{
            this.displayMessage = "Admin Id or Password Cannot be Null, Retry Login";
            this.pageLink = "faces/index.xhtml";
        }
        return result;        
    }
    //Event List
    public String addEventList(){
        String result = FAILURE;
        EventDTO event = new EventDTO();
        event.setEventid(eventId);
        event.setEventname(this.eventName);
        event.setDate(date);
        event.setDescription(description);
        event.setCost(Integer.parseInt(this.cost));
        event.setAvailableslots(Integer.parseInt(this.availableSlots));
        event.setDuration(duration);
        if(eventFacade.addEventToList(event)){
            result = SUCCESS;
            this.pageLink = "faces/addEventsHome.xhtml";
            this.displayMessage = "Event added to List";
        }
        else{
            this.pageLink = "faces/addEventsHome.xhtml";
            this.displayMessage = "Unable to Add Event to List, Event ALready Exists or Error in adding";
        }
        return result;
    }
    public String updateListEvent(){
        String result = FAILURE;
        EventDTO event = eventFacade.getEventFromList(eventId);
        if(event != null){
            switch(this.selectedValue){
                case "name": event.setEventname(this.newData);
                             break;
                case "date": event.setDate(this.newData);
                             break;        
                case "duration": event.setDuration(this.newData);
                             break;               
                case "cost": event.setCost(Integer.parseInt(this.newData));
                             break;                             
                case "slot": event.setAvailableslots(Integer.parseInt(this.newData));
                             break;                 
                case "description": event.setDescription(this.newData);
                             break;                             
            }
            if(eventFacade.editEventInList(event)){
                result = SUCCESS;
                this.pageLink = "faces/addEventsHome.xhtml";
                this.displayMessage = "Event Successfully edited";
            }
            else{
                this.pageLink = "faces/addEventsHome.xhtml";
                this.displayMessage = "Unable to Edit event";                
            }
        }
        else{
            this.displayMessage = "Event Not in List";
            this.pageLink = "faces/addEventsHome.xhtml";
        }
        return result;
    }
    public String removeEvent(){
        String result = FAILURE;
        EventDTO event = eventFacade.getEventFromList(eventId);
        if(event != null){
            if(eventFacade.removeEventFromList(eventId)){
                result = SUCCESS;
                this.pageLink = "faces/addEventsHome.xhtml";
                this.displayMessage = "Event Successfully Removed";
            }
            else{
                this.pageLink = "faces/addEventsHome.xhtml";
                this.displayMessage = "Unable to Remove event";                
            }            
        }
        else{
            this.displayMessage = "Event Not in List";
            this.pageLink = "faces/addEventsHome.xhtml";
        }
        return result;
    }
    public String completeEventsList(){
        this.eventFacade.completeAddEvents();
        return SUCCESS;
    }
    
    //New Service
    public String addNewService(){
        String result = FAILURE;
        ServiceDTO service = new ServiceDTO();
        service.setServiceId(serviceId);
        service.setServiceName(serviceName);
        service.setDuration(duration);
        service.setDescription(description);
        service.setCost(Integer.parseInt(cost));
        service.setAvailableSlots(availableSlots);
        if(serviceFacade.addService(service)){
            result = SUCCESS;
            this.pageLink = "faces/adminHome.xhtml";
            this.displayMessage = "Service Successfully added";
        }
        else{
            this.pageLink = "faces/adminHome.xhtml";
            this.displayMessage = "Add Service failed";

        }
        return result;
    }
    
    //Update Admin
    public String updateAdmin(){
        String result = FAILURE;
        AdministratorDTO tempAdmin = new AdministratorDTO();
        tempAdmin.setAdminId(loggedinAdmin.getAdminId());
        tempAdmin.setSecretKey(secretKey);
        tempAdmin.setPassword(newData);
        if(adminFacade.verifySecretKey(tempAdmin)){     
            //loggedinAdmin.setPassword(newData);
            //if(adminFacade.editPassword(loggedinAdmin)){
                result = SUCCESS;
                this.pageLink = "faces/adminHome.xhtml";
                this.displayMessage = "Admin Successfully edited";
            //}
            //else{
              //  this.pageLink = "faces/adminHome.xhtml";
                //this.displayMessage = "Unable to update Password, try agian"; 
            //}
        }
        else{
            this.pageLink = "faces/adminHome.xhtml";
            this.displayMessage = "Secret Key Wrong, try agian"; 
        }
        return result;
    }
    //Update Event/Service
    public String adminEventServiceUpdate() throws IOException{
        String result = FAILURE;
        switch(selectedValue){
            case "event":
                EventDTO tempEvent = eventFacade.findEventbyId(updateId);
                if(tempEvent != null){
                    FacesContext.getCurrentInstance().getExternalContext().redirect("updateEventDetails.xhtml");
                    FacesContext.getCurrentInstance().responseComplete();
                }
                else{
                    this.pageLink = "faces/adminHome.xhtml";
                    this.displayMessage = "Event does not exists, try agian"; 
                }
                break;
            case "service":
                ServiceDTO tempService = serviceFacade.findServicebyId(updateId);
                if(tempService != null){
                    FacesContext.getCurrentInstance().getExternalContext().redirect("updateServiceDetails.xhtml");
                    FacesContext.getCurrentInstance().responseComplete();
                }
                else{
                    this.pageLink = "faces/adminHome.xhtml";
                    this.displayMessage = "Service does not exists, try agian"; 
                }
                break;
        }
        return result;
    }
    public String updateAdminEvent(){
        String result = FAILURE;
        EventDTO event = eventFacade.findEventbyId(updateId);
        if(event != null){
            switch(this.selectedValue){
                case "name": event.setEventname(this.newData);
                             break;
                case "date": event.setDate(this.newData);
                             break;        
                case "duration": event.setDuration(this.newData);
                             break;               
                case "cost": event.setCost(Integer.parseInt(this.newData));
                             break;                             
                case "slot": event.setAvailableslots(Integer.parseInt(this.newData));
                             break;                 
                case "description": event.setDescription(this.newData);
                             break;                             
            }
            if(eventFacade.editEvent(event)){
                result = SUCCESS;
                this.pageLink = "faces/adminHome.xhtml";
                this.displayMessage = "Event Successfully edited";
            }
            else{
                this.pageLink = "faces/adminHome.xhtml";
                this.displayMessage = "Unable to Edit event";                
            }
        }
        return result;        
    }
    public String updateAdminService(){
        String result = FAILURE;
        ServiceDTO service = serviceFacade.findServicebyId(updateId);
        if(service != null){
            switch(this.selectedValue){
                case "name": service.setServiceName(this.newData);
                             break;    
                case "duration": service.setDuration(this.newData);
                             break;               
                case "cost": service.setCost(Integer.parseInt(this.newData));
                             break;                             
                case "slot": service.setAvailableSlots(this.newData);
                             break;                 
                case "description": service.setDescription(this.newData);
                             break;                             
            }
            if(serviceFacade.editService(service)){
                result = SUCCESS;
                this.pageLink = "faces/adminHome.xhtml";
                this.displayMessage = "Service Successfully edited";
            }
            else{
                this.pageLink = "faces/adminHome.xhtml";
                this.displayMessage = "Unable to Edit service";                
            }
        }
        return result;        
    }    
    
    //User Controls
    public String updateUserAccount(){
        String result = FAILURE;
        switch(this.selectedValue){
            case "firstname": loggedinUser.setFirstname(this.newData);
                         break;
            case "lastname": loggedinUser.setLastname(this.newData);
                         break;
            case "password": loggedinUser.setPassword(this.newData);
                         break;        
            case "email": loggedinUser.setEmailid(this.newData);
                         break;               
            case "address": loggedinUser.setAddress(newData);
                         break;                             
            case "contactnumber": loggedinUser.setContactnumber(newData);
                         break;                 
            case "account": loggedinUser.setAccounttype(this.newData);
                         break;                             
        }
        if(useraccountFacade.updateUser(loggedinUser)){
            result = SUCCESS;
            this.pageLink = "faces/userHome.xhtml";
            this.displayMessage = "User Successfully Updated";
        }
        else{
            this.pageLink = "faces/userHome.xhtml";
            this.displayMessage = "User updation failed";
        }
        return result;
    }
    
    public String userEventServiceUpdate() throws IOException{
        String result = FAILURE;
        switch(selectedValue){
            case "event":
                List<String> events = eventregistryFacade.getUserBookedEvent(loggedinUser.getUserid());
                if(events != null && events.contains(updateId)){
                    EventRegistryDTO eventReg = new EventRegistryDTO();
                    eventReg.setEventId(updateId);
                    eventReg.setUserId(loggedinUser.getUserid());
                    if(eventregistryFacade.cancelSubscription(eventReg)){
                        result = SUCCESS;
                        this.pageLink = "faces/userHome.xhtml";
                        this.displayMessage = "Event Successfully Cancelled";
                    }
                    else{
                        this.pageLink = "faces/userHome.xhtml";
                        this.displayMessage = "Unable to cancel Booking, try again / check Event details!";
                    }
                }
                else{
                    this.pageLink = "faces/adminHome.xhtml";
                    this.displayMessage = "Event does not exists, try agian"; 
                }
                break;
            case "service":
                int flag = 0;
                List<ServiceRegistryDTO> tempService = serviceregistryFacade.getServiceByUser(loggedinUser.getUserid());
                if(tempService != null){
                    for(ServiceRegistryDTO sr : tempService){
                        if(sr.getServiceId().equals(updateId) && sr.getCompletionStatus()!= 0){
                            result = SUCCESS;
                            serviceregistryFacade.cancelBooking(sr);
                            this.pageLink = "faces/userHome.xhtml";
                            this.displayMessage = "Service cancelled";
                            flag = 1;
                            break;
                        }
                    }
                    if(flag == 0){
                        this.pageLink = "faces/userHome.xhtml";
                        this.displayMessage = "Service not Booked";
                    }
                }
                else{
                    this.pageLink = "faces/userHome.xhtml";
                    this.displayMessage = "Service does not exists, try agian"; 
                }
                break;
        }
        return result;
    }
    
    public String addEventUser(){
        String result = FAILURE;
        EventRegistryDTO event = new EventRegistryDTO();
        event.setEventId(eventId);
        event.setUserId(loggedinUser.getUserid());
        event.setCompletionStatus(1);
        if(eventregistryFacade.addEventSubscriber(event)){
            this.pageLink = "faces/userHome.xhtml";
            this.displayMessage = "successfully Subscribed to event";            
            result = SUCCESS;
        }
        else{
            this.pageLink = "faces/userHome.xhtml";
            this.displayMessage = "Unable to Subscribe for event!";
        }
        return result;
    }
    
    //Service
    public String addServiceToList(){
        String result = FAILURE;
        ServiceRegistryDTO servReg = new ServiceRegistryDTO();
        servReg.setServiceId(this.selectedService);
        servReg.setUserId(this.loggedinUser.getUserid());
        servReg.setSlotBooked(this.selectedSlot);
        servReg.setCompletionStatus(1);
        servReg.setDateBooked(date);
        if(serviceregistryFacade.addServiceTOList(servReg)){
            result = SUCCESS;
            this.pageLink = "faces/bookServiceHome.xhtml";
            this.displayMessage = "Service Added to Cart. Add next Service or Complete Booking";
        }
        else{
            this.pageLink = "faces/bookServiceHome.xhtml";
            this.displayMessage = "Unable to add service, Try again";
        }
        return result;
    }
    public String removeServiceFromList(){
        String result = FAILURE;
        ServiceRegistryDTO serv = serviceregistryFacade.getSeriveFromList(serviceId);
        if(serv != null){
            if(serviceregistryFacade.removeServiceFromList(serv)){
                result = SUCCESS;
                this.pageLink = "faces/bookServiceHome.xhtml";
                this.displayMessage = "Service Successfully Removed";
            }
            else{
                this.pageLink = "faces/addEventsHome.xhtml";
                this.displayMessage = "Unable to Remove event";                
            }            
        }
        else{
            this.displayMessage = "Event Not in List";
            this.pageLink = "faces/addEventsHome.xhtml";
        }        
        return result;
    }
    public String completeServicesList(){
        serviceregistryFacade.completeServiceList();
        this.displayMessage = "Service Added Successfully";
        this.pageLink = "faces/userHome.xhtml";
        return SUCCESS;
    }
    
    public String userEventServiceBooked(){
        String result = FAILURE;
        switch(selectedValue){
            case "event":
                List<String> tempEvent = eventregistryFacade.getUserBookedEvent(loggedinUser.getUserid());
                if(tempEvent != null){
                    this.pageLink = "faces/userHome.xhtml";
                    this.displayMessage = "User Booked Events " +tempEvent.toString(); 
                    result = SUCCESS;
                }
                else{
                    this.pageLink = "faces/userHome.xhtml";
                    this.displayMessage = "Event booking List empty, try agian"; 
                }
                break;
            case "service":
                List<ServiceRegistryDTO> tempService = serviceregistryFacade.getServiceByUser(loggedinUser.getUserid());
                if(tempService != null){
                    this.pageLink = "faces/userHome.xhtml";
                    List<String> services = new ArrayList<String>();
                    for( ServiceRegistryDTO ser : tempService){
                        services.add(ser.getServiceId());
                    }
                    if(services != null){
                        this.displayMessage = "User Booked Services" + services.toString();
                    }
                    else
                        this.displayMessage = "No services Booked.";
                    result = SUCCESS;
                }
                else{
                    this.pageLink = "faces/adminHome.xhtml";
                    this.displayMessage = "Service does not exists, try agian"; 
                }
                break;
        }
        return result;        
    }
    public String updateUserDetails(){
        String result =FAILURE;
         if(newData != null){
                switch(this.selectedValue){
                    case "fname": loggedinUser.setFirstname(this.newData);
                                 break;
                    case "lname": loggedinUser.setLastname(this.newData);
                                 break;        
                    case "password": loggedinUser.setPassword(this.newData);
                                 break;               
                    case "email": loggedinUser.setEmailid(this.newData);
                                 break;                             
                    case "contact": loggedinUser.setContactnumber(this.newData);
                                 break;                 
                    case "address": loggedinUser.setAddress(this.newData);
                                 break;                             
                    case "account": loggedinUser.setAccounttype(this.newData);
                                 break;                    
                }
                if(useraccountFacade.updateUser(loggedinUser)){
                    result = SUCCESS;
                    this.pageLink = "faces/userHome.xhtml";
                    this.displayMessage = "User Successfully edited";
                }
                else{
                    this.pageLink = "faces/userHome.xhtml";
                    this.displayMessage = "Unable to Edit Account";                
                }
            }        
        return result;
    }
    
    //Register User
    public String registerUser(){
        String result = FAILURE;
        UserAccountDTO user = new UserAccountDTO();
        user.setUserid(userId);
        user.setFirstname(firstName);
        user.setLastname(lastName);
        user.setPassword(password);
        user.setEmailid(email);
        user.setContactnumber(contact);
        user.setAccounttype(account);
        if(useraccountFacade.addUser(user)){
            result = SUCCESS;
            this.pageLink = "faces/index.xhtml";
            this.displayMessage = "Account Created Successfully";
        }
        else{
            this.pageLink = "faces/index.xhtml";
            this.displayMessage = "Unable to create New User";
        }
        return result;
    }
    //Logout
    public String logoutUser(){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.invalidate();            
        return LOGOUT;
    }
}