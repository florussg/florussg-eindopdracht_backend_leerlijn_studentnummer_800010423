package nl.florussg.eindopdracht_novi_backend_800010423.Services;

import nl.florussg.eindopdracht_novi_backend_800010423.Dto.CustomerDto;
import nl.florussg.eindopdracht_novi_backend_800010423.Exceptions.RecordNotFoundException;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.Customer;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
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
        when(customerRepository.findAllByLastnameContainingIgnoreCase("Janssen")).thenReturn(customers);

        //act -uitvoeren wat er getest wordt
        Iterable<Customer> foundCustomers = customerService.getAllCustomers("Janssen");

        //assert -vergelijking
        verify(customerRepository,times(1)).findAllByLastnameContainingIgnoreCase("Janssen");
        //assertThat(foundCustomers).isEqualTo(customers);
        assertEquals(customers,foundCustomers);
    }

    @Test
    void getOneCustomerById() {

        when(customerRepository.findById(42L)).thenReturn(Optional.of(customerOne));

        Customer oneCustomerById = customerService.getOneCustomerById(42L);
        verify(customerRepository,times(1)).findById(42L);

        assertNotNull(oneCustomerById);
    }

    @Test
    void getOneCustomerByIdException() {
        assertThrows(RecordNotFoundException.class, () -> customerService.getOneCustomerById(1L));
    }

    @Test
    void getCustomerByBsnnumber() {

        //arrange - gedrag in programmeren.
        when(customerRepository.findCustomerByBsnnumber(123456789)).thenReturn(Optional.of(customerOne));

        //act - uitvoeren wat er getest wordt.
        Customer oneCustomerByBsnnunmber = customerService.getCustomerByBsnnumber(123456789);
        verify(customerRepository, times(1)).findCustomerByBsnnumber(123456789);

        //assert -vergelijking.
        assertNotNull(oneCustomerByBsnnunmber);
    }

    @Test
    void getCustomerByBsnnumberException() {
        assertThrows(RecordNotFoundException.class, () -> customerService.getCustomerByBsnnumber(123456789));
    }

    @Test
    void addCustomer() {

        //arrange -gedrag in programmeren.
        when(customerRepository.save(ArgumentMatchers.any(Customer.class))).thenReturn(customerOne);

        //act - uitvoeren wat er getest wordt.
        Customer customerCreated = customerRepository.save(customerOne);
        long customerCreatedId = customerService.addNewCustomer(customerDto);
        verify(customerRepository).save(customerOne);

        //assert - vergelijking.
        assertThat(customerCreatedId).isSameAs(customerCreated.getId());
    }

    @Test
    void addCustomerException() {
    }

    @Test
    void deleteCustomer() {

        //arange - gedrag in programmeren.
        when(customerRepository.findById(customerOne.getId())).thenReturn(Optional.of(customerOne));

        //act - uitvoeren wat er getest wordt.
        customerService.deleteCustomer(customerOne.getId());
        verify(customerRepository).deleteById(customerOne.getId());
    }

    @Test
    void deleteCustomerException() {
        assertThrows(RecordNotFoundException.class, () -> customerService.deleteCustomer(1000L));
    }

    @Test
    void editCustomer() {

        when(customerRepository.findById(1L)).thenReturn(Optional.ofNullable(customerOne));
        when(customerRepository.save(customerOne)).thenReturn(customerOne);

        Customer customerToEdit = customerService.editCustomer(1L, customerDto);

        verify(customerRepository, times(1)).findById(customerOne.getId());
        verify(customerRepository, times(1)).save(customerOne);

        assertThat(customerToEdit.getId()).isEqualTo(customerOne.getId());
    }

    @Test
    void editCustomerException() {
        assertThrows(RecordNotFoundException.class, () -> customerService.editCustomer(1000L, customerDto));
    }

    @Test
    void partialEditCustomer() {
    }

    @Test
    void checkIfCustomerExistsInDatabaseBasedOnBsnnumber() {
        when(customerRepository.findCustomerByBsnnumber(123456789)).thenReturn(Optional.ofNullable(customerOne));

        Boolean customerToCheck = customerService.checkIfCustomerExistsInDatabaseBasedOnBsnnumber(customerDto);

        assertThat(customerToCheck).isTrue();
    }
}