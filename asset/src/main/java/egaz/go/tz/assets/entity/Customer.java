package egaz.go.tz.assets.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import egaz.go.tz.assets.constants.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data


public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long customer_id;
    String firstname;
    String lastname;
    @Column(name="address", nullable=false)
    String address;
    @Column(name="phone", nullable=false)
    String phone;
    @Column(name ="email",nullable=false,unique = true)
    @Email
    String email;
    @Column(name="password", nullable=false,unique = true)
    String password;
    @Enumerated(EnumType.STRING)
private UserRole role;
    @Version
    private Long version;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "customer")
@EqualsAndHashCode.Exclude
    @lombok.ToString.Exclude
    @JsonIgnore
    private List<BookingAsset> bookingAssets;


}
