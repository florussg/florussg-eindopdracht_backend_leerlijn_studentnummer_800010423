package nl.florussg.eindopdracht_novi_backend_800010423.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Car {

    //attributes
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long id;

    private String brand;

    private String type;

    private String licenseplatenumber;

    private String fileNameCarRegistrationDocument;
    // Nog te bepalen hoe ik de uploadfunctie ga inrichten //
    //private VectorOperators.Binary carpapers; of multipart.MultipartFile;


    //getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        return licenseplatenumber;
    }

    public void setLicenseplatenumber(String licenseplatenumber) {
        this.licenseplatenumber = licenseplatenumber;
    }

    public String getFileNameCarRegistrationDocument() {
        return fileNameCarRegistrationDocument;
    }

    public void setFileNameCarRegistrationDocument(String fileNameCarRegistrationDocument) {
        this.fileNameCarRegistrationDocument = fileNameCarRegistrationDocument;
    }
}
