package nl.florussg.eindopdracht_novi_backend_800010423.Services;

import nl.florussg.eindopdracht_novi_backend_800010423.Dto.CustomerDto;
import nl.florussg.eindopdracht_novi_backend_800010423.Exceptions.BadRequestException;
import nl.florussg.eindopdracht_novi_backend_800010423.Exceptions.RecordNotFoundException;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.Customer;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Iterable<Customer> getAllCustomers(String lastname) {
        if (lastname.isEmpty()) {
            return customerRepository.findAll();
        } else {
            return customerRepository.findAllByLastnameContainingIgnoreCase(lastname);
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

    public long addCustomer (CustomerDto customerDto) {

        if(checkIfCustomerExistsInDatabase(customerDto) == true) {
            throw new BadRequestException("Customer already exists based on input-bsnnumber");
            }
        else {
            Customer newCustomer = new Customer();
            newCustomer.setFirstname(customerDto.getFirstname());
            newCustomer.setLastname(customerDto.getLastname());
            newCustomer.setBsnnumber(customerDto.getBsnnumber());
            newCustomer.setPhonenumber(customerDto.getPhonenumber());

            Customer saveCustomer = customerRepository.save(newCustomer);
            return saveCustomer.getId();
        }
    }

        public boolean checkIfCustomerExistsInDatabase (CustomerDto customerDto) {
            int bsnnumberInput = customerDto.getBsnnumber();
            Optional<Customer> optionalCustomer = customerRepository.findCustomerByBsnnumber(bsnnumberInput);
            if (optionalCustomer.isPresent()) {
                return true;
            } else {
                return false;
            }
        }



}
