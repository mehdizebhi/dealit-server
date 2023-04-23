package ir.dealit.restful.config.security.jwt.filter;

import ir.dealit.restful.config.security.jwt.util.JwtUtils;
import ir.dealit.restful.service.user.UserDaoService;
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
    private final UserDaoService userDaoService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {
        super.defaultDoFilterInternal(request, response, filterChain);
    }

    @Override
    protected String extractAuthHeader(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    @Override
    protected String extractToken(String authHeader) {
        return authHeader.substring(7);
    }

    @Override
    protected String extractSubject(String token) {
        //Todo: extract with JwtUtil
        return null;
    }

    @Override
    protected boolean isValidHeader(String authHeader) {
        return authHeader != null && authHeader.startsWith("Bearer ");
    }

    @Override
    protected boolean isValidToken(String token) {
        //Todo: validate with JwtUtil
        return false;
    }

    @Override
    protected boolean isValidSubject(String subject) {
        return subject != null && !subject.isBlank();
    }


    @Override
    protected UserDetails loadUserBySubject(String subject) {
        return userDaoService.loadUserByUsername(subject);
    }

    @Override
    protected boolean isValidLoadedUser(UserDetails loadedUser) {
        return loadedUser != null
                && loadedUser.isAccountNonExpired()
                && loadedUser.isEnabled();
    }

}
