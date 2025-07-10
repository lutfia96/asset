package egaz.go.tz.assets.service;

import egaz.go.tz.assets.dto.BookingDtoResponse;
import egaz.go.tz.assets.dto.BoookingDTO;
import egaz.go.tz.assets.entity.BookingAsset;


import java.util.List;

public interface BookingService {
    //List<BoookingDTO> findByCustomer_Id(Long customerId);
    List<BookingAsset> findByBookingStatus(String status);
    BookingAsset saveBookingAsset(BoookingDTO bookingAsset);
    List<BookingAsset> findByAsset_Id(Long assetId);
    List<BookingDtoResponse>findByCustomer_Id(Long customerId);
}
