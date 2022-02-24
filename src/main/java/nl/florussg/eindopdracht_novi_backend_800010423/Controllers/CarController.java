package nl.florussg.eindopdracht_novi_backend_800010423.Controllers;

import nl.florussg.eindopdracht_novi_backend_800010423.Dto.CarDto;
import nl.florussg.eindopdracht_novi_backend_800010423.Services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping (value = "/cars")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getAllCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }

    @GetMapping (value = "/cars/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getOneCarById (@PathVariable long id) {
        return ResponseEntity.ok(carService.getOneCarById(id));
    }

    @GetMapping (value = "cars/licenseplate")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getOneCarByLicenseplateNumber(@RequestParam(name = "number", defaultValue = "") String licenseplatenumber) {
        return ResponseEntity.ok(carService.getCarByLicenseplateNumber(licenseplatenumber));
    }

    @PostMapping (value = "cars/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> addNewCar(@Valid @RequestBody CarDto carDto) {
        long newId = carService.addNewCar(carDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();
        return ResponseEntity.created(location).build();
    }

    //Vragen, werkt niet
    @DeleteMapping (value = "/cars/{licenseplateNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteCarByLicenseplateNumber (@PathVariable String licenseplateNumber) {
        carService.deleteCarByLicenseplateNumber(licenseplateNumber);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/cars/{licenseplateNumber}" )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> editCar (@PathVariable String licenseplateNumber, @RequestBody CarDto carDto ) {
        carService.editCar(licenseplateNumber, carDto);
        return ResponseEntity.noContent().build();
    }




}
