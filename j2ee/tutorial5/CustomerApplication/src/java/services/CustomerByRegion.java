package services;

import beans.CustomerManager;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Ubilife Lab
 */
@Path("customerByRegion")
public class CustomerByRegion {

    @EJB
    private CustomerManager manager;
    
    public CustomerByRegion() {  
    }
    
    @GET    
    @Path("{r}")
    @Produces(MediaType.TEXT_HTML)    
    public String getCustomerByRegionPath(@PathParam("r") String region) {
        return "<h3>Customer count ("+region+"): " + manager.getCustomerCountByRegion(region) + "</h3>";
    }
    
    @GET    
    @Produces(MediaType.TEXT_HTML)    
    public String getCustomerByRegionGet(@QueryParam("r") @DefaultValue("East") String region) {
        return "<h3>Customer count ("+region+"): " + manager.getCustomerCountByRegion(region) + "</h3>";
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String getCustomerByRegionPost(@FormParam("region") String r) {
        return "<h3>Customer count ("+r+"): "  + manager.getCustomerCountByRegion(r) + "</h3>";
    }
   
    @GET
    @Path("all/")
    @Produces(MediaType.TEXT_HTML)    
    public String customerCount() {
        return "<h3>All customers: " + manager.getCustomerCount() + "</h3>";
    }
}
