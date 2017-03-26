/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stateful;

import entity.ServiceRegistryDTO;
import entity.Serviceregistry;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Vish
 */
@Local
public interface ServiceregistryFacadeLocal {

    void create(Serviceregistry serviceregistry);

    void edit(Serviceregistry serviceregistry);

    void remove(Serviceregistry serviceregistry);

    Serviceregistry find(Object id);

    List<Serviceregistry> findAll();

    List<Serviceregistry> findRange(int[] range);

    int count();
    boolean addServiceTORegister(ServiceRegistryDTO service);
}
