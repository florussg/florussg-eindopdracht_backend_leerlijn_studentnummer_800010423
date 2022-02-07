package nl.florussg.eindopdracht_novi_backend_800010423.Controllers;

import nl.florussg.eindopdracht_novi_backend_800010423.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    //Get all Customers with an option to filter on the lastname
    @GetMapping (value = "/Customers")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getAllCustomers(@RequestParam(name="lastname", defaultValue = "") String lastname) {
        return ResponseEntity.ok(customerService.getAllCustomers(lastname));

    }


}
