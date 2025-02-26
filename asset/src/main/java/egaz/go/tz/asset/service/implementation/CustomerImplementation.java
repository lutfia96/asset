package egaz.go.tz.asset.service.implementation;

import egaz.go.tz.asset.entity.Customer;
import egaz.go.tz.asset.repository.CustomerRepo;
import egaz.go.tz.asset.service.CustomerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @Override
    public Customer registerCustomer(Customer customer) {
        Customer customer1 = customerRepo.save(customer) ;
customer1.setEmail(customer.getEmail());
customer1.setPassword(customer.getPassword());
customer1.setLastname(customer.getLastname());
customer1.setFirstname(customer.getFirstname());
customer1.setAddress(customer.getAddress());
customer1.setPhone(customer.getPhone());
        return customerRepo.save(customer);
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
    public Customer updateCustomer(Customer Customer) {
        return null;
    }

    @Override
    public Optional<Customer> findCustomer(String email) {
        return customerRepo.findCustomerByEmail(email);
    }

    @Override
    public Customer deleteCustomer(String email) {
        return null;
    }
}
