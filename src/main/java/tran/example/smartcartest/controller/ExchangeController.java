package tran.example.smartcartest.controller;

import com.smartcar.sdk.SmartcarException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import tran.example.smartcartest.model.domain.ApplicationUser;
import tran.example.smartcartest.service.exchange.ExchangeService;
import tran.example.smartcartest.service.security.CustomUserDetailsService;
import tran.example.smartcartest.utility.controller.ControllerHelper;

import static tran.example.smartcartest.configuration.smartcar.constants.SmartCarApiConstants.SMARTCAR_CONNECT_LINK;
import static tran.example.smartcartest.utility.constants.PathConstants.EXCHANGE_PATH;
import static tran.example.smartcartest.utility.constants.PathConstants.REMOVE_TOKEN_PATH;
import static tran.example.smartcartest.utility.constants.view.ViewConstants.VEHICLE_VIEW_NAME;

/*
 * Endpoint to obtain an access token from the Smartcar API.
 */
@Controller
public class ExchangeController {

    private ExchangeService exchangeService;
    private CustomUserDetailsService customUserDetailsService;

    public ExchangeController(ExchangeService exchangeService, CustomUserDetailsService customUserDetailsService) {
        this.exchangeService = exchangeService;
        this.customUserDetailsService = customUserDetailsService;
    }

    @GetMapping(EXCHANGE_PATH)
    public RedirectView exchange(@RequestParam String code) {
//         if something goes wrong just go back home and try to connect to Smartcar Connect.
        RedirectView redirectView = new RedirectView("");
        System.out.println("in exchange controller!!");
        String userName = ControllerHelper.getLoggedInUserName();

        try {
            ApplicationUser applicationUser = (ApplicationUser) customUserDetailsService.loadUserByUsername(userName);
            exchangeService.generateAccessToken(code, applicationUser);
            redirectView.setUrl(VEHICLE_VIEW_NAME);
        } catch (SmartcarException exception) {
            System.out.println(exception.getMessage());
        } catch (UsernameNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
       return redirectView;
    }

    @GetMapping(REMOVE_TOKEN_PATH)
    public RedirectView removeToken() {
        RedirectView redirectView = new RedirectView(SMARTCAR_CONNECT_LINK);
        String userName = ControllerHelper.getLoggedInUserName();
        exchangeService.deleteAccessToken(userName);
        return redirectView;
    }
}
