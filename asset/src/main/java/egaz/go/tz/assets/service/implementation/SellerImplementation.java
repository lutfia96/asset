package egaz.go.tz.assets.service.implementation;

import egaz.go.tz.assets.dto.SellerDTO;
import egaz.go.tz.assets.dto.SellerDtoRequest;
import egaz.go.tz.assets.entity.Seller;
import egaz.go.tz.assets.repository.SellerRepo;
import egaz.go.tz.assets.service.AssetService;
import egaz.go.tz.assets.service.SellerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class SellerImplementation implements SellerService {

    private final PasswordEncoder passwordEncoder   ;
    @Autowired
    private   AssetService assetService;
    @Autowired
   private  SellerRepo sellerRepo;

    private final ModelMapper modelMapper = new ModelMapper();


    @Autowired
    public SellerImplementation(@Lazy PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }




    @Override
    public Seller register(SellerDtoRequest seller) {
        Seller sellerEntity = new Seller();
        sellerEntity.setId(null);
        sellerEntity.setName(seller.getName());
        sellerEntity.setEmail(seller.getEmail());
        sellerEntity.setPhone(seller.getPhone());
        sellerEntity.setPassword(passwordEncoder.encode(seller.getPassword()));
        System.out.println(sellerEntity);
        if (sellerEntity.getPassword()==null){
            throw new RuntimeException("password cannot be null");}
        return sellerRepo.save(sellerEntity);
    }

    @Override
    public Optional<Seller> findByEmail(String email) {
        Seller seller = sellerRepo.findByEmail(email).orElse(null);
        System.out.println(seller);
        return Optional.ofNullable(seller);
    }

    @Override
    public Optional<Seller> findById(Long sellerId) {
        Seller seller = sellerRepo.findById(sellerId).orElse(null);
        System.out.println(seller);
        return Optional.ofNullable(seller);
    }

    @Override
    public SellerDTO getSellerById(Long sellerId) {
        Seller seller = sellerRepo.findById(sellerId).orElseThrow(()
                -> new UsernameNotFoundException("User not found"));
        seller.setId(sellerId);
        System.out.println("seller id "+sellerId);
        SellerDTO sellerDTO = modelMapper.map(seller, SellerDTO.class);
        System.out.println(sellerDTO);
        return sellerDTO;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       var user = sellerRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
        return new User(
                user.getEmail(),
                user.getPassword(),
                Collections.emptyList()

        );
    }

}
