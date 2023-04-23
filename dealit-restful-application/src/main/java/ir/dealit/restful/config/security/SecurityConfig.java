package ir.dealit.restful.config.security;

import ir.dealit.restful.config.security.jwt.util.JwtUtils;
import ir.dealit.restful.config.security.jwt.util.JwtUtilsImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {
    @Bean
    public JwtUtils getJwtUtilsBean() {
        return new JwtUtilsImpl();
    }
}
