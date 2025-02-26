package egaz.go.tz.asset.repository;

import egaz.go.tz.asset.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
  Optional<Customer> findCustomerByEmail(String email);
    Optional<Customer> deleteCustomerByEmail(String email);
    boolean existsCustomerByEmail(String email);
}
