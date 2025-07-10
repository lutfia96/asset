package egaz.go.tz.assets.service;

import egaz.go.tz.assets.entity.Seller;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    String secret="JA3UM02kKWwR1y4jWynkdOlINjF27WfH";

    public String generateAccessToken(String email){
         Map<String,Object>claims=new HashMap<>();
//         claims.put("email",seller.getEmail());
//         claims.put("id",seller.getId());
//         claims.put("firstName",seller.getName());
       return generateToken(email,new Date(System.currentTimeMillis()*3600*1000),claims);

    }
    public String generateRefreshToken(String email){
        return generateToken(email,new Date(System.currentTimeMillis()*1000),null);

    }


    private String generateToken(String email,Date expiration, Map<String,Object> claims) {
   return Jwts.builder()
           .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
           .issuedAt(new Date())
           .expiration(expiration)
           .subject(email)
           .claims(claims)
           .compact();

    }

    public boolean validateToken(String token){
        try {
            var header= token.startsWith("Bearer ") ? token.substring(7) : token;
            System.out.println("token:"+header);
            var jwt = getClaims(header);
            return jwt.getExpiration().after(new Date());
        }catch (Exception e){
            System.out.println("Token validation failed: " + e.getMessage());
            return false;
        }
    }

    private Claims getClaims(String token) {
        return  Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


    public String getEmailFromToken(String token) {

        return getClaims(token).getSubject();
    }
    public String generateTokenById(Seller seller) {
  return       Jwts.builder()
                .setSubject(seller.getId().toString())
                .claim("email",seller.getEmail())
                .claim("name",seller.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000L *60*60))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();


    }
    public Boolean isTokenExpired(String token) {
        try {
            var jwt = getClaims(token);
            return jwt.getExpiration().before(new Date());
        }catch (Exception e){
            return false;
        }
    }
}
