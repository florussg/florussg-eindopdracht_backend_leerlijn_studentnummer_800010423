package nl.florussg.eindopdracht_novi_backend_800010423.Services;

import nl.florussg.eindopdracht_novi_backend_800010423.Exceptions.BadRequestException;
import nl.florussg.eindopdracht_novi_backend_800010423.Exceptions.RecordNotFoundException;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.Appointment;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.Repair;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.RepairStatus;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.AppointmentRepository;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.RepairPartRepository;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RepairService {

    @Autowired
    private RepairRepository repairRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    public long createRepairAndLinkItToTheAppointment(Repair repair, long appointmentId) {
        if (appointmentRepository.findById(appointmentId).isPresent()) {
            if (checkIfAppointmentHasRepairBooleanTrue(appointmentId) == false) {
                throw new BadRequestException("You cant add a repair while the appointment doesnt have a repair-value on true!");

            } else {
                var repairSaved = repairRepository.save(repair);

                linkRepairToAppointment(repairSaved.getId(), appointmentId);
                autoSetStartingRepairStatus(repairSaved.getId());
                return repair.getId();
            }

        } else {
            throw new RecordNotFoundException("There is no appointment with this id!");
        }
    }

    public Repair setRepairStatus(long idRepair, Repair inputStatus) {
        Optional<Repair> optionalRepair = repairRepository.findById(idRepair);

        if (optionalRepair.isPresent()) {
            Repair repairToChangeStatus = optionalRepair.get();
            repairToChangeStatus.setRepairStatus(inputStatus.getRepairStatus());

            repairRepository.save(repairToChangeStatus);
            return repairToChangeStatus;
        } else {
            throw new RecordNotFoundException("There is no repair with this id");
        }
    }

    public List<Repair> findRepairByRepairStatus(RepairStatus repairStatus) {
        List<Repair> allRepairs = repairRepository.findRepairByRepairStatusEquals(repairStatus);
        if (allRepairs.size() > 0) {
            return allRepairs;
        } else {
            throw new RecordNotFoundException("There are no repairs based on your input");
        }
    }

    public List<Repair> getAllRepairs() {
        List<Repair> all = repairRepository.findAll();
        return all;
    }

    // methods
    public boolean checkIfAppointmentHasRepairBooleanTrue(long appointmentId) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
        if (optionalAppointment.isPresent()) {
            Appointment appointmentToCheck = optionalAppointment.get();
            if (appointmentToCheck.getRepair() == true) {
                return true;
            } else {
                return false;
            }
        } else {
            throw new RecordNotFoundException("There is no appointment with this id");
        }
    }

    public long linkRepairToAppointment(long repairId, long appointmentId) {
        Optional<Repair> optionalRepair = repairRepository.findById(repairId);
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);

        if (optionalRepair.isPresent() && optionalAppointment.isPresent()) {
            Repair repairToEdit = optionalRepair.get();
            Appointment appointmentToAdd = optionalAppointment.get();

            repairToEdit.setRepairAppointment(appointmentToAdd);

            repairRepository.save(repairToEdit);
            return repairToEdit.getId();
        } else {
            throw new RecordNotFoundException("Repair or appointment does not exist");
        }
    }

    public Repair autoSetStartingRepairStatus(long repairId) {
        Optional<Repair> optionalRepair = repairRepository.findById(repairId);

        if (optionalRepair.isPresent()) {
            Repair repairToSetStatus = optionalRepair.get();

            if (repairToSetStatus.getRepairStatus() == null || repairToSetStatus.getRepairStatus().toString().isEmpty()) {
                repairToSetStatus.setRepairStatus(RepairStatus.PENDING_APPROVAL);

                repairRepository.save(repairToSetStatus);
                return repairToSetStatus;
            }
        }
        return null;
    }
}


