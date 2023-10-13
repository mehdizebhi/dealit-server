package ir.dealit.restful.module.user.service;

import ir.dealit.restful.module.user.entity.RoleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleDaoService {

    public Optional<RoleEntity> findByAuthority() {
        return null;
    }

    public void register() {

    }
}
