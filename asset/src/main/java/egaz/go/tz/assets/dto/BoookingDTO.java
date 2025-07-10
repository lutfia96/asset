package egaz.go.tz.assets.dto;

import egaz.go.tz.assets.constants.BookingStatus;
import lombok.Data;

@Data
public class BoookingDTO {
    private Long bookingId;
    private String bookingName;
    private String description;
    private BookingStatus bookingStatus;
    private Long customerId;
    private Long assetId;

    public BoookingDTO() {

    }
}
