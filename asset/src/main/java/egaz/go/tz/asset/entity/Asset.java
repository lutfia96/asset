package egaz.go.tz.asset.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import egaz.go.tz.asset.constants.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int id;
    @Column(name="is_active")
    private   boolean active;
    private String name;
@Column(unique=true,nullable = false)
@NotEmpty(message = "this barcode is unique and not empty")
    private String barcode;
    private double price;
    @Enumerated(EnumType.STRING)  // Ensure it's stored as a string
    @Column(name = "status")
    private Status status ;

    @OneToOne(optional = false)
    @JoinColumn(name = "seller_id", referencedColumnName = "seller_id")
    private Seller seller;

    @OneToOne(optional = false)
    @JoinColumn(name = "bookingId", referencedColumnName = "bookingId")
    private BookingAsset bookingAsset;




}
