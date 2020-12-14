package tran.example.smartcartest.service.registration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tran.example.smartcartest.exception.AddRoleException;
import tran.example.smartcartest.exception.UserExistsException;
import tran.example.smartcartest.model.domain.ApplicationUser;
import tran.example.smartcartest.model.domain.Role;
import tran.example.smartcartest.repositories.ApplicationUserRepository;
import tran.example.smartcartest.repositories.RoleRepository;

import static tran.example.smartcartest.utility.constants.security.SecurityConstants.*;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private ApplicationUserRepository applicationUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private RoleRepository roleRepository;

    public RegistrationServiceImpl(ApplicationUserRepository applicationUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
                                   RoleRepository roleRepository) {
        this.applicationUserRepository = applicationUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public void createUser(String userName, String password) {
        String encryptedPassword = bCryptPasswordEncoder.encode(password);
        ApplicationUser newUser = new ApplicationUser(userName, encryptedPassword);

        // check if username is taken. (need custom exception) just to let the user know they cannot create this user.
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(userName);
        if(applicationUser != null) {
            throw new UserExistsException(USER_EXISTS_ERROR);
        }

        // assign the "User" role to the user.
        Role role = roleRepository.findRoleByAuthority(USER_AUTHORITY);

        if(!addUserAndRole(newUser, role)) {
            throw new AddRoleException(ROLE_EXISTS_FOR_USER_ERROR);
        }

        // save/create the user.
        applicationUserRepository.save(newUser);
    }

    private boolean addUserAndRole(ApplicationUser applicationUser, Role role) {
        if(!applicationUser.getRoles().add(role)) {
            return false;
        }
        return role.getUsers().add(applicationUser);
    }
}
