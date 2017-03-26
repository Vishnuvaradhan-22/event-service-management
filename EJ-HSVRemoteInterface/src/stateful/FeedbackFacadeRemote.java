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
import javax.ejb.Remote;

/**
 *
 * @author Vish
 */
@Remote
public interface FeedbackFacadeRemote {

    void create(Feedback feedback);

    void edit(Feedback feedback);

    void remove(Feedback feedback);

    Feedback find(Object id);

    List<Feedback> findAll();

    List<Feedback> findRange(int[] range);

    int count();
    boolean addFeedbackToList(FeedbackDTO feedback);
    ArrayList<FeedbackDTO> getAllFeedback();
    List<FeedbackDTO> getFeedbackByEvent(String eventId);
    List<FeedbackDTO> getFeedbackByService(String serviceId);
    boolean submitFeedback();
}
