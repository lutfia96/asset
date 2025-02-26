package egaz.go.tz.asset.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import egaz.go.tz.asset.constants.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.support.BeanDefinitionDsl;

import java.util.Collection;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data


public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long customer_id;
    String firstname;
    String lastname;
    @Column(name="address", nullable=false)
    String address;
    @Column(name="phone", nullable=false)
    String phone;
    @Column(name ="email",nullable=false,unique = true)
    String email;
    @Column(name="password", nullable=false,unique = true)
    String password;
private UserRole role;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "customer")
@EqualsAndHashCode.Exclude
    @lombok.ToString.Exclude
    @JsonIgnore
    private List<BookingAsset> bookingAssets;
    @OneToMany(mappedBy = "customer")
    private Collection<BookingAsset> bookingAsset;

}
