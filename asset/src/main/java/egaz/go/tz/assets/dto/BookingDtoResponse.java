package egaz.go.tz.assets.dto;

import egaz.go.tz.assets.entity.Asset;
import egaz.go.tz.assets.entity.Customer;
import lombok.Data;

import java.util.List;

@Data
public class BookingDtoResponse {
    private String bookingName;
    private String description;
    private String customerName;
    private String assetName;
    private String price;

}
