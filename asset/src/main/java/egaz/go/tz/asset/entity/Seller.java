package egaz.go.tz.asset.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long seller_id;
    String name;
    @Column(name = "phone", nullable = false)
    String phone;

    @Column(name = "email", nullable = false, unique = true)
    String email;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "seller")
    private List<Asset> assets;
}
