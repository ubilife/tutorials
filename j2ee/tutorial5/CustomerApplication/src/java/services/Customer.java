package services;

import beans.CustomerManager;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Ubilife Lab
 */
@Path("customer")
public class Customer {

    @EJB
    private CustomerManager manager;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getCustomerCount() {
        return "<h3>Customer Count: " + manager.getCustomerCount() + "</h3>";
    }
}
