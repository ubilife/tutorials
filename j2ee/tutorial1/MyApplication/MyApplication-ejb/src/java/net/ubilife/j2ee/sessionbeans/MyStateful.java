package net.ubilife.j2ee.sessionbeans;

import javax.ejb.Stateful;

/**
 * This stateful Enterprise Java Bean uses a remote interface.
 * All methods defined in the "MyStatefulRemote" interface can be 
 * called by clients both inside and outside the container.
 *
 * Stateful beans maintains a state (i.e. instance variable values)
 * to a particular client. Thus, for each client there is one stateful
 * bean instance managed by the container. 
 * 
 * To deploy & run the application, right-click "MyApplication" project, 
 * and choose "Run".
 * 
 * @author Ubilife Lab
 */
@Stateful
public class MyStateful implements MyStatefulRemote {
 
    public String helloStateful(String name) {
        return "Hello from stateful bean, "+name; 
    }
    
}
