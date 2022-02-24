package nl.florussg.eindopdracht_novi_backend_800010423.Dto;

import nl.florussg.eindopdracht_novi_backend_800010423.Models.Appointment;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.Car;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class CustomerDto {

    //attributen
    private long id;

    @NotBlank
    @Size(min=2) //The firstname has to be a minimal of 2 characters
    private String firstname;

    @NotBlank
    @Size(min=2) //The lastname has to be a minimal of 2 characters
    private String lastname;

    @NotNull
    @Size(min=10, max=10) //Validator on dutch phonenumbers only
    private int phonenumber;

    @NotBlank
    @Size(min=9, max=9)
    private int bsnnumber;

    private List<Car> ownedCar;

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
