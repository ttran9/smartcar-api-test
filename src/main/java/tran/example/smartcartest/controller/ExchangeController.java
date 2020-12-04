package tran.example.smartcartest.controller;

import com.smartcar.sdk.SmartcarException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import tran.example.smartcartest.service.exchange.ExchangeService;
import tran.example.smartcartest.utility.constants.APIClientConstants;

/*
 * Handle the case to assign the access token after being redirected from the /login route.
 */
@Controller
public class ExchangeController {

    private ExchangeService exchangeService;

    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping("/exchange")
    public RedirectView exchange(@RequestParam String code) {
        // if something goes wrong just go back home and try to connect to Smartcar Connect.
        RedirectView redirectView = new RedirectView("");
        try {
            redirectView = exchangeService.setAccessToken(code, APIClientConstants.client);
        } catch (SmartcarException exception) {
            System.out.println(exception.getMessage());
        }
       return redirectView;
    }
}
