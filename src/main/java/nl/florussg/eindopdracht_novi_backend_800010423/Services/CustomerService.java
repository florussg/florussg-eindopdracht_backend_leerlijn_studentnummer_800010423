package nl.florussg.eindopdracht_novi_backend_800010423.Services;

import nl.florussg.eindopdracht_novi_backend_800010423.Dto.CustomerDto;
import nl.florussg.eindopdracht_novi_backend_800010423.Exceptions.BadRequestException;
import nl.florussg.eindopdracht_novi_backend_800010423.Exceptions.RecordNotFoundException;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.Customer;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
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
          //Optional<Customer> customer = Optional.ofNullable(customerRepository.findCustomerByBsnnumber(bsnnumber));
          if (optionalCustomer.isPresent()) {
              return optionalCustomer.get();
          } else {
              throw new RecordNotFoundException("Customer with this bsnnumber is not found");
          }
      }

    public Customer addCustomer (CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setFirstname(customerDto.getFirstname());
        customer.setLastname(customerDto.getLastname());
        customer.setBsnnumber(customerDto.getBsnnumber());
        customer.setPhonenumber(customerDto.getPhonenumber());

        return customerRepository.save(customer);
    }


}
