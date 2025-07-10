package egaz.go.tz.assets.repository;

import egaz.go.tz.assets.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
  Optional<Customer> findByEmail(String email);
    Optional<Customer> deleteCustomerByEmail(String email);
    boolean existsCustomerByEmail(String email);
    Optional<Customer> findById (Long customerId);
}
