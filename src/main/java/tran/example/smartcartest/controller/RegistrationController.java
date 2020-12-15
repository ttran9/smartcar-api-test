package tran.example.smartcartest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import tran.example.smartcartest.model.form.ApplicationUserForm;
import tran.example.smartcartest.utility.controller.ControllerHelper;

import static tran.example.smartcartest.utility.constants.KeyConstants.TITLE_KEY;
import static tran.example.smartcartest.utility.constants.PathConstants.REGISTRATION_PATH;
import static tran.example.smartcartest.utility.constants.ValueConstants.REGISTRATION_TITLE;
import static tran.example.smartcartest.utility.constants.view.ViewConstants.REGISTRATION_VIEW_NAME;


/**
 * Endpoint(s) to handle registration requests.
 */
@Controller
public class RegistrationController {

    @GetMapping(REGISTRATION_PATH)
    public String login(@ModelAttribute ApplicationUserForm applicationUserForm, Model model) {
        // TODO: provide login.html
        ControllerHelper.addAttribute(model, TITLE_KEY, REGISTRATION_TITLE);
        return REGISTRATION_VIEW_NAME;
    }
}
