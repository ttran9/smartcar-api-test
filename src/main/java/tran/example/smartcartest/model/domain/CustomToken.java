package tran.example.smartcartest.model.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Holds the logged in user and the corresponding access token information.
 */
@Entity
@Table(name = "CustomToken")
public class CustomToken extends DomainObject {

    @OneToOne(mappedBy = "customToken")
    private ApplicationUser applicationUser;

    private String accessToken;
    private Date expiration;
    private String refreshToken;
    private Date refreshExpiration;

    public CustomToken() { }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Date getRefreshExpiration() {
        return refreshExpiration;
    }

    public void setRefreshExpiration(Date refreshExpiration) {
        this.refreshExpiration = refreshExpiration;
    }
}
