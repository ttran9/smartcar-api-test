package tran.example.smartcartest.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tran.example.smartcartest.utility.controller.ControllerHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;
import static tran.example.smartcartest.utility.constants.KeyConstants.TITLE_KEY;
import static tran.example.smartcartest.utility.constants.PathConstants.HOME_PATH;
import static tran.example.smartcartest.utility.constants.ValueConstants.HOME_TITLE;
import static tran.example.smartcartest.utility.constants.ViewConstants.HOME_PAGE;

/**
 * Endpoints to display the home page.
 */
@Controller
public class HomeController {

    @GetMapping(HOME_PATH)
    public String displayHomePage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(true);
        System.out.println("authentication object below!");
        System.out.println(session.getAttribute(SPRING_SECURITY_CONTEXT_KEY));
        System.out.println("authentication object above");

        ControllerHelper.addAttribute(model, TITLE_KEY, HOME_TITLE);
        return HOME_PAGE;
    }
}
