package nl.florussg.eindopdracht_novi_backend_800010423.Services;

import nl.florussg.eindopdracht_novi_backend_800010423.Dto.CustomerDto;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.Customer;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.CustomerRepository;
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
//@SpringBootTest
//@ContextConfiguration(classes={EindopdrachtNoviBackend800010423Application.class})
//@EnableConfigurationProperties
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    Customer customerOne = new Customer();
    Customer customerTwo = new Customer();
    Customer customerThree = new Customer();
    List<Customer> customers = new ArrayList();
    CustomerDto customerDto = new CustomerDto();

    @BeforeEach
    public void setUp() {
        customerOne.setId(1);
        customerOne.setFirstname("Piet");
        customerOne.setLastname("Janssen");
        customerOne.setBsnnumber(123456789);
        customerOne.setPhonenumber(0611111111);

        customerTwo.setId(2);
        customerTwo.setFirstname("Jan");
        customerTwo.setLastname("Derksen");
        customerTwo.setBsnnumber(125689234);
        customerTwo.setPhonenumber(0622222222);

        customerThree.setId(3);
        customerThree.setFirstname("Truus");
        customerThree.setLastname("Janssen");
        customerThree.setBsnnumber(584515785);
        customerThree.setPhonenumber(0633333333);

        customerDto.setId(4);
        customerDto.setFirstname("Dirk");
        customerDto.setLastname("Schutter");
        customerDto.setBsnnumber(123456789);
        customerDto.setPhonenumber(0611111111);

        customers.add(customerOne);
        customers.add(customerTwo);
        customers.add(customerThree);
    }

    @Test
    public void getAllCustomersWithNoLastname() {
        when(customerRepository.findAll()).thenReturn(customers);

        Iterable<Customer> foundCustomers = customerService.getAllCustomers("");

        verify(customerRepository,times(1)).findAll();

        assertThat(foundCustomers).isEqualTo(customers);
        }

    @Test
    public void getAllCustomersWithLastname() {
        //arrange -gedrag in programmeren
        when(customerRepository.findAllByLastnameContainingIgnoreCase("glenn")).thenReturn(customers);

        //act -uitvoeren wat er getest wordt
        Iterable<Customer> foundCustomers = customerService.getAllCustomers("Janssen");

        //assert -vergelijking
        verify(customerRepository,times(1)).findAllByLastnameContainingIgnoreCase("Janssen");
        //assertThat(foundCustomers).isEqualTo(customers);
        assertEquals(customers,foundCustomers);
    }

//    @Test
//    void getOneCustomerById() {
//        when(customerRepository.findById(.getId())).thenReturn(Optional.ofNullable());
//
//        Optional<Customer> optionalCustomer = Optional.ofNullable(customerService.getOneCustomerById(1L));
//
//        verify(customerRepository,times(1)).findById(1L);
//        assertEquals(,optionalCustomer);
//    }

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