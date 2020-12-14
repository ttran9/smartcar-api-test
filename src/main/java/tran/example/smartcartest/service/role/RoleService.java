package tran.example.smartcartest.service.role;

import tran.example.smartcartest.model.domain.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAllRoles();

    void save(String roleName);
}
