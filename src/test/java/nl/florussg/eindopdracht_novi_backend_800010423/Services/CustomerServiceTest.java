package nl.florussg.eindopdracht_novi_backend_800010423.Services;

import nl.florussg.eindopdracht_novi_backend_800010423.Dto.CustomerDto;
import nl.florussg.eindopdracht_novi_backend_800010423.EindopdrachtNoviBackend800010423Application;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.Customer;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
//@SpringBootTest
//@ContextConfiguration(classes={EindopdrachtNoviBackend800010423Application.class})
//@EnableConfigurationProperties
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    private Customer oneCustomer;
    private Customer twoCustomer;
    private List<Customer> customers;

    private CustomerDto customerDto;

    @BeforeEach
    public void setUp() {
        oneCustomer.setFirstname("Piet");
        oneCustomer.setLastname("Janssen");
        oneCustomer.setBsnnumber(123456789);
        oneCustomer.setPhonenumber(0611111111);

        twoCustomer.setFirstname("Jan");
        twoCustomer.setLastname("Derksen");
        twoCustomer.setBsnnumber(125689234);
        twoCustomer.setPhonenumber(0622222222);

        customerDto.setFirstname("Piet");
        customerDto.setLastname("Janssen");
        customerDto.setBsnnumber(123456789);
        customerDto.setPhonenumber(0611111111);

        customers.add(oneCustomer);
        customers.add(twoCustomer);

    }



    @Test
    public void getAllCustomers() {
        when(customerRepository.findAll()).thenReturn(customers);

        customerService.getAllCustomers("");

        verify(customerRepository,times(1)).findAll();

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