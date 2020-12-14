package tran.example.smartcartest.configuration.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import tran.example.smartcartest.exception.CustomAuthenticationException;
import tran.example.smartcartest.model.domain.ApplicationUser;
import tran.example.smartcartest.model.domain.Role;
import tran.example.smartcartest.service.role.RoleService;
import tran.example.smartcartest.service.security.CustomUserDetailsService;

import java.util.Collection;
import java.util.Set;

import static tran.example.smartcartest.utility.constants.security.SecurityConstants.INVALID_CREDENTIALS_ERROR;

//@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private CustomUserDetailsService customUserDetailsService;

    public CustomAuthenticationProvider(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        System.out.println("provider authentication");
        Authentication auth = null;

        String userName = authentication.getName();
        String password = (String) authentication.getCredentials();

        // stored user.
        ApplicationUser applicationUser = (ApplicationUser) customUserDetailsService.loadUserByUsername(userName);

        if(BCrypt.checkpw(password, applicationUser.getPassword())) {
            Set<Role> roles = applicationUser.getRoles();
            auth = new UsernamePasswordAuthenticationToken(userName, password, roles);
        }

        return auth;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
