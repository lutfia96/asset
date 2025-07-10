package egaz.go.tz.assets.service.implementation;

import egaz.go.tz.assets.dto.CustomerDTO;
import egaz.go.tz.assets.dto.CustomerResponseDTO;
import egaz.go.tz.assets.entity.Customer;
import egaz.go.tz.assets.repository.AssetRepo;
import egaz.go.tz.assets.repository.CustomerRepo;
import egaz.go.tz.assets.service.CustomerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class CustomerImplementation implements CustomerService {
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private AssetRepo assetRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Customer registerCustomer(Customer customer) {
        if (customer.getCustomer_id() == null) {
            return customerRepo.save(customer);

        } else {
           Customer customer1;
                   customer1= customerRepo.findById(customer.getCustomer_id()).orElseThrow(() ->
                           new RuntimeException("Customer with id " + customer.getEmail() + " already exists"));
            customer1.setEmail(customer.getEmail());
            customer1.setPassword(customer.getPhone());
            customer1.setLastname(customer.getLastname());
            customer1.setFirstname(customer.getFirstname());
            customer1.setAddress(customer.getAddress());
            customer1.setPhone(customer.getPhone());
            customer1.setRole(customer.getRole());


            return customerRepo.save(customer1);
        }
    }

    @Override
    public Collection<Customer> listCustomers() {
        return List.of();
    }

    @Override
    public Customer getCustomer(String email) {
        return null;
    }

    @Override
    public Optional<CustomerDTO> updateCustomer(String email,CustomerDTO customer) {
        Optional<Customer> customer1 = customerRepo.findByEmail(email);
        if (customer1.isPresent()) {
             Customer customer2 = customer1.get();
            customer2.setFirstname(customer.getFirstName());
            customer2.setLastname(customer.getLastName());
            customer2.setPhone(customer.getPhone());
            customer2.setAddress(customer.getAddress());
            Customer  customer3 = customerRepo.save(customer2);
            return Optional.of(modelMapper.map(customer3,CustomerDTO.class));
        } else {
            log.info("Customer with email " + email + " does not exist");
return Optional.empty();
        }

    }

    @Override
    public Optional<Customer> findCustomer(String email) {
        return null;
    }

    @Override
    public Customer deleteCustomer(String email) {
        return null;
    }
}
