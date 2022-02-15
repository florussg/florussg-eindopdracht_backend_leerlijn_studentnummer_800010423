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

    private int bsnnumber;

    private int phonenumber;

    @OneToMany (mappedBy = "carCustomer", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Car> ownedCar; //ArrayList van maken?~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @OneToMany (mappedBy = "appointmentOfCustomer")
    private List<Appointment> customerAppointment;

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

    public List<Car> getOwnedCar() {
        return ownedCar;
    }

    public void setOwnedCar(List<Car> ownedCar) {
        this.ownedCar = ownedCar;
    }

    public List<Appointment> getCustomerAppointment() {
        return customerAppointment;
    }

    public void setCustomerAppointment(List<Appointment> customerAppointment) {
        this.customerAppointment = customerAppointment;
    }
}

