package egaz.go.tz.assets.controller;

import egaz.go.tz.assets.dto.BookingDtoResponse;
import egaz.go.tz.assets.dto.BoookingDTO;
import egaz.go.tz.assets.entity.BookingAsset;
import egaz.go.tz.assets.service.BookingService;
import egaz.go.tz.assets.service.implementation.BookingImplementation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingImplementation bookingService;

 private final ModelMapper modelMapper;

    @PostMapping("/save")
    public ResponseEntity<BoookingDTO> saveBooking(@RequestBody BoookingDTO bookingAsset) {
        BookingAsset booking = bookingService.saveBookingAsset(bookingAsset);
        BoookingDTO response;
        response = modelMapper.map(bookingAsset, BoookingDTO.class);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/bookedAssets/{customerId}")
    public ResponseEntity<List<BookingDtoResponse>> bookedAssets(@PathVariable Long customerId) {
        List<BookingDtoResponse> bookingAsset = bookingService.findByCustomer_Id(Long.valueOf(customerId));
        return ResponseEntity.ok(bookingAsset);
    }
}
