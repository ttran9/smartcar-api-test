package tran.example.smartcartest.controller;

import okhttp3.HttpUrl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;
import tran.example.smartcartest.utility.ClientConstants;

/*
 * Defines routes to redirect the user to the page to go to the Smartcar connect under test mode.
 */
@Controller
public class LoginController {

    @GetMapping({"/", "/login"})
    public RedirectView login() {
        /*
         * for now just make it so when the user starts up this local app that he or should is taken to the page to
         * connect the vehicle to Smartcar Connect.
         */
        String link = ClientConstants.client.getAuthUrl();
        return new RedirectView(link);
    }
}
