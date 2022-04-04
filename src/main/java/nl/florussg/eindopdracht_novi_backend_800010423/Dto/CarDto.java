package nl.florussg.eindopdracht_novi_backend_800010423.Dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CarDto {

    //attributen
    private long id;

    @NotBlank
    @Size(min=3, max=50)
    private String brand;

    private String type;

    @Size(max=10)
    private String licenseplateNumber;


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

    public String getLicenseplateNumber() {
        return licenseplateNumber;
    }

    public void setLicenseplatenumber(String licenseplatenumber) {
        this.licenseplateNumber = licenseplatenumber;
    }


}
