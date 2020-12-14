package tran.example.smartcartest.service.security;

import com.smartcar.sdk.SmartcarException;
import com.smartcar.sdk.data.Auth;
import org.springframework.stereotype.Service;
import tran.example.smartcartest.configuration.smartcar.CustomAPIClient;
import tran.example.smartcartest.model.domain.ApplicationUser;
import tran.example.smartcartest.model.domain.CustomToken;
import tran.example.smartcartest.repositories.ApplicationUserRepository;
import tran.example.smartcartest.repositories.CustomTokenRepository;
import tran.example.smartcartest.utility.api.SmartCarApiHelper;

import java.util.Date;

@Service
public class TokenValidationServiceImpl implements TokenValidationService {
    private ApplicationUserRepository applicationUserRepository;
    private CustomTokenRepository customTokenRepository;

    public TokenValidationServiceImpl(ApplicationUserRepository applicationUserRepository, CustomTokenRepository customTokenRepository) {
        this.applicationUserRepository = applicationUserRepository;
        this.customTokenRepository = customTokenRepository;
    }

    /**
     * Checks if the user has an expired access token and refresh token.
     * @param userName The name of the logged in user to be tested for.
     * @return True if the user has an access or refresh token that is not expired.
     */
    public boolean checkToken(String userName) throws SmartcarException {
        // TODO: once I get this working need to modify this to use the refresh token instead of the access token.

        boolean hasValidToken = true;
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(userName);

        CustomToken customToken = applicationUser.getCustomToken();

        if(customToken == null) {
            hasValidToken = false;
        } else {
            String accessToken = customToken.getAccessToken();

            if(accessToken.length() == 0) {
                /*
                 * it is safe to assume that if we don't have an access token there will be no refresh token so
                 * that is why I am only checking for the access token.
                 */
                hasValidToken = false;
                customTokenRepository.deleteCustomTokenByApplicationUser(applicationUser);
            } else {
                Date date = new Date();
                if(date.after(customToken.getExpiration())) {
                    // check the refresh token.
                    if(date.after(customToken.getRefreshExpiration())) {
                        // both tokens are expired.
                        // just delete the custom token.
                        hasValidToken = false;
                        customTokenRepository.deleteCustomTokenByApplicationUser(applicationUser);
                    } else {
                        // only access token is expired but refresh isn't.
                        String refreshToken = customToken.getRefreshToken();
                        Auth newAccess = CustomAPIClient.getClient().exchangeRefreshToken(refreshToken);
                        SmartCarApiHelper.setCustomTokenHelper(customToken, newAccess.getAccessToken(),
                                                                newAccess.getExpiration(), newAccess.getRefreshToken(),
                                                                newAccess.getRefreshExpiration());
                        applicationUserRepository.save(applicationUser);
                    }
                }

            }
        }
        return hasValidToken;
    }

}
