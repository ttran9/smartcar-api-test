package tran.example.smartcartest.handler;

import com.smartcar.sdk.SmartcarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import tran.example.smartcartest.configuration.smartcar.CustomAPIClient;
import tran.example.smartcartest.exception.CustomAuthenticationException;
import tran.example.smartcartest.service.security.TokenValidationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static tran.example.smartcartest.utility.constants.PathConstants.VEHICLE_PATH;
import static tran.example.smartcartest.utility.constants.error.MessageConstants.AUTHENTICATION_WITH_SMARTCAR_API_ERROR;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private TokenValidationService tokenValidationService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
      throws IOException {

        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
    }

    private void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException {
        try {
            String targetUrl = getTargetUrl(authentication);
            redirectStrategy.sendRedirect(request, response, targetUrl);
        } catch(SmartcarException exception) {
            throw new CustomAuthenticationException(AUTHENTICATION_WITH_SMARTCAR_API_ERROR);
        }
    }

    private String getTargetUrl(Authentication authentication) throws SmartcarException {
        // since we have successfully authenticated we can just grab the username without checks.
        String userName = authentication.getName();
        String targetUrl = CustomAPIClient.getClient().getAuthUrl();

        // the user has a valid access token or refresh token just go back to the home page.
//        if(tokenValidationService.checkToken(userName)) {
//            // go to a page where a user can see vehicle information.
//            targetUrl = VEHICLE_PATH;
//        }

        return targetUrl;
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
