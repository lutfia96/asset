package egaz.go.tz.assets.repository;

import egaz.go.tz.assets.constants.BookingStatus;
import egaz.go.tz.assets.entity.Asset;
import egaz.go.tz.assets.entity.BookingAsset;
import egaz.go.tz.assets.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookingRepo extends JpaRepository<BookingAsset, Long> {

    List<BookingAsset> findByCustomer(Customer customer );
    List<BookingAsset> findByAsset(Asset asset);

    List<BookingAsset> findByBookingStatus(BookingStatus bookingStatus);


}
