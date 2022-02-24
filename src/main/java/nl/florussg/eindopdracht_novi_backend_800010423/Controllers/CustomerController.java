package nl.florussg.eindopdracht_novi_backend_800010423.Controllers;

import nl.florussg.eindopdracht_novi_backend_800010423.Dto.CustomerDto;
import nl.florussg.eindopdracht_novi_backend_800010423.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    //Get all customers with an option to filter on the lastname
    @GetMapping (value = "/customers")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getAllCustomers(@RequestParam(name="lastname", defaultValue = "") String lastname) {
        return ResponseEntity.ok(customerService.getAllCustomers(lastname));
    }

    //Get one customer
    @GetMapping (value = "/customers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getOneCustomerById(@PathVariable long id) {
        return ResponseEntity.ok(customerService.getOneCustomerById(id));
    }

    //Get customer by BSN
    @GetMapping (value = "/customers/bsn")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getCustomerByBsnnumber(@RequestParam(name="number", defaultValue = "") int bsnnumber) {
        return ResponseEntity.ok(customerService.getCustomerByBsnnumber(bsnnumber));
    }

    //Add customer
    @PostMapping(value = "/customers/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> addCustomer(@Valid @RequestBody CustomerDto customerDto) {

        long newId = customerService.addNewCustomer(customerDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();
        return ResponseEntity.created(location).build();
    }

    //Delete one customer
    @DeleteMapping(value = "/customers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteCustomer(@PathVariable long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    //Total change of one customer
    @PutMapping(value = "/customers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> editCustomer(@PathVariable long id, @RequestBody CustomerDto customerDto) {
        customerService.editCustomer(id, customerDto);
        return ResponseEntity.noContent().build();
    }

    //partial change of one customer
    @PatchMapping(value = "/customers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> partialEditCustomer(@PathVariable long id, @RequestBody CustomerDto customerDto) {
        customerService.partialEditCustomer(id, customerDto);
        return ResponseEntity.noContent().build();
    }
}
