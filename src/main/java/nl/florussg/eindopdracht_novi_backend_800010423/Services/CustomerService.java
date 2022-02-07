package nl.florussg.eindopdracht_novi_backend_800010423.Services;

import nl.florussg.eindopdracht_novi_backend_800010423.Models.Customer;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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




}
