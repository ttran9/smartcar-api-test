package tran.example.smartcartest.service.exchange;

import com.smartcar.sdk.AuthClient;
import com.smartcar.sdk.SmartcarException;
import com.smartcar.sdk.data.Auth;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

/*
 * A component/bean defining method(s) used to set the access token to enable future API calls to be made.
 */
@Service
public class ExchangeServiceImpl implements ExchangeService {

    private String accessToken;

    /*
     * Sets the access token required to make HTTP requests to the Smartcar API.
     */
    @Override
    public RedirectView setAccessToken(String code, AuthClient client) throws SmartcarException {
        Auth auth = client.exchangeCode(code);
        // in a production app you'll want to store this in some kind of persistent storage
        this.setAccessToken(auth.getAccessToken());
        return new RedirectView("vehicle"); // go to vehicle page to see information.
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
