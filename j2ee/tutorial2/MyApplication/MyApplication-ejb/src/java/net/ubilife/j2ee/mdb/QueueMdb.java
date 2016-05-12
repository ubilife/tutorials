
package net.ubilife.j2ee.mdb;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * This MDB receives messages from the jms/myqueue queue. Before deploying this
 * application, make sure to create necessary JMS resources using 
 * the GlassFish Domain Admin Console. See: 
 * 
 * https://www.youtube.com/watch?v=_EqhUKNXkcM&index=2&list=PLyCD4xKABPa7N5G1gCmJk47ctkhRIMjV9
 *  
 * To deploy & run: Right click "My Application" project, and choose "Run"
 * 
 * @author Ubilife Lab
 */
@JMSDestinationDefinition(name = "jms/myqueue", interfaceName = "javax.jms.Queue", resourceAdapter = "jmsra", destinationName = "myqueue")
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/myqueue"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class QueueMdb implements MessageListener {
    
    public QueueMdb() {
    }
    
    @Override
    public void onMessage(Message message) {
        
        try {
            String id;            
            TextMessage msg = (TextMessage) message;            
            id = msg.getStringProperty("ID");
            System.out.println("ID: "+id);
            System.out.println("QueueMdb msg: "+msg.getText());
        } catch (JMSException ex) {
            Logger.getLogger(QueueMdb.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        
    }
    
}
