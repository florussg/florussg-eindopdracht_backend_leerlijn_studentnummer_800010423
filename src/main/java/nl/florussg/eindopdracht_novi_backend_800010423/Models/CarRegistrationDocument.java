package nl.florussg.eindopdracht_novi_backend_800010423.Models;

import javax.persistence.*;

@Entity
public class CarRegistrationDocument {

    @Id
    @GeneratedValue
    private long id;

    private String fileName;

    @Lob
    //@Type(type = "org.hibernate.type.ImageType")
    byte[] dataFileName;

    @OneToOne
    Car carRegistrationDocument;

    //constructor
    public CarRegistrationDocument() {
    }

    public CarRegistrationDocument(String fileName, byte[] dataFileName) {
        this.fileName = fileName;
        this.dataFileName = dataFileName;
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

    public byte[] getDataFileName() {
        return dataFileName;
    }

    public void setDataFileName(byte[] dataFileName) {
        this.dataFileName = dataFileName;
    }

    public Car getCarRegistrationDocument() {
        return carRegistrationDocument;
    }

    public void setCarRegistrationDocument(Car carRegistrationDocument) {
        this.carRegistrationDocument = carRegistrationDocument;
    }
}
