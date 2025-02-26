package egaz.go.tz.asset.controller;

import egaz.go.tz.asset.entity.Customer;
import egaz.go.tz.asset.repository.CustomerRepo;
import egaz.go.tz.asset.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerRepo customerRepo;
    @PostMapping(value= "/register", consumes = "application/json")
    public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer) {
        Customer customer1 = customerRepo.save(customer);
        return ResponseEntity.ok(customer1);
    }
    @PostMapping("/login/{email}/{password}")
    public ResponseEntity<Customer> findCustomer(@RequestBody Customer customer, @PathVariable String email,@PathVariable String password) {
        Optional<Customer> customer1 = customerRepo.findCustomerByEmail(email);
        if (customer1.isPresent()){
            return ResponseEntity.ok(customer1.get());
        } else return ResponseEntity.notFound().build();
    }


    }
