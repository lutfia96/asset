package egaz.go.tz.asset.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import egaz.go.tz.asset.constants.BookingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

public class BookingAsset {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long bookingId;

    String bookingName;
    String description;
    private BookingStatus bookingStatus;



    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "bookingAsset")

    private Asset assets;

}
