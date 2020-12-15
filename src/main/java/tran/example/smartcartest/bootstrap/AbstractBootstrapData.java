package tran.example.smartcartest.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import tran.example.smartcartest.model.domain.ApplicationUser;
import tran.example.smartcartest.model.domain.Role;
import tran.example.smartcartest.service.registration.RegistrationService;
import tran.example.smartcartest.service.role.RoleService;
import tran.example.smartcartest.service.user.ApplicationUserService;

import java.util.stream.StreamSupport;

import static tran.example.smartcartest.utility.constants.data.TestDataConstants.*;
import static tran.example.smartcartest.utility.constants.security.SecurityConstants.USER_AUTHORITY;

/**
 * Class to provide basic functionality to create two users to prevent the need for going through the registration form.
 */
public class AbstractBootstrapData implements ApplicationListener<ContextRefreshedEvent> {

    private ApplicationUserService applicationUserService;
    private RegistrationService registrationService;
    private RoleService roleService;

    public AbstractBootstrapData(ApplicationUserService applicationUserService, RegistrationService registrationService,
                                 RoleService roleService) {
        this.applicationUserService = applicationUserService;
        this.registrationService = registrationService;
        this.roleService = roleService;
    }

    private boolean checkUsers() {
        Iterable<ApplicationUser> users = applicationUserService.getAllUsers();
        return StreamSupport.stream(users.spliterator(), false).count() < 1;
    }

    private boolean checkRoles() {
        Iterable<Role> roles = roleService.getAllRoles();
        return StreamSupport.stream(roles.spliterator(), false).count() < 1;
    }

    private void createUsers() {
        // create two users
        createUser(USER_NAME_ONE, PASSWORD_ONE);

        createUser(USER_NAME_TWO, PASSWORD_ONE);
    }


    private void createUser(String userName, String password) {
        registrationService.createUser(userName, password);
    }

    private void createRoles() {
        // create user role.
        createRole(USER_AUTHORITY);
    }

    private void createRole(String roleName) {
        roleService.save(roleName);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(checkRoles()) {
            createRoles();
        }

        if(checkUsers()) {
            createUsers();
        }
    }
}
