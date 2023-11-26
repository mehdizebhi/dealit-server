package ir.dealit.restful.module.user.service;

import ir.dealit.restful.module.user.entity.RoleEntity;
import ir.dealit.restful.module.user.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoleDaoService {

    private final RoleRepository repository;

    @Transactional
    public RoleEntity registerRole (RoleEntity roleEntity) throws Exception {
        Integer count = repository.countByName(roleEntity.getName());
        if (count > 0) {
            throw new RuntimeException(String.format("Can not create new role. %s is exist", roleEntity.getName()));
        }
        return repository.save(roleEntity);
    }

    @Transactional
    public RoleEntity addPrivilegesToRole(String roleName, String... privileges){
        var role = repository.findByName(roleName);
        if (role == null) {
            throw new RuntimeException(String.format("Can not find the role. %s is not exist", roleName));
        }
        role.addPrivileges(privileges);
        return repository.save(role);
    }

    public RoleEntity loadRoleByName(String name){
        return repository.findByName(name);
    }
}
