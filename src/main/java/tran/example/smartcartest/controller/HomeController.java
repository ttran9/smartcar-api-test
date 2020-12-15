package tran.example.smartcartest.controller;

import com.smartcar.sdk.SmartcarException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tran.example.smartcartest.service.security.TokenValidationService;
import tran.example.smartcartest.utility.controller.ControllerHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;
import static tran.example.smartcartest.utility.constants.KeyConstants.*;
import static tran.example.smartcartest.utility.constants.PathConstants.HOME_PATH;
import static tran.example.smartcartest.utility.constants.PathConstants.VEHICLE_PATH;
import static tran.example.smartcartest.utility.constants.ValueConstants.HOME_TITLE;
import static tran.example.smartcartest.utility.constants.security.SecurityConstants.ANONYMOUS_USER;
import static tran.example.smartcartest.utility.constants.view.TextConstants.VIEW_VEHICLES_TEXT;
import static tran.example.smartcartest.utility.constants.view.ViewConstants.HOME_VIEW_NAME;

/**
 * Endpoints to display the home page.
 */
@Controller
public class HomeController {

    private TokenValidationService tokenValidationService;

    public HomeController(TokenValidationService tokenValidationService) {
        this.tokenValidationService = tokenValidationService;
    }

    @GetMapping(HOME_PATH)
    public String displayHomePage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(true);

        String userName = ControllerHelper.getLoggedInUserName();
        try {
            if(!userName.equals(ANONYMOUS_USER)) {
                if(tokenValidationService.checkToken(userName)) {
                    ControllerHelper.addAttribute(model, VEHICLE_PATH_KEY, VEHICLE_PATH);
                    ControllerHelper.addAttribute(model, VEHICLE_TEXT_KEY, VIEW_VEHICLES_TEXT);
                }
            }
        } catch (SmartcarException exception) {
            System.out.println(exception.getMessage());
        }
        System.out.println("authentication object below!");
        System.out.println(session.getAttribute(SPRING_SECURITY_CONTEXT_KEY));
        System.out.println("authentication object above");

        ControllerHelper.addAttribute(model, TITLE_KEY, HOME_TITLE);
        return HOME_VIEW_NAME;
    }
}
