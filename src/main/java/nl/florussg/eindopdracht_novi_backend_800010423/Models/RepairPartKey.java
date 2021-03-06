package nl.florussg.eindopdracht_novi_backend_800010423.Models;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RepairPartKey implements Serializable {

    //attributes
    long partId;

    long repairId;

    //constructor
    public RepairPartKey(){}

    public RepairPartKey(long partId, long repairId) {
        this.partId = partId;
        this.repairId = repairId;
    }

    //getters and setters
    public long getPartId() {
        return partId;
    }

    public void setPartId(long partid) {
        this.partId = partid;
    }

    public long getRepairId() {
        return repairId;
    }

    public void setRepairId(long repairId) {
        this.repairId = repairId;
    }


    public boolean equals(final Object o) {
        if (o == this)
            return true;
        if (!(o instanceof RepairPartKey))
            return false;
        final RepairPartKey other = (RepairPartKey) o;

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
