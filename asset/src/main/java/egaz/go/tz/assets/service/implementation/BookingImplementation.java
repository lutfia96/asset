package egaz.go.tz.assets.service.implementation;

import egaz.go.tz.assets.dto.AssetDTO;
import egaz.go.tz.assets.dto.BookingDtoResponse;
import egaz.go.tz.assets.dto.BoookingDTO;
import egaz.go.tz.assets.entity.Asset;
import egaz.go.tz.assets.entity.BookingAsset;
import egaz.go.tz.assets.entity.Customer;
import egaz.go.tz.assets.repository.AssetRepo;
import egaz.go.tz.assets.repository.BookingRepo;
import egaz.go.tz.assets.repository.CustomerRepo;
import egaz.go.tz.assets.service.BookingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springdoc.webmvc.core.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class BookingImplementation implements BookingService {
    @Autowired
    private BookingRepo bookingRepo;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RequestService requestService;
    @Autowired
    private AssetRepo assetRepo;


    @Override
    public List<BookingDtoResponse> findByCustomer_Id(Long customerId) {
        Customer customer= customerRepo.findById(customerId).orElseThrow();
        new RuntimeException("Customer not found");
       List <BookingAsset>bookingAsset = bookingRepo.findByCustomer( customer );
       if (bookingAsset.isEmpty() || customerId==null) {
           log.info("No booking found for"+customerId);
           return List.of();
       }else
        return bookingAsset.stream().map(a ->{
            var as = modelMapper.map(a, BookingDtoResponse.class);
                as.setCustomerName(a.getCustomer().getFirstname());
                as.setPrice(String.valueOf(a.getAsset().getPrice()));
                return as;
        }
        )
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingAsset> findByBookingStatus(String status) {
        return List.of();
    }

    @Override
    public BookingAsset saveBookingAsset(BoookingDTO request) {
        Customer customer = customerRepo.findById(request.getCustomerId()).orElseThrow();
        new RuntimeException("Customer not found");
        Asset asset= assetRepo.findById(request.getAssetId()).orElseThrow();
        new  RuntimeException("Asset not found");
        BookingAsset bookingAsset = modelMapper.map(request, BookingAsset.class);
        BookingAsset booking = new BookingAsset();
        if (request.getBookingId() == null) {
            booking.setCustomer(customer);
            booking.setAsset(asset);
            booking.setBookingStatus(request.getBookingStatus());
            booking.setBookingName(request.getBookingName());
            booking.setDescription(request.getDescription());

            return bookingRepo.save(booking);
        } else {
           BookingAsset booking1 = bookingRepo.findById(request.getBookingId())
                    .orElseThrow(() ->
                            new RuntimeException("Booking not found"));
            booking1.setBookingStatus(request.getBookingStatus());
            booking1.setBookingName(request.getBookingName());
            booking1.setDescription(request.getDescription());
            booking1.setCustomer(customer);
            booking1.setAsset(asset);

            return bookingRepo.save(booking1);
        }
    }

    @Override
    public List<BookingAsset> findByAsset_Id(Long assetId) {
        return List.of();
    }
}
