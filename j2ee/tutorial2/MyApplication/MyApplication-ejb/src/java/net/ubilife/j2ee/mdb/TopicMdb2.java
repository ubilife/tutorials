
package net.ubilife.j2ee.mdb;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * This MDB receives messages from the jms/mytopic topic. Before deploying this
 * application, make sure to create necessary JMS resources using 
 * the GlassFish Domain Admin Console. See: 
 * 
 * https://www.youtube.com/watch?v=_EqhUKNXkcM&index=2&list=PLyCD4xKABPa7N5G1gCmJk47ctkhRIMjV9
 * 
 * To deploy & run: Right click "My Application" project, and choose "Run"
 * 
 * @author Ubilife Lab
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/mytopic"),
    @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
    @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "jms/mytopic")
})
public class TopicMdb2 implements MessageListener {
    
    public TopicMdb2() {
    }
    
    @Override
    public void onMessage(Message message) {
          try {        
            TextMessage msg = (TextMessage) message;
            System.out.println("TopicMdb2 msg: "+msg.getText());
        } catch (JMSException ex) {
            Logger.getLogger(QueueMdb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
