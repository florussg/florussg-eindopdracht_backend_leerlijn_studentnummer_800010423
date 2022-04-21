package nl.florussg.eindopdracht_novi_backend_800010423.Services;

import nl.florussg.eindopdracht_novi_backend_800010423.Dto.CarDto;
import nl.florussg.eindopdracht_novi_backend_800010423.Exceptions.BadRequestException;
import nl.florussg.eindopdracht_novi_backend_800010423.Exceptions.RecordNotFoundException;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.Car;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.CarRepository;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public Iterable<Car> getAllCars() {
        List<Car> all = carRepository.findAll();
        return all;
    }

    public Car getOneCarById(long id) {
        Optional<Car> car = carRepository.findById(id);
        if (car.isPresent()) {
            return car.get();
        } else {
            throw new RecordNotFoundException("A car with this id is not found");
        }
    }

    public Car getCarByLicenseplateNumber(String licenseplateNumber) {
        Optional<Car> optionalCar = carRepository.findCarByLicenseplateNumberContainingIgnoreCase(licenseplateNumber);
        if (optionalCar.isPresent()) {
            return optionalCar.get();
        } else {
            throw new RecordNotFoundException("A car with this licenseplate number is not found");
        }
    }

    public long addNewCar(CarDto carDto) {
        if (checkIfCarExistsInDatabaseBasedOnLicenseplateNumber(carDto) == true) {
            throw new BadRequestException("Car already exists based on input-licenseplatenumber");
        } else {
            Car newCar = new Car();
            newCar.setBrand(carDto.getBrand());
            newCar.setType(carDto.getType());
            newCar.setLicenseplatenumber(carDto.getLicenseplateNumber());

            Car saveCar = carRepository.save(newCar);
            return saveCar.getId();
        }
    }

    public void deleteCarByLicenseplateNumber(String licenseplateNumber) {
        Optional<Car> optionalCar = carRepository.findCarByLicenseplateNumberContainingIgnoreCase(licenseplateNumber);

        if (optionalCar.isPresent()) {
            carRepository.deleteById(optionalCar.get().getId());
        } else {
            throw new RecordNotFoundException("A car with this licenseplate number does not exist");
        }
    }

    public Car editCar (String licenseplateNumber, CarDto carDto) {
        Optional<Car> optionalCar = carRepository.findCarByLicenseplateNumberContainingIgnoreCase(licenseplateNumber);

        if (optionalCar.isPresent()) {
            Car carToEdit = optionalCar.get();

            if (checkIfCarExistsInDatabaseBasedOnLicenseplateNumber(carDto) == true) {
                throw new BadRequestException("Car already exists based on input-licenseplate number");
            } else {
                carToEdit.setLicenseplatenumber(carDto.getLicenseplateNumber());
            }
            carToEdit.setBrand(carDto.getBrand());
            carToEdit.setType(carDto.getType());

            carRepository.save(carToEdit);
            return carToEdit;
        } else {
            throw new RecordNotFoundException("A car with this licenseplate number does not exist");
        }
    }

    public void partialEditCar (String licenseplateNumber, CarDto carDto ) {
        Optional<Car> optionalCar = carRepository.findCarByLicenseplateNumberContainingIgnoreCase(licenseplateNumber);

        if (optionalCar.isPresent()) {
            Car carToEdit = optionalCar.get();

            if (carDto.getLicenseplateNumber() != null && !carDto.getLicenseplateNumber().isEmpty()) {
                if (checkIfCarExistsInDatabaseBasedOnLicenseplateNumber(carDto) == true) {
                    throw new BadRequestException("Car already exists based on input-licenseplate number");
                } else {
                    carToEdit.setLicenseplatenumber(carDto.getLicenseplateNumber());
                }
            }

            if (carDto.getBrand() != null && !carDto.getBrand().isEmpty()) {
                carToEdit.setBrand(carDto.getBrand());
            }
            if (carDto.getType() != null && !carDto.getType().isEmpty()) {
                carToEdit.setType(carDto.getType());
            }

        carRepository.save(carToEdit);

        } else {
            throw new RecordNotFoundException("A car with this licenseplate number does not exist");
        }
    }


    public boolean checkIfCarExistsInDatabaseBasedOnLicenseplateNumber(CarDto carDto) {
        String licenseplateNumberInput = carDto.getLicenseplateNumber();
        Optional<Car> optionalCar = carRepository.findCarByLicenseplateNumberContainingIgnoreCase(licenseplateNumberInput);
        if (optionalCar.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

}







