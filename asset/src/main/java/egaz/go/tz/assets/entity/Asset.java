package egaz.go.tz.assets.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import egaz.go.tz.assets.constants.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    @Column(name="is_active")
    private   boolean active;
    private String name;
@Column(unique=true,nullable = false)
@NotEmpty(message = "this barcode is unique and not empty")
    private String barcode;
    private double price;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status ;

@Version
private int version;

    @ManyToOne(optional = false)
    @JoinColumn(name = "seller_Id", referencedColumnName = "seller_id",nullable = false)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Seller seller;


    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }


    @OneToMany(mappedBy = "asset", cascade = CascadeType.ALL)
    private List<BookingAsset> bookingAsset;



}
