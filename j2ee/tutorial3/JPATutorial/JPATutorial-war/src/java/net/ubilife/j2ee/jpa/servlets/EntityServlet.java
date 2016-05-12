
package net.ubilife.j2ee.jpa.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.ubilife.j2ee.jpa.*;

/**
 * This code demonstrates how to use various methods of 
 * PersonFacade, which is a stateless session bean managing 
 * Person entities (see JPATutorial-ejb module).
 * 
 * @author Ubilife Lab
 */
@WebServlet(name = "EntityServlet", urlPatterns = {"/"})
public class EntityServlet extends HttpServlet {

    @EJB 
    private PersonFacade facade;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Client for PersonFacade</title>");            
            out.println("</head>");
            out.println("<body>");
            
            Person person1, person2;
            
             // create John Doe
            person1 = new Person();
            person1.setFirstName("John");
            person1.setLastName("Doe");
            Address addr = new Address();
            addr.setStreet("Kidney Bean Drive 42");
            addr.setCity("Beanville");
            addr.setZipCode(12412);
            addr.setCountry("Beantopia");
            person1.setAddress(addr);
            Calendar c = Calendar.getInstance();
            c.set(1979, 3, 15);
            person1.setDateOfBirth(c.getTime());

            // create Jane Doe
            person2 = new Person();
            person2.setFirstName("Jane");
            person2.setLastName("Doe");
            addr = new Address();
            addr.setStreet("Coffee Bean Avenue 42");
            addr.setCity("Beantown");
            addr.setZipCode(23567);
            addr.setCountry("Beantopia");
            person2.setAddress(addr);
            c.set(1984, 7, 23);
            person2.setDateOfBirth(c.getTime());

            // persist John and Jane into database
            facade.create(person1);
            facade.create(person2);

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Entity Example</title>");
            out.println("</head>");
            out.println("<body>");
            
            
            // get and print all rows from the database
            out.println("<br />Listing all Persons in the database: ");
            for (Person p : facade.findAll()) {
                out.println("<br>"+p.toString());
            }
            
            // search persons by city (custom method)
              
            out.println("<br /><br />Search persons living in Beantown: ");
            for (Person p : facade.findPersonsByCity("Beantown")) {
                out.println("<br>"+p.toString());
            }
            
            // update Jane's first name
            person2.setFirstName("Janet");
            facade.edit(person2);
            out.println("<br /><br />Updated Jane: " + facade.find(person2.getId()).toString());

            // remove John            
            facade.remove(person1);
            out.println("<br /><br />Removed John, printing all remaining Persons: ");
            for (Person p : facade.findAll()) {
                out.println("<br>" + p.toString());
            }

            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
