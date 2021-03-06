package nl.florussg.eindopdracht_novi_backend_800010423.Services;

import nl.florussg.eindopdracht_novi_backend_800010423.Dto.CustomerDto;
import nl.florussg.eindopdracht_novi_backend_800010423.Exceptions.BadRequestException;
import nl.florussg.eindopdracht_novi_backend_800010423.Exceptions.RecordNotFoundException;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.Car;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.Customer;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.CarRepository;
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

    @Mock
    private CarRepository carRepository;

    Customer customerOne = new Customer();
    Customer customerTwo = new Customer();
    Customer customerThree = new Customer();
    List<Customer> customers = new ArrayList();
    CustomerDto customerDto = new CustomerDto();
    CustomerDto customerDtoTwo = new CustomerDto();
    Car car = new Car();

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

        customerDtoTwo.setLastname("Schuiten");
        customerDtoTwo.setFirstname("Dirk-Jan");
        customerDto.setBsnnumber(125689234);
        customerDtoTwo.setPhonenumber(0611111211);

        car.setId(1L);
        car.setBrand("Tesla");
        car.setType("Model S");
        car.setLicenseplatenumber("EL-EC-44");

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
        when(customerRepository.findAllByLastnameContainingIgnoreCase("Janssen")).thenReturn(customers);

        Iterable<Customer> foundCustomers = customerService.getAllCustomers("Janssen");

        verify(customerRepository,times(1)).findAllByLastnameContainingIgnoreCase("Janssen");

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
        when(customerRepository.findCustomerByBsnnumber(123456789)).thenReturn(Optional.of(customerOne));

        Customer oneCustomerByBsnnunmber = customerService.getCustomerByBsnnumber(123456789);
        verify(customerRepository, times(1)).findCustomerByBsnnumber(123456789);

        assertNotNull(oneCustomerByBsnnunmber);
    }

    @Test
    void getCustomerByBsnnumberException() {
        assertThrows(RecordNotFoundException.class, () -> customerService.getCustomerByBsnnumber(123456789));
    }

    @Test
    void addCustomer() {
        when(customerRepository.save(ArgumentMatchers.any(Customer.class))).thenReturn(customerOne);

        Customer customerCreated = customerRepository.save(customerOne);
        long customerCreatedId = customerService.addNewCustomer(customerDto);
        verify(customerRepository).save(customerOne);

        assertThat(customerCreatedId).isSameAs(customerCreated.getId());
    }

    @Test
    void deleteCustomer() {
        when(customerRepository.findById(customerOne.getId())).thenReturn(Optional.of(customerOne));

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

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customerOne));
        when(customerRepository.save(customerOne)).thenReturn(customerOne);

        Customer customerToEdit = customerService.partialEditCustomer(1L, customerDtoTwo);

        verify(customerRepository, times(2)).findById(1L);
        verify(customerRepository, times(1)).save(customerOne);

        assertThat(customerToEdit).isEqualTo(customerOne);
    }

    @Test
    void partialEditCustomerException() {

        when(customerRepository.findById(1000L)).thenReturn(Optional.ofNullable(null));

        Exception exception = assertThrows(RecordNotFoundException.class, () -> {
            customerService.partialEditCustomer(1000L, customerDto);
        });

        String expectedMessage = "A customer with this id does not exist";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void addExistingCarToCustomer() {

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customerOne));
        when(carRepository.findCarByLicenseplateNumberContainingIgnoreCase("EL-EC-44")).thenReturn((Optional.of(car)));

        Customer carAdded = customerService.addExistingCarToCustomer(1L, "EL-EC-44" );

        verify(customerRepository, times(1)).findById(1L);

        assertThat(carAdded.getId()).isEqualTo(customerOne.getId());
    }

    @Test
    void addExistingCarToCustomerException() {

        when(customerRepository.findById(1111L)).thenReturn(Optional.ofNullable(null));
        when(carRepository.findCarByLicenseplateNumberContainingIgnoreCase("EL-EC-44")).thenReturn((Optional.of(car)));

        Exception exception = assertThrows(RecordNotFoundException.class, () -> {
            customerService.addExistingCarToCustomer(1111L, "EL-EC-44");
        });

        String expectedMessage = "Customer or Car does not exist!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void addNewCarToCustomerException() {
        assertThrows(RecordNotFoundException.class, () -> customerService.addNewCarToCustomer(1000L, car));
    }
}





