package egaz.go.tz.assets.controller;

import egaz.go.tz.assets.dto.CustomerDTO;
import egaz.go.tz.assets.entity.Customer;
import egaz.go.tz.assets.repository.CustomerRepo;
import egaz.go.tz.assets.service.CustomerService;
import egaz.go.tz.assets.service.implementation.CustomerImplementation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    @Autowired
    CustomerImplementation customerService;
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    ModelMapper modelMapper;
    @PostMapping(value= "/register", consumes = "application/json")
    public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer) {
        Customer customer1 = customerService.registerCustomer(customer);
        return ResponseEntity.ok(customer1);
    }
    @PostMapping("/login/{email}/{password}")
    public ResponseEntity<Customer> findCustomer(@RequestBody Customer customer, @PathVariable String email,@PathVariable String password) {
        Optional<Customer> customer1 = customerRepo.findByEmail(email);
        if (customer1.isPresent()){
            return ResponseEntity.ok(customer1.get());
        } else return ResponseEntity.notFound().build();
    }
@PutMapping("/updating/{email}")
    public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody CustomerDTO customer, @PathVariable String email) {
        Optional<CustomerDTO> customer1= customerService.updateCustomer(email,customer);
        if (customer1.isPresent()){
            return ResponseEntity.ok(customer1.get());
        }
        else return ResponseEntity.notFound().build();

}

    }
