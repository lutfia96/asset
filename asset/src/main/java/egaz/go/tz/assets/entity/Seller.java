package egaz.go.tz.assets.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "seller_id")
    private  Long Id;
    private String name;
    @Column(name = "phone")
    private String phone;
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email",  unique = true)
  private   String email;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "seller")
    private List<Asset> assets = new ArrayList<>();
    @JsonBackReference

    public List<Asset> getAssets() {
        return assets;
    }
    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

}
