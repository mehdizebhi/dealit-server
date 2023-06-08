package ir.dealit.restful.service.user;

import ir.dealit.restful.entity.user.User;
import ir.dealit.restful.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDaoService implements UserDetailsService {

    private final UserRepository userRepository;

    public User save(User user) {
        //Todo: use DTO object
        //Todo: validate User object
        return userRepository.save(user);
    }

    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public List<User> loadAllUsers() {
        return userRepository.findAll();
    }
}
