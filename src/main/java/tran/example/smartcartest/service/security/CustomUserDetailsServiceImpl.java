package tran.example.smartcartest.service.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tran.example.smartcartest.model.domain.ApplicationUser;
import tran.example.smartcartest.repositories.ApplicationUserRepository;

import static tran.example.smartcartest.utility.constants.ErrorConstants.USER_NOT_FOUND_MESSAGE;

@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    private ApplicationUserRepository applicationUserRepository;

    public CustomUserDetailsServiceImpl(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
        /*
         * the below occurs if a user tries to log in a username not yet registered.
         */
        if(applicationUser == null) throw new UsernameNotFoundException(USER_NOT_FOUND_MESSAGE);

        return applicationUser;
    }
}
