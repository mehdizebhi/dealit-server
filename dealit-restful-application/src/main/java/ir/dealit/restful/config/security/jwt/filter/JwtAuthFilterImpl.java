package ir.dealit.restful.config.security.jwt.filter;

import ir.dealit.restful.config.security.jwt.util.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilterImpl extends JwtAuthFilter {

    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {
        super.defaultDoFilterInternal(request, response, filterChain);
    }

    @Override
    protected String extractAuthHeader(HttpServletRequest request) {
        return null;
    }

    @Override
    protected String extractToken(String authHeader) {
        return null;
    }

    @Override
    protected String extractSubject(String token) {
        return null;
    }

    @Override
    protected boolean isValidHeader(String authHeader) {
        return false;
    }

    @Override
    protected boolean isValidToken(String token) {
        return false;
    }

    @Override
    protected boolean isValidSubject(String subject) {
        return false;
    }


    @Override
    protected UserDetails loadUserBySubject(String subject) {
        return null;
    }

    @Override
    protected boolean isValidUser(UserDetails user) {
        return false;
    }

}
