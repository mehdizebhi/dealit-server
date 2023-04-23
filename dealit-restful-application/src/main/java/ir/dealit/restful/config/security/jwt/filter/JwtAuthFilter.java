package ir.dealit.restful.config.security.jwt.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;


public abstract class JwtAuthFilter extends OncePerRequestFilter {

    protected void defaultDoFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        final String authHeader = extractAuthHeader(request);
        final String jwt;
        final String username;
        if (!isValidHeader(authHeader)) {
            filterChain.doFilter(request, response);
            return;
        }
        jwt = extractToken(authHeader);
        username = extractSubject(jwt);
        if (isValidSubject(username) && !isAlreadyAuthenticated()) {
            UserDetails user = loadUserBySubject(username);
            if (isValidLoadedUser(user) && isTokenValid(jwt, user)) {
                setAuthenticationSecurityContextHolder(createAthenticationToken(
                        user, null, user.getAuthorities(), request
                ));
            }
        }
        filterChain.doFilter(request, response);
    }

    protected boolean isAlreadyAuthenticated() {
        return SecurityContextHolder.getContext().getAuthentication() != null;
    }

    protected Authentication createAthenticationToken(
            Object principal,
            Object credentials,
            Collection<? extends GrantedAuthority> authorities,
            HttpServletRequest context) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(principal, credentials, authorities);
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(context));
        return authToken;
    }

    protected <T extends Authentication> void setAuthenticationSecurityContextHolder(T authToken) {
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }

    protected abstract String extractAuthHeader(HttpServletRequest request);

    protected abstract String extractToken(String authHeader);

    protected abstract String extractSubject(String token);

    protected abstract boolean isValidHeader(String authHeader);

    protected abstract boolean isTokenValid(String token, UserDetails user);

    protected abstract boolean isValidSubject(String subject);

    protected abstract boolean isValidLoadedUser(UserDetails user);

    protected abstract UserDetails loadUserBySubject(String subject);

}
