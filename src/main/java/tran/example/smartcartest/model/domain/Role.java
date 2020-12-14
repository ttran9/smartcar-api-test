package tran.example.smartcartest.model.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="Role")
public class Role extends DomainObject implements GrantedAuthority  {

    @Column(unique=true)
    private String authority; // the role name

    /**
     * A list holding the users that are associated with this specific role.
     */
    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private Set<ApplicationUser> users = new HashSet<>();

    public Role() { }

    public Role(String authority) {
        this.authority = authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Set<ApplicationUser> getUsers() {
        return users;
    }

    public void setUsers(Set<ApplicationUser> users) {
        this.users = users;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
