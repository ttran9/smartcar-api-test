package tran.example.smartcartest.service.exchange;

import com.smartcar.sdk.AuthClient;
import com.smartcar.sdk.SmartcarException;
import org.springframework.web.servlet.view.RedirectView;

/*
 * Interface to enforce expected behaviors used by the ExchangeController.
 */
public interface ExchangeService {

    public RedirectView setAccessToken(String code, AuthClient authClient) throws SmartcarException;

    public String getAccessToken();

    public void setAccessToken(String token);
}
