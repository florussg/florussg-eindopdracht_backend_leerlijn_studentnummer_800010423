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

    @Autowired
    private RepairPartRepository repairPartRepository;


       public long addRepair (Repair repair, long appointmentId) {
        if (checkIfAppointmentHasRepairBooleanTrue(appointmentId) == false) {
            throw new BadRequestException("You cant add a repair while the appointment doesnt have a repair-value on true!");
        } else {
            //Repair saveRepair = new Repair();
            //saveRepair.setRepairAppointmentById(appointmentId);
            //repairRepository.save(saveRepair);
            Repair repairToSave = new Repair();
            repairToSave.setRepairAppointmentById(appointmentId);
            repairToSave.setRepairStatus(repair.getRepairStatus());
            repairToSave.setPartToRepair(repair.getPartToRepair());
            repairToSave.setFinding(repair.getFinding());

           var test = repairRepository.save(repairToSave);
           test.getId();
           if(appointmentRepository.findById(appointmentId).isPresent()){
               Appointment appointment = appointmentRepository.findById(appointmentId).get();
               linkRepairToAppointment(test.getId(), appointmentId);
           }
           return repairToSave.getId();
        }
    }

    public long linkRepairToAppointment (long repairId, long appointmentId) {
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

    public Repair setRepairStatus (long idRepair, RepairStatus repairStatus) {

        Optional<Repair> optionalRepair = repairRepository.findById(idRepair);

        if (optionalRepair.isPresent()) {
            Repair repairToChangeStatus = optionalRepair.get();
            repairToChangeStatus.setRepairStatus(repairStatus);

            repairRepository.save(repairToChangeStatus);
            return repairToChangeStatus;
        } else {
            throw new RecordNotFoundException("There is no repair with this id");
        }
    }

    public List<Repair> getRepairWithStatusStarted(String repairStatus) {
        List<Repair> allRepairsWithStatusStarted = repairRepository.findRepairByRepairStatus(repairStatus);
        if (allRepairsWithStatusStarted.size() > 0) {
            return allRepairsWithStatusStarted;
        } else {
            throw new RecordNotFoundException("There are no repairs with a repair status Started");
        }
    }



//    public List<Repair> getAllOpenRepairs() {
//    }
//
//    public Repair getOneRepairWithPartsToRepair (long id) {
//        List<>
//
//        return null;
//    }









    // methods

    public boolean checkIfAppointmentHasRepairBooleanTrue (long appointmentId) {

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
}
