
package net.ubilife.j2ee.sessionbeans;

import javax.ejb.Remote;

/**
 *
 * @author Ubilife Lab
 */
@Remote
public interface MyStatefulRemote {
    
    String helloStateful(String name);
}
