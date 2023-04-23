package ir.dealit.restful.config.security.jwt.util;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public interface JwtUtils {

    String extractSubject(String token);

    Date extractExpiration(String token);

    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    Claims extractAllClaims(String token);

    boolean isTokenExpired(String token);

    String generateToken(UserDetails user);

    String generateToken(Map<String, Object> extraClaims, UserDetails user);

    boolean isTokenValid(String token, UserDetails user);

}
