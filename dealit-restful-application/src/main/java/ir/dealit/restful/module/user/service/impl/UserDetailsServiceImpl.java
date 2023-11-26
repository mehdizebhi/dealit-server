package ir.dealit.restful.module.user.service.impl;

import ir.dealit.restful.module.user.entity.PrivilegeEntity;
import ir.dealit.restful.module.user.entity.RoleEntity;
import ir.dealit.restful.module.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = repository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username is not found.");
        }
        user.setAuthorities(getAuthorities(user.getRoles()));
        return user;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<RoleEntity> roles) {
        return getGrantedAuthorities(getPrivileges(roles));
    }

    private Set<String> getPrivileges(Collection<RoleEntity> roles) {
        Set<String> privileges = new HashSet<>();
        for (RoleEntity role : roles) {
            privileges.add(role.getName());
            privileges.addAll(role.getPrivileges());
        }
        return privileges;
    }

    private Set<GrantedAuthority> getGrantedAuthorities(Collection<String> privileges) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (String privilege : privileges){
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}
