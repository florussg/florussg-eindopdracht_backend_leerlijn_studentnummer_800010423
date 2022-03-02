package nl.florussg.eindopdracht_novi_backend_800010423.Services;

import nl.florussg.eindopdracht_novi_backend_800010423.Dto.CarDto;
import nl.florussg.eindopdracht_novi_backend_800010423.Dto.CustomerDto;
import nl.florussg.eindopdracht_novi_backend_800010423.Exceptions.BadRequestException;
import nl.florussg.eindopdracht_novi_backend_800010423.Exceptions.RecordNotFoundException;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.Appointment;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.Car;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.Customer;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.AppointmentRepository;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.CarRepository;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    public Iterable<Customer> getAllCustomers(String lastname) {
        if (lastname.isEmpty()) {
            List<Customer> all = customerRepository.findAll();
            return all;
        } else {
            Iterable<Customer> allByLastnameContainingIgnoreCase = customerRepository.findAllByLastnameContainingIgnoreCase(lastname);
            return allByLastnameContainingIgnoreCase;
        }
    }

    public Customer getOneCustomerById (long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isPresent()) {
            return customer.get();
        } else {
            throw new RecordNotFoundException("Customer with this id is not found");
        }
      }

      public Customer getCustomerByBsnnumber (int bsnnumber) {
          Optional<Customer> optionalCustomer = customerRepository.findCustomerByBsnnumber(bsnnumber);
          if (optionalCustomer.isPresent()) {
              return optionalCustomer.get();
          } else {
              throw new RecordNotFoundException("Customer with this bsnnumber is not found");
          }
      }

    public long addNewCustomer(CustomerDto customerDto) {

        if(checkIfCustomerExistsInDatabaseBasedOnBsnnumber(customerDto) == true) {
            throw new BadRequestException("Customer already exists based on input-bsnnumber");
        } else {
            Customer newCustomer = new Customer();
            newCustomer.setFirstname(customerDto.getFirstname());
            newCustomer.setLastname(customerDto.getLastname());
            newCustomer.setBsnnumber(customerDto.getBsnnumber());
            newCustomer.setPhonenumber(customerDto.getPhonenumber());

            Customer saveCustomer = customerRepository.save(newCustomer);
            return saveCustomer.getId();
        }
    }

    public void deleteCustomer(long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if(optionalCustomer.isPresent()) {
            customerRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("A customer with this id does not exist");
        }
    }

    public Customer editCustomer (long id, CustomerDto customerDto) {
        Optional <Customer> optionalCustomer = customerRepository.findById(id);

        if(optionalCustomer.isPresent()) {
            Customer customerToEdit = optionalCustomer.get();

            customerToEdit.setFirstname(customerDto.getFirstname());
            customerToEdit.setLastname(customerDto.getLastname());
            if(checkIfCustomerExistsInDatabaseBasedOnBsnnumber(customerDto) == true) {
                throw new BadRequestException("There is already a customer with the same bsnnumber");
            } else {
                customerToEdit.setBsnnumber(customerDto.getBsnnumber());
            }
            customerToEdit.setPhonenumber(customerDto.getPhonenumber());

            customerRepository.save(customerToEdit);

            return customerToEdit;

        } else {
            throw new RecordNotFoundException("A customer with this id does not exist");
        }

    }

    public void partialEditCustomer (long id, CustomerDto customerDto) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isPresent()) {
            Customer customerToEdit = customerRepository.findById(id).orElse(null);

            if (customerDto.getFirstname() != null && !customerDto.getFirstname().isEmpty()) {
                customerToEdit.setFirstname(customerDto.getFirstname());
            }
            if (customerDto.getLastname() != null && !customerDto.getLastname().isEmpty()) {
                customerToEdit.setLastname(customerDto.getLastname());
            }
            if (String.valueOf(customerDto.getBsnnumber()) != null && !String.valueOf(customerDto.getBsnnumber()).isEmpty()) {
                customerToEdit.setBsnnumber(customerDto.getBsnnumber());
            }
            if (String.valueOf(customerDto.getPhonenumber()) != null && !String.valueOf(customerDto.getPhonenumber()).isEmpty()) {
                customerToEdit.setPhonenumber(customerDto.getPhonenumber());
            }

            customerRepository.save(customerToEdit);

        } else {
            throw new RecordNotFoundException("A customer with this id does not exist");
        }
    }

    public void addNewCarToCustomer(long id, Car car) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            //List<Car> carsFromCustomer = customer.getOwnedCars();


            car.setCarCustomer(customer);

            carRepository.save(car);
            customer.addOwnedCars(car);

            customerRepository.save(customer);

        } else {
            throw new RecordNotFoundException("A customer with this id does not exist");
        }
    }

        //methods
        public boolean checkIfCustomerExistsInDatabaseBasedOnBsnnumber (CustomerDto customerDto) {
            int bsnnumberInput = customerDto.getBsnnumber();
            Optional<Customer> optionalCustomer = customerRepository.findCustomerByBsnnumber(bsnnumberInput);
            if (optionalCustomer.isPresent()) {
                return true;
            } else {
                return false;
            }
        }
}
