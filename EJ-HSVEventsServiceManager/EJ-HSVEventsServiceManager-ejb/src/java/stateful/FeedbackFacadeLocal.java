/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stateful;

import entity.Feedback;
import entity.FeedbackDTO;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Vish
 */
@Local
public interface FeedbackFacadeLocal {

    void create(Feedback feedback);

    void edit(Feedback feedback);

    void remove(Feedback feedback);

    Feedback find(Object id);

    List<Feedback> findAll();

    List<Feedback> findRange(int[] range);

    int count();
    boolean addFeedback(FeedbackDTO feedback);
}
