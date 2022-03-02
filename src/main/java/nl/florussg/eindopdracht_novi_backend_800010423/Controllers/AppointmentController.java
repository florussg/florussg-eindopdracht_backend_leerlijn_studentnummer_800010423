package nl.florussg.eindopdracht_novi_backend_800010423.Controllers;

import nl.florussg.eindopdracht_novi_backend_800010423.Services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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


}
