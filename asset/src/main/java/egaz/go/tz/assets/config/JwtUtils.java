//package egaz.go.tz.asset.config;
//
//
//import egaz.go.tz.asset.entity.Customer;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.io.Serial;
//import java.io.Serializable;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Set;
//import java.util.function.Function;
//
//@Slf4j
//@Component
//public class JwtUtils implements Serializable {
//    @Serial
//    private static final long serialVersionUID = -2550185165626007488L;
//
//    @Value("${jwtTokenValidity}")
//    public long JWT_TOKEN_VALIDITY;
//
//    @Value("${jwtApiTokenValidity}")
//    public long JWT_API_TOKEN_VALIDITY;
//    private final String secretKey = "s0@s-@uth";
//
//    public String generateTokenFromUsername(String username) {
//        return Jwts.builder().setSubject(username).setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
//                .signWith(SignatureAlgorithm.HS512,secretKey)
//                .compact();
//    }
//    public Date getExpirationDateFromToken(String token) {
//
//        return getClaimFromToken(token, Claims::getExpiration);
//    }
//
//    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims;
//        claims = getAllClaimsFromToken(token);
//        return claimsResolver.apply(claims);
//    }
//    public Claims getAllClaimsFromToken(String token) {
////        return Jwts.parser(setSigningKey(secretKey).build().parseClaimsJws(token).getBody());
//        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
//    }
//    private Boolean isTokenExpired(String token) {
//        final Date expiration = getExpirationDateFromToken(token);
//        return expiration.before(new Date());
//    }
//    public String generateToken(Customer userDetails, Set<String> permissions) {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("userId", userDetails.getCustomer_id());
//        claims.put("firstname", userDetails.getFirstname());
//        claims.put("lastname", userDetails.getLastname());
//        claims.put("email", userDetails.getEmail());
//        claims.put("address", userDetails.getAddress());
//        claims.put("phone", userDetails.getPhone());
//        claims.put("role", userDetails.getRole());
//        claims.put("password", userDetails.getPassword());
//        return doGenerateToken(claims, userDetails.getEmail());
//    }
//
//
//    private String doGenerateToken(Map<String, Object> claims, String subject) {
//        return Jwts.builder().setClaims(claims).setSubject(subject)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
//                .signWith(SignatureAlgorithm.HS512,secretKey)
//                .compact();
//    }
//    public Boolean validateToken(String token, UserDetails userDetails) {
//        final String username = getUsernameFromToken(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
//    public String getUsernameFromToken(String token) {
//        final Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
//        return claims.getSubject();
//    }
//}
