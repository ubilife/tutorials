
package net.ubilife.j2ee.sessionbeans;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 * This stateless Enterprise Java Bean uses so-called "no-interface"
 * technique, where all public methods will be automatically
 * included in the local interface. That is, only clients in the 
 * same container (server) can call the public methods of this bean.
 * 
 * Stateless bean does not maintain a state (i.e. instance variable values).
 * This means that one bean instance can serve multiple clients. 
 * Therefore, do not save anything to stateless beans' instance variables
 * because it is not guaranteed that the same bean instance will serve 
 * the same client upon next method call.
 * 
 * To deploy & run the application, right-click "MyApplication" project, 
 * and choose "Run".
 * 
 * @author Ubilife Lab
 */
@Stateless
@LocalBean
public class MyStateless {
   
    public String helloStateless(String name) {        
        return "Hello from stateless bean, "+name;                
    }
    
}
