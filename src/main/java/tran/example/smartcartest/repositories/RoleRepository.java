package tran.example.smartcartest.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tran.example.smartcartest.model.domain.ApplicationUser;
import tran.example.smartcartest.model.domain.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findRoleByAuthority(String authority);
}


