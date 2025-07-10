package egaz.go.tz.assets.service;

import egaz.go.tz.assets.dto.LoginRequest;
import egaz.go.tz.assets.dto.SellerDTO;
import egaz.go.tz.assets.dto.SellerDtoRequest;
import egaz.go.tz.assets.entity.Seller;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface SellerService extends UserDetailsService {
    Seller register(SellerDtoRequest seller);
   Optional<Seller> findByEmail(String email);
   Optional<Seller> findById(Long sellerId);
  SellerDTO  getSellerById(Long sellerId);


}