package tran.example.smartcartest.service.exchange;

import com.smartcar.sdk.AuthClient;
import com.smartcar.sdk.SmartcarException;
import com.smartcar.sdk.data.Auth;
import org.springframework.stereotype.Service;
import tran.example.smartcartest.configuration.smartcar.CustomAPIClient;
import tran.example.smartcartest.model.domain.ApplicationUser;
import tran.example.smartcartest.model.domain.CustomToken;
import tran.example.smartcartest.repositories.ApplicationUserRepository;
import tran.example.smartcartest.repositories.CustomTokenRepository;
import tran.example.smartcartest.service.security.TokenValidationService;
import tran.example.smartcartest.utility.api.SmartCarApiHelper;

/*
 * Provides functionality to get the access token associated with the user unless it is expired.
 */
@Service
public class ExchangeServiceImpl implements ExchangeService {

    private CustomTokenRepository customTokenRepository;
    private ApplicationUserRepository applicationUserRepository;
    private TokenValidationService tokenValidationService;

    public ExchangeServiceImpl(CustomTokenRepository customTokenRepository, ApplicationUserRepository applicationUserRepository,
                               TokenValidationService tokenValidationService) {
        this.customTokenRepository = customTokenRepository;
        this.applicationUserRepository = applicationUserRepository;
        this.tokenValidationService = tokenValidationService;
    }

    /**
     * Updates the logged in user's access and refresh token with the specified code.
     * @param code The code used to get the access token from the Smartcar API.
     * @param applicationUser The user whose access and refresh token will be updated.
     * @throws SmartcarException Throws an exception if there is an issue with obtaining an access and refresh token.
     */
    @Override
    public void generateAccessToken(String code, ApplicationUser applicationUser) throws SmartcarException {
        // TODO: figure out how to leave in this valid token check.
//        if(!tokenValidationService.checkToken(applicationUser.getUsername())) {
            AuthClient authClient = CustomAPIClient.getClient();
            // check for token and only need to generate a new one. if there is no valid token to be used.
            Auth auth = authClient.exchangeCode(code);

            CustomToken customToken = applicationUser.getCustomToken();
            if(customToken == null) {
                customToken = new CustomToken();
            }
            SmartCarApiHelper.setCustomTokenHelper(customToken, auth.getAccessToken(), auth.getExpiration(),
                    auth.getRefreshToken(), auth.getRefreshExpiration());

            applicationUser.setCustomToken(customToken);
            applicationUserRepository.save(applicationUser);
//        }
    }
}
