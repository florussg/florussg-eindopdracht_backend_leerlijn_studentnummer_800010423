package nl.florussg.eindopdracht_novi_backend_800010423.Models;

import javax.persistence.*;

@Entity
public class CarRegistrationDocument {

    //attributes
    @Id
    @GeneratedValue
    private long id;

    private String fileName;

    private String pathSaved;

    @OneToOne
    Car car;

    //constructor
    public CarRegistrationDocument() {
    }

    //getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setPathSaved(String pathSaved) {
        this.pathSaved = pathSaved;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car carRegistrationDocument) {
        this.car = carRegistrationDocument;
    }
}
