package tran.example.smartcartest.model.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "ApplicationUser")
public class ApplicationUser extends DomainObject implements UserDetails {
    // TODO: see if there is a way not to use UserDetails. May not need it as we don't use enabled accounts and such.

    private String password;

    private String username;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "custom_token_id", referencedColumnName = "id")
    private CustomToken customToken;

    /**
     * A list of the roles associated with this user.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "APPLICATION_USER_ROLE", joinColumns = @JoinColumn(name = "application_user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public ApplicationUser() { }

    public ApplicationUser(String username, String password) {
        this.password = password;
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // below methods are not used in this application so they can be ignored although I have default implementations for them.

    public Set<Role> getRoles() {
        return roles;
    }

    public CustomToken getCustomToken() {
        return customToken;
    }

    public void setCustomToken(CustomToken customToken) {
        this.customToken = customToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationUser that = (ApplicationUser) o;
        return Objects.equals(username, that.username) &&
                Objects.equals(roles, that.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, roles);
    }

    /*
     * for this sample application I will not need the below methods.
     * note: I need to implement UserDetails spring security can be wired.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
