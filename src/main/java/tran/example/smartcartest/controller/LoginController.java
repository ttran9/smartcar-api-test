package tran.example.smartcartest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import tran.example.smartcartest.model.form.LoginForm;
import tran.example.smartcartest.utility.controller.ControllerHelper;

import static tran.example.smartcartest.utility.constants.KeyConstants.TITLE_KEY;
import static tran.example.smartcartest.utility.constants.PathConstants.LOGIN_PATH;
import static tran.example.smartcartest.utility.constants.ValueConstants.LOGIN_TITLE;
import static tran.example.smartcartest.utility.constants.view.ViewConstants.LOGIN_VIEW_NAME;

/**
 * Defines routes to redirect the user to the page to go to the Smartcar connect under test mode.
 */
@Controller
public class LoginController {

    @GetMapping(LOGIN_PATH)
    public String login(@ModelAttribute LoginForm loginForm, Model model) {
        ControllerHelper.addAttribute(model, TITLE_KEY, LOGIN_TITLE);
        return LOGIN_VIEW_NAME;
    }


}
