package nl.florussg.eindopdracht_novi_backend_800010423.Controllers;

import nl.florussg.eindopdracht_novi_backend_800010423.Models.Appointment;
import nl.florussg.eindopdracht_novi_backend_800010423.Services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping (value= "/appointments")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    @GetMapping (value= "/appointments/apk")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getAllApkAppointments() {
        return ResponseEntity.ok(appointmentService.getAllApkAppointments());
    }

    @GetMapping (value= "/appointments/repair")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getAllRepairAppointments() {
        return ResponseEntity.ok(appointmentService.getAllRepairAppointments());
    }

    @PostMapping (value = "/appointments/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> addNewAppointment(@RequestBody Appointment appointment) {
        long newId = appointmentService.addNewAppointment(appointment);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping (value = "/appointments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteAppointment (@PathVariable long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/appointments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> editAppointment (@PathVariable long id, @RequestBody Appointment appointment) {
        appointmentService.editAppointment(id, appointment);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/appointments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> partialEditAppointment (@PathVariable long id, @RequestBody Appointment appointment) {
        appointmentService.partialEditAppointment(id, appointment);
        return ResponseEntity.noContent().build();
    }

    //Possible parameters are = "STARTED", "PASS", "FAIL", "APK_INSPECTION_FAILED"
    @PatchMapping(value = "/appointments/{id}/setapkstatus")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> setApkStatus (@PathVariable long id, @RequestBody Appointment appointment) {
        appointmentService.setApkStatus(id, appointment);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value =  "/appointments/{id}/customer")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> addCustomerToAppointment (@PathVariable long id, @RequestBody int bsnnumber ) {
        appointmentService.addCustomerToAppointment(id, bsnnumber);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/appointments/{id}/car")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> addCarToAppointment(@PathVariable long id, @RequestBody String licenseplateNumber) { //alternative @RequestParam(name = "license", defaultValue = "") String licenseplateNumber)
        appointmentService.addCarToAppointment(id, licenseplateNumber);
        return ResponseEntity.noContent().build();
    }
}
