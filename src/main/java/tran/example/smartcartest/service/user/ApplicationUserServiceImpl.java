package tran.example.smartcartest.service.user;

import org.springframework.stereotype.Service;
import tran.example.smartcartest.model.domain.ApplicationUser;
import tran.example.smartcartest.repositories.ApplicationUserRepository;
import tran.example.smartcartest.utility.common.DomainHelper;

import java.util.List;

@Service
public class ApplicationUserServiceImpl implements ApplicationUserService {

    private ApplicationUserRepository applicationUserRepository;

    public ApplicationUserServiceImpl(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public List<ApplicationUser> getAllUsers() {
        return DomainHelper.userIterableToList(this.applicationUserRepository.findAll());
    }

    @Override
    public ApplicationUser getUserByUserName(String userName) {
        return applicationUserRepository.findByUsername(userName);
    }
}
