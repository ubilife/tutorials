package net.ublife.j2ee.sessionbeans.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.ubilife.j2ee.sessionbeans.MySingletonLocal;
import net.ubilife.j2ee.sessionbeans.MyStatefulRemote;
import net.ubilife.j2ee.sessionbeans.MyStateless;

/**
 * This servlet tests the beans in the MyApplication-ejb module.
 *
 * We use both dependency injection (@EJB) and JNDI to access the bean.
 * 
 * To deploy & run the application, right-click "MyApplication" project, 
 * and choose "Run".
 * 
 * @author Ubilife Lab
 */
@WebServlet(name = "Client", urlPatterns = {"/"})
public class Client extends HttpServlet {

    // Dependency injection. Always use interface type when it exists.
    @EJB
    private MyStatefulRemote stateful;
    
    // Dependency injection. MyStateless has no-interface, 
    // so we use the bean type. 
    @EJB
    private MyStateless stateless;
    
    // We'll use JNDI to get reference to this bean (see below).
    private MySingletonLocal singleton; 
    
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
            out.println("<title>Session Bean Client</title>");            
            out.println("</head>");
            out.println("<body>");
            
            /*  Get a references to our singleton bean with JNDI.
             * You can verify the Portable JNDI name ("java:global....")
             * by checking the GlassFish log after deploying the application.
             */
            Context ctx = new InitialContext();            
            singleton = (MySingletonLocal) ctx.lookup("java:global/MyApplication/MyApplication-ejb/MySingleton!net.ubilife.j2ee.sessionbeans.MySingletonLocal");
                        
            // call the beans            
            out.println(stateless.helloStateless("Ubilife Lab") + "<br />");
            out.println(stateful.helloStateful("Ubilife Lab") + "<br />");
            out.println(singleton.helloSingleton("Ubilife Lab") + "<br />");
            
            
            out.println("</body>");
            out.println("</html>");
        } catch (NamingException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
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
