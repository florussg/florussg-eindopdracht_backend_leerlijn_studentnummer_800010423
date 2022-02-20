package nl.florussg.eindopdracht_novi_backend_800010423.Services;

import nl.florussg.eindopdracht_novi_backend_800010423.Dto.CustomerDto;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.Customer;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private CustomerDto customerDto;



    @Test
    public void getAllCustomers() {

    }

    @Test
    void getOneCustomerById() {
    }

    @Test
    void getCustomerByBsnnumber() {
    }

    @Test
    void addCustomer() {
    }

    @Test
    void deleteCustomer() {
    }

    @Test
    void editCustomer() {
    }

    @Test
    void partialEditCustomer() {
    }

    @Test
    void checkIfCustomerExistsInDatabaseBasedOnBsnnumber() {
    }
}