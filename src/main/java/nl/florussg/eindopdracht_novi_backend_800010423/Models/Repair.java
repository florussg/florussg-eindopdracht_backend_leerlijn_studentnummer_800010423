package nl.florussg.eindopdracht_novi_backend_800010423.Models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Repair {

    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    private String finding;

    @OneToMany (mappedBy = "repair")
    private List<RepairPart> partToRepair;

    private RepairStatus repairStatus;

    @OneToOne
    private Appointment repairAppointment;

    //getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<RepairPart> getPartToRepair() {
        return partToRepair;
    }

    public void setPartToRepair(List<RepairPart> partToRepair) {
        this.partToRepair = partToRepair;
    }

    public String getFinding() {
        return finding;
    }

    public void setFinding(String finding) {
        this.finding = finding;
    }

    public RepairStatus getRepairStatus() {
        return repairStatus;
    }

    public void setRepairStatus(RepairStatus repairStatus) {
        this.repairStatus = repairStatus;
    }

    public Appointment getRepairAppointment(Appointment appointmentId) {
        return repairAppointment;
    }

    public void setRepairAppointment(Appointment repairAppointment) {
        this.repairAppointment = repairAppointment;
    }

    public void setRepairAppointmentById(Long repairAppointmentId) {
        this.repairAppointment = repairAppointment;
    }

}



