package tran.example.smartcartest.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tran.example.smartcartest.model.domain.ApplicationUser;
import tran.example.smartcartest.model.domain.CustomToken;

/**
 * An object to allow the application to grab data from the CustomToken table.
 */
@Repository
public interface CustomTokenRepository extends CrudRepository<CustomToken, Long> {
    void deleteCustomTokenByApplicationUser(ApplicationUser applicationUser);
}