
package net.ubilife.j2ee.jpa;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/** This will be embedded in the Person entity. In database, 
 * this results in one table having both Person fields and Address fields.
 *
 * @author Ubilife Lab
 */
@Embeddable
public class Address implements Serializable {

    private String street;
    private String city;
    private int zipCode;
    private String country;

    @Column(name = "STREET")
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }

    @Column(name = "CITY")
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "STATE")
    public int getZipCode() {
        return zipCode;
    }
    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }
    
    @Column(name = "COUNTRY")
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Address{" + "street=" + street + ", city=" + city + ", zipCode=" + zipCode + ", country=" + country + '}';
    }
    
}

