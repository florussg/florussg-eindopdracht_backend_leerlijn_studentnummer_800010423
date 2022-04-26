package nl.florussg.eindopdracht_novi_backend_800010423.Controllers;

import nl.florussg.eindopdracht_novi_backend_800010423.Models.Car;
import nl.florussg.eindopdracht_novi_backend_800010423.Services.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


@ExtendWith({MockitoExtension.class})
class CarControllerTest {

    @InjectMocks
    CarController carController;

    @Mock
    CarService carService;

    Car carOne = new Car();
    Car carTwo = new Car();

    List<Car> cars = new ArrayList<>();

    @BeforeEach
    public void setUp() {

        carOne.setId(1L);
        carOne.setBrand("Seat");
        carOne.setType("Leon");
        carOne.setLicenseplatenumber("31-LZ-XL");

        carTwo.setId(2L);
        carTwo.setBrand("Toyota");
        carTwo.setType("Yaris");
        carTwo.setLicenseplatenumber("11-BV-BV");

        cars.add(carOne);
        cars.add(carTwo);
    }

    @Test
    void getAllCars() {

        when(carService.getAllCars()).thenReturn(cars);

        carController.getAllCars();

        verify(carService, times(1)).getAllCars();
    }

    @Test
    void getOneCarById() {
        when(carService.getOneCarById(1L)).thenReturn(carOne);

        carController.getOneCarById(1L);

        verify(carService, times(1)).getOneCarById(1L);
    }

    @Test
    void getOneCarByLicenseplateNumber() {

        when(carService.getCarByLicenseplateNumber("31-LZ-XL")).thenReturn(carOne);

        carController.getOneCarByLicenseplateNumber("31-LZ-XL");

        verify(carService, times(1)).getCarByLicenseplateNumber("31-LZ-XL");
    }

    @Test
    void deleteCarByLicenseplateNumber() {

        carController.deleteCarByLicenseplateNumber("31-LZ-XL");
        verify(carService, times(1)).deleteCarByLicenseplateNumber("31-LZ-XL");
    }

}