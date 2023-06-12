package ir.dealit.restful.service.user;

import ir.dealit.restful.entity.user.UserEntity;
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

    public UserEntity save(UserEntity userEntity) {
        //Todo: use DTO object
        //Todo: validate UserEntity object
        return userRepository.save(userEntity);
    }

    public UserEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public List<UserEntity> loadAllUsers() {
        return userRepository.findAll();
    }
}
