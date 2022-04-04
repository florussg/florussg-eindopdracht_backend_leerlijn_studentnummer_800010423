package nl.florussg.eindopdracht_novi_backend_800010423.Models;

import javax.persistence.*;

@Entity
public class CarRegistrationDocument {

    @Id
    @GeneratedValue
    private long id;

    private String fileName;

    private String pathSaved;

//    @Lob
//    //@Type(type = "org.hibernate.type.ImageType")
//    byte[] dataFileName;

    @OneToOne
    Car car;

    //constructor
    public CarRegistrationDocument() {
    }

//    public CarRegistrationDocument(String fileName, byte[] dataFileName) {
//        this.fileName = fileName;
//        this.dataFileName = dataFileName;
//    }

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

//    public byte[] getDataFileName() {
//        return dataFileName;
//    }
//
//    public void setDataFileName(byte[] dataFileName) {
//        this.dataFileName = dataFileName;
//    }

    public String getPathSaved() {
        return pathSaved;
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
