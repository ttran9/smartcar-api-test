package tran.example.smartcartest.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tran.example.smartcartest.model.domain.ApplicationUser;

/**
 * An object to allow the application to grab data from the ApplicationUser table.
 */
@Repository
public interface ApplicationUserRepository extends CrudRepository<ApplicationUser, Long>  {
    ApplicationUser findByUsername(String username);
}
