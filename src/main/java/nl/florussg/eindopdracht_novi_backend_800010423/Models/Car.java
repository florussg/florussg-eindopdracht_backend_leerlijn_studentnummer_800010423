package nl.florussg.eindopdracht_novi_backend_800010423.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name= "car")
public class Car {

    //attributes
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    long id;

    @Column(name= "brand")
    private String brand;

    @Column(name= "type")
    private String type;

    @Column(name= "licenseplate_number")
    private String licenseplateNumber;

    //TODO Deze uiteindelijk verwijderen zodra CarRegistrationDocument registrationDocument werkt
    @Column(name= "registration_document")
    private String carRegistrationDocument;

    @OneToOne (mappedBy = "carRegistrationDocument")
    private CarRegistrationDocument registrationDocument;

    @ManyToOne
    @JsonIgnore
    private Customer carCustomer;

    @OneToMany(mappedBy = "carAppointment")
    private List<Appointment> carAppointment;

    //Default constructor
    public Car() {
    }

    //getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLicenseplatenumber() {
        return licenseplateNumber;
    }

    public void setLicenseplatenumber(String licenseplatenumber) {
        this.licenseplateNumber = licenseplatenumber;
    }

    public String getCarRegistrationDocument() {
        return carRegistrationDocument;
    }

    public void setCarRegistrationDocument(String fileNameCarRegistrationDocument) {
        this.carRegistrationDocument = fileNameCarRegistrationDocument;
    }

    public Customer getCarCustomer() {
        return carCustomer;
    }

    public void setCarCustomer(Customer carCustomer) {
        this.carCustomer = carCustomer;
    }

    public List<Appointment> getCarAppointment() {
        return carAppointment;
    }

    public void setCarAppointment(List<Appointment> carAppointment) {
        this.carAppointment = carAppointment;
    }

    public CarRegistrationDocument getRegistrationDocument() {
        return registrationDocument;
    }

    public void setRegistrationDocument(CarRegistrationDocument registrationDocument) {
        this.registrationDocument = registrationDocument;
    }
}
