package egaz.go.tz.assets.entity;

import egaz.go.tz.assets.constants.BookingStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

public class BookingAsset {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long bookingId;

    String bookingName;
    String description;
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

@Version
private Long version;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Asset asset;


}
