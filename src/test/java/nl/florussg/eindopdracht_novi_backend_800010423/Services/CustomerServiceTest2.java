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
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ContextConfiguration(classes={EindopdrachtNoviBackend800010423Application.class})
@EnableConfigurationProperties
@AutoConfigureMockMvc
public class CustomerServiceTest2 {

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

        Customer customer;
    }



    @Test
    public void getOneCustomerById() {

        when(customerRepository.existsById(1L)).thenReturn(true);
        Mockito
                .doReturn(Optional.of(customerOne)).when(customerRepository).findById(1L);

        Customer customerFound = customerService.getOneCustomerById(1L);

        assertThat(customerFound).isEqualTo(customerOne);
    }
    
    @Test
    public void getCustomerByBsnnumber() {
        when(customerRepository.findCustomerByBsnnumber(customerOne.getBsnnumber())).thenReturn(Optional.ofNullable(customerOne));


    }







}
