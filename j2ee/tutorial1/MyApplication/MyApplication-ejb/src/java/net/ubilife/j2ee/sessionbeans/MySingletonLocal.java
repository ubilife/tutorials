package net.ubilife.j2ee.sessionbeans;

import javax.ejb.Local;

/**
 *
 * @author Ubilife Lab
 */
@Local
public interface MySingletonLocal {
    String helloSingleton(String name);
}
