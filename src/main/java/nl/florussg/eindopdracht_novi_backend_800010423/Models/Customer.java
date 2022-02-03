package nl.florussg.eindopdracht_novi_backend_800010423.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {

    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    private String firstname;

    private String lastname;

    private int bsnnumber;

    private int phonenumber;

    //getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getBsnnumber() {
        return bsnnumber;
    }

    public void setBsnnumber(int bsnnumber) {
        this.bsnnumber = bsnnumber;
    }

    public int getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(int phonenumber) {
        this.phonenumber = phonenumber;
    }
}

