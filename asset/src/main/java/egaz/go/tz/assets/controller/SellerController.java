package egaz.go.tz.assets.controller;

import egaz.go.tz.assets.dto.*;
import egaz.go.tz.assets.entity.Seller;
import egaz.go.tz.assets.service.JwtService;
import egaz.go.tz.assets.service.SellerService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/seller")
@RequiredArgsConstructor
public class SellerController {


    @Autowired
    private SellerService sellerService;
    private final JwtService  jwtService;
     private final AuthenticationManager authenticationManager;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/register")
    public ResponseEntity<Seller> addSeller(@RequestBody SellerDtoRequest sellerDTO) {
      Seller newSeller=  sellerService.register(sellerDTO);
        return ResponseEntity.ok().body(newSeller);
    }
    @PostMapping
    public ResponseEntity<LoginResponse>logIn(@Valid @RequestBody LoginRequest  request , HttpServletResponse response) {
      authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                  request.getEmail(),
                  request.getPassword()
            )
      );
         var accessToken =  jwtService.generateAccessToken(request.getEmail());
         var refreshToken = jwtService.generateRefreshToken(request.getEmail());
         var cookie = new Cookie("REFRESH_TOKEN", accessToken);
        cookie.setHttpOnly(true);
        cookie.setPath("/user/refresh");
        cookie.setMaxAge(604800); //7days
        cookie.setSecure(true);
        response.addCookie(cookie);

        return  ResponseEntity.ok(new LoginResponse(accessToken));

    }


    @PostMapping("/log")
    public ResponseEntity<LoginResponse>logInWithId(@Valid @RequestBody LoginRequest  request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var seller = sellerService.findByEmail(request.getEmail()).orElseThrow();
        var token =  jwtService.generateTokenById(seller);

        return  ResponseEntity.ok(new LoginResponse(token));
    }
@PostMapping("/refreshToken")
    public ResponseEntity<LoginResponse>refreshToken(@CookieValue (value = "REFRESH_TOKEN")String refreshToken) {
    if (!jwtService.isTokenExpired(refreshToken) && jwtService.validateToken(refreshToken)) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();

    }
    if (jwtService.validateToken(refreshToken) || jwtService.isTokenExpired(refreshToken)) {
        var token = jwtService.generateRefreshToken(refreshToken);
        return ResponseEntity.ok(new LoginResponse(token));
    }
    var user = jwtService.getEmailFromToken(refreshToken);
    var  seller = sellerService.findByEmail(user);
    if (seller.isEmpty()) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    }
    return ResponseEntity.ok(new LoginResponse(jwtService.generateAccessToken(user)));

}

    @ExceptionHandler(BadCredentialsException.class)
        public ResponseEntity<Void>badCredentials (){

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

@GetMapping("/get/{sellerId}")
    public ResponseEntity<SellerDTO>getSellerById(@PathVariable Long sellerId){
        SellerDTO sellerDTO = sellerService.getSellerById(sellerId);
        return ResponseEntity.ok().body(sellerDTO);
    }

}
