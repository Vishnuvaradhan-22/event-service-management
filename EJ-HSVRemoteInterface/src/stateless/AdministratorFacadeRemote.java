/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stateless;

import entity.AdministratorDTO;
import entity.UserAccountDTO;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Vish
 */
@Remote
public interface AdministratorFacadeRemote {
/**
    void create(entity.Administrator administrator);

    void edit(entity.Administrator administrator);

    void remove(entity.Administrator administrator);

    entity.Administrator find(Object id);

    List<Administrator> findAll();

    List<Administrator> findRange(int[] range);
**/
    int count();
    boolean verifyAdmin(AdministratorDTO admin);
    boolean verifySecretKey(AdministratorDTO admin);
    boolean editPassword(AdministratorDTO admin);
    boolean freezeUserAccount(UserAccountDTO user);
    AdministratorDTO finAdmin(String adminId);
}
