package nl.florussg.eindopdracht_novi_backend_800010423.Models;


import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

//Nog geen idee hoe ik de tussenklasse moet gaan inrichten~~~~~~~~~~~~~~~~~~~~~~~~~~~~
@Embeddable
public class RepairPartKey implements Serializable {

    //attributen
    Long partid;

    Long repairId;

    //getters and setters
    public Long getPartId() {
        return partid;
    }

    public void setPartId(Long partid) {
        this.partid = partid;
    }

    public Long getRepairId() {
        return repairId;
    }

    public void setRepairId(Long repairId) {
        this.repairId = repairId;
    }


    public boolean equals(final Object o) {
        if (o == this)
            return true;
        if (!(o instanceof RepairPartKey))
            return false;
        //final nodig?~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        final RepairPartKey other = (RepairPartKey) o;

        //Is dit allemaal nodig?~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        if (!other.canEqual((Object) this))
            return false;
        final Object this$repairId = this.getRepairId();
        final Object other$repairId = other.getRepairId();
        if (this$repairId == null ? other$repairId != null : !this$repairId.equals(other$repairId)) return false;
        final Object this$partId = this.getPartId();
        final Object other$partId = other.getPartId();
        if (this$partId == null ? other$partId != null : !this$partId.equals(other$partId)) return false;
        return true;
    }

    protected boolean canEqual(final Object otherOne) {
        return otherOne instanceof RepairPartKey;
    }

    public int hashCode() {
    return Objects.hash(getRepairId(), getPartId());
    }


}
