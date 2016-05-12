package beans;

import javax.ejb.Stateless;

/**
 *
 * @author Ubilife Lab
 */
@Stateless
public class CustomerManager {

    public int getCustomerCount() {
        return 27;
    }
    
    public int getCustomerCountByRegion(String region) {
        if (region.equalsIgnoreCase("West")) {
            return 12;
        } else if (region.equalsIgnoreCase("East")) {
            return 15;
        } else {
            return 0;
        }        
    }    
}
