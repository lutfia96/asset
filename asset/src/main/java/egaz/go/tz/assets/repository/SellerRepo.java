package egaz.go.tz.assets.repository;

import egaz.go.tz.assets.dto.SellerDTO;
import egaz.go.tz.assets.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SellerRepo extends JpaRepository<Seller, Long> {
    //Optional<Seller> findSellerByEmail(String email);
    //Optional<Seller> findSellerById(Long id);
    Optional<Seller>findById(Long sellerId);

    Optional<Seller> findByEmail(String email);


    //List<SellerDTO> findAll(SellerDTO sellerDTO);
}
