package tran.example.smartcartest.service.role;

import org.springframework.stereotype.Service;
import tran.example.smartcartest.model.domain.Role;
import tran.example.smartcartest.repositories.RoleRepository;
import tran.example.smartcartest.utility.common.DomainHelper;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        Iterable<Role> iterable = roleRepository.findAll();
        return DomainHelper.roleIterableToList(iterable);
    }

    @Override
    public void save(String roleName) {
        Role role = roleRepository.findRoleByAuthority(roleName);

        if(role != null) {
            return ;
        }

       roleRepository.save(new Role(roleName));
    }
}
