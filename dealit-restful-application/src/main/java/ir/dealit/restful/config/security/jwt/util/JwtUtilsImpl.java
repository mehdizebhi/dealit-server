package ir.dealit.restful.config.security.jwt.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@Slf4j
public class JwtUtilsImpl implements JwtUtils {

    @Value("${jwt.key}")
    private String SECRET_KEY;

    @Value("${jwt.period}")
    private long EXPIRATION_PERIOD;

    @Override
    public String extractSubject(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    @Override
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    @Override
    public Claims extractAllClaims(String token) {
        Claims claims = null;
        try {
            claims = Jwts
                    .parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.warn("There is a problem with the JWT token: {}", token);
        }
        return claims;
    }

    private Key getSigningKey() {
        byte[] keyByte = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyByte);
    }

    @Override
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    @Override
    public String generateToken(UserDetails user) {
        return generateToken(new HashMap<>(), user);
    }

    @Override
    public String generateToken(Map<String, Object> extraClaims, UserDetails user) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_PERIOD))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public boolean isTokenValid(String token, UserDetails user) {
        final String username = extractSubject(token);
        return username.equals(user.getUsername()) && !isTokenExpired(token);
    }
}
