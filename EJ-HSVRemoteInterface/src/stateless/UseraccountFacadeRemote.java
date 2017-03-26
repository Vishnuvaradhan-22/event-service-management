/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stateless;

import entity.UserAccountDTO;
import entity.Useraccount;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Vish
 */
@Remote
public interface UseraccountFacadeRemote {

    void create(Useraccount useraccount);

    void edit(Useraccount useraccount);

    void remove(Useraccount useraccount);

    Useraccount find(Object id);

    List<Useraccount> findAll();

    List<Useraccount> findRange(int[] range);

    int count();
    boolean addUser(UserAccountDTO user);
    boolean updateUser(UserAccountDTO user);
    boolean verifyUser(UserAccountDTO user);
    UserAccountDTO findUserById(String userId);
}
