package egaz.go.tz.assets.service;

import egaz.go.tz.assets.dto.CustomerDTO;
import egaz.go.tz.assets.dto.CustomerResponseDTO;
import egaz.go.tz.assets.entity.Customer;

import java.util.Collection;
import java.util.Optional;

public interface CustomerService {
    Customer registerCustomer(Customer customer);
    Collection<Customer> listCustomers();
    Customer getCustomer(String email);
    Optional<CustomerDTO> updateCustomer(String email, CustomerDTO customer);
   Optional<Customer> findCustomer(String email);
   Customer deleteCustomer(String email);

}
