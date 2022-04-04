package nl.florussg.eindopdracht_novi_backend_800010423.Services;

import nl.florussg.eindopdracht_novi_backend_800010423.Dto.CarDto;
import nl.florussg.eindopdracht_novi_backend_800010423.Exceptions.RecordNotFoundException;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.Car;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @InjectMocks
    private CarService carService;

    @Mock
    private CarRepository carRepository;

    Car carOne = new Car();
    Car carTwo = new Car();
    Car carThree = new Car();
    List<Car> cars = new ArrayList<>();
    CarDto carDto = new CarDto();

    @BeforeEach
    public void setUp() {
        carOne.setId(1);
        carOne.setBrand("Seat");
        carOne.setType("Ibiza");
        carOne.setLicenseplatenumber("GL-33-NN");
        //carOne.setCarRegistrationDocument("Florus.pdf");

        carTwo.setId(2);
        carTwo.setBrand("Toyota");
        carTwo.setType("Yaris");
        carTwo.setLicenseplatenumber("GI-NG-34");
        //carTwo.setCarRegistrationDocument("Piet.pdf");

        carThree.setId(3);
        carThree.setBrand("Mazda");
        carThree.setType("CX5");
        carThree.setLicenseplatenumber("RI-CH-11");
        //carThree.setCarRegistrationDocument("Rich.pdf");

        carDto.setId(4);
        carDto.setBrand("Ford");
        carDto.setType("Ka");
        carDto.setLicenseplatenumber("TR-UU-55");
        //carDto.setFileNameCarRegistrationDocument("Truus.pdf");

        cars.add(carOne);
        cars.add(carTwo);
        cars.add(carThree);
    }

    @Test
    public void getAllCars() {

        when(carRepository.findAll()).thenReturn(cars);

        Iterable<Car> foundCars = carService.getAllCars();

        verify(carRepository,times(1)).findAll();

        assertThat(foundCars).isEqualTo(cars);

    }

    @Test
    void getOneCarById() {

        when(carRepository.findById(1L)).thenReturn(Optional.of(carOne));

        Car oneCarById = carService.getOneCarById(1L);
        verify(carRepository,times(1)).findById(1L);

        assertNotNull(oneCarById);
    }

    @Test
    void getOneCarByIdException() {
        assertThrows(RecordNotFoundException.class, () -> carService.getOneCarById(1L));
    }

    @Test
    void getCarByLicenseplateNumber() {
    }

    @Test
    void addNewCar() {
    }

    @Test
    void deleteCarByLicenseplateNumber() {
    }

    @Test
    void editCar() {
    }

    @Test
    void partialEditCar() {
    }

    @Test
    void checkIfCarExistsInDatabaseBasedOnLicenseplateNumber() {
    }
}
