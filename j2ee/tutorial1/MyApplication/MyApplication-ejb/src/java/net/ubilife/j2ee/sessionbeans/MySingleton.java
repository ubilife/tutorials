
package net.ubilife.j2ee.sessionbeans;

import javax.ejb.Singleton;

/**
 * This singleton Enterprise Java Bean uses a local interface.
 * The methods defined in the "MySingletonLocal" interface can be 
 * called only by clients in the same container (server).
 *
 * Only one instance of the singleton bean exists throughout the 
 * application lifetime. Therefore, all clients share the same 
 * bean instance. Singleton beans are useful for doing application 
 * initialization and clean-up tasks. 
 * 
 * See: @PostConstruct and @PreDestroy annotations for defining
 * bean life cycle callback methods.
 *
 * To deploy & run the application, right-click "MyApplication" project, 
 * and choose "Run".
 * 
 * @author Ubilife Lab
 */
@Singleton
public class MySingleton implements MySingletonLocal {

    @Override
    public String helloSingleton(String name) {
         return "Hello from singleton bean, "+name; 
    }

}
