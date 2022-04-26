package nl.florussg.eindopdracht_novi_backend_800010423.Models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name= "customer")
public class Customer {

    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstname;

    private String lastname;

    @Column(name = "bsn_number") //Even though this is not clean code, the @Column gives you more control over the data.sql input
    private int bsnnumber;

    @Column(name = "phone_number")
    private int phonenumber;

    @OneToMany (mappedBy = "carCustomer", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Car> ownedCars;

    @OneToMany(mappedBy = "customerAppointment")
    private List<Appointment> customerAppointments;

    //Default constructor
    public Customer() {
    }

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

    public List<Car> getOwnedCars() {
        return ownedCars;
    }

    public List<Appointment> getCustomerAppointments() {
        return customerAppointments;
    }
}

