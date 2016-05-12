
package net.ubilife.j2ee.jpa;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/** This class differs from tutorial: 
 * 
 * https://www.youtube.com/watch?v=HwscenZsMh0&index=3&list=PLyCD4xKABPa7N5G1gCmJk47ctkhRIMjV9
 * 
 * We have defined two custom methods which extends the basic methods
 * offered by AbstractFacade. 
 *
 * @author Ubilife Lab
 */
@Stateless
public class PersonFacade extends AbstractFacade<Person> {
    @PersistenceContext(unitName = "JPATutorial-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonFacade() {
        super(Person.class);
    }
    
    /** A custom method that finds persons by lastname.
     * 
     * @param lastname Lastname of the persons to be searched.
     * @return A list of persons with given lastname, or empty
     * list in case no persons were found.
     */
    public List<Person> findByLastname(String lastname) {
        
        List<Person> result = new ArrayList<Person>();
        
        for(Person p : findAll()) {
            if(p.getLastName().equalsIgnoreCase(lastname))
                result.add(p);
            
        }
        return result;        
    }
    
    /** A custom method that finds persons by city.
     * 
     * @param city City of the persons to be searched.
     * @return A list of persons who live in given city, or empty
     * list in case no persons were found.
     */
    public List<Person> findPersonsByCity(String city) {
        List<Person> result = new ArrayList<Person>();
        for(Person p : findAll())
            if(p.getAddress().getCity().equalsIgnoreCase(city))
                result.add(p);
        return result;
    }
    
}
