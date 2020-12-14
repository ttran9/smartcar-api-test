package tran.example.smartcartest.bootstrap;

import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import tran.example.smartcartest.service.registration.RegistrationService;
import tran.example.smartcartest.service.role.RoleService;
import tran.example.smartcartest.service.user.ApplicationUserService;

import static tran.example.smartcartest.utility.constants.data.TestDataConstants.PRODUCTION_PROFILE;

@Component
@Profile(PRODUCTION_PROFILE)
public class GenerateTestData extends AbstractBootstrapData {

    private ApplicationUserService applicationUserService;
    private RegistrationService registrationService;
    private RoleService roleService;

    public GenerateTestData(ApplicationUserService applicationUserService, RegistrationService registrationService,
                            RoleService roleService) {
        super(applicationUserService, registrationService, roleService);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        super.onApplicationEvent(contextRefreshedEvent);
    }
}
