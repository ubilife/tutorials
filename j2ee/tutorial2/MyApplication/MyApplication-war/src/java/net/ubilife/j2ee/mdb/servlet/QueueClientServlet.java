/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ubilife.j2ee.mdb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This MDB sends a message to the jms/myquee queue. Before deploying this
 * application, make sure to create necessary JMS resources using 
 * the GlassFish Domain Admin Console. See: 
 * 
 * https://www.youtube.com/watch?v=_EqhUKNXkcM&index=2&list=PLyCD4xKABPa7N5G1gCmJk47ctkhRIMjV9
 * 
 * To deploy & run: Right click "My Application" project, and choose "Run"
 * 
 * @author Ubilife Lab
 */
@WebServlet(name = "QueueClientServlet", urlPatterns = {"/queueclient"})
public class QueueClientServlet extends HttpServlet {

    @Resource(mappedName = "jms/myqueueFactory")
    private QueueConnectionFactory connFactory;

    @Resource(mappedName = "jms/myqueue")
    private Queue queue;
    
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
            out.println("<title>MDB queue client</title>");
            out.println("</head>");
            out.println("<body>");
                        
            // Send a message to queue "jms/myqueue"            
            Connection conn = connFactory.createConnection();
            Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            
            MessageProducer mp = session.createProducer(queue);
            TextMessage msg = session.createTextMessage();
            msg.setText("Hello World - Queue!");
            msg.setStringProperty("ID", "1");
            mp.send(msg);
            
            out.println("<H1>Message sent to queue! (see GlassFish server log)</H1>");
            
                       
           
            out.println("</body>");
            out.println("</html>");
        } catch (JMSException ex) {
            Logger.getLogger(QueueClientServlet.class.getName()).log(Level.SEVERE, null, ex);
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
