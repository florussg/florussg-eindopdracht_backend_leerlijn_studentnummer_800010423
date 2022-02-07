package nl.florussg.eindopdracht_novi_backend_800010423.Models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Part {

    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    private String description;

    private BigDecimal price;

    @OneToMany (mappedBy = "part")
    private List<RepairPart> partForRepair;

    //getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
