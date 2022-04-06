package nl.florussg.eindopdracht_novi_backend_800010423.Controllers;

import nl.florussg.eindopdracht_novi_backend_800010423.Models.Customer;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.CustomerRepository;
import nl.florussg.eindopdracht_novi_backend_800010423.Services.CustomerService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
class CustomerControllerTest {

    @InjectMocks
    CustomerController customerController;

    @Mock
    CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    Customer customerOne = new Customer();
    Customer customerTwo = new Customer();

    List<Customer> customers = new ArrayList<>();

    @BeforeEach
    public void setUp() {

        customerOne.setId(2L);
        customerOne.setFirstname("Piet");
        customerOne.setLastname("Janssen");
        customerOne.setBsnnumber(111111111);
        customerOne.setPhonenumber(0612121212);

        customerTwo.setId(3L);
        customerTwo.setFirstname("Jan");
        customerTwo.setLastname("Derksen");
        customerTwo.setBsnnumber(222222222);
        customerTwo.setPhonenumber(0612121214);

        customers.add(customerOne);
        customers.add(customerTwo);

    }


    @Test
    void getAllCustomers() {
        when(customerService.getAllCustomers("Janssen")).thenReturn(customers);

        customerController.getAllCustomers("Janssen");

        verify(customerService, times(1)).getAllCustomers("Janssen");

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
}