package egaz.go.tz.asset.service;

import egaz.go.tz.asset.entity.Customer;

import java.util.Collection;
import java.util.Optional;

public interface CustomerService {
    Customer registerCustomer(Customer customer);
    Collection<Customer> listCustomers();
    Customer getCustomer(String email);
    Customer updateCustomer(Customer Customer);
   Optional<Customer> findCustomer(String email);
   Customer deleteCustomer(String email);

}
