package tran.example.smartcartest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import tran.example.smartcartest.exception.AddRoleException;
import tran.example.smartcartest.exception.UserExistsException;
import tran.example.smartcartest.model.form.RegistrationForm;
import tran.example.smartcartest.service.registration.RegistrationService;
import tran.example.smartcartest.utility.controller.ControllerHelper;

import static tran.example.smartcartest.utility.constants.KeyConstants.TITLE_KEY;
import static tran.example.smartcartest.utility.constants.PathConstants.REGISTRATION_PATH;
import static tran.example.smartcartest.utility.constants.ValueConstants.REGISTRATION_TITLE;
import static tran.example.smartcartest.utility.constants.view.ViewConstants.LOGIN_VIEW_NAME;
import static tran.example.smartcartest.utility.constants.view.ViewConstants.REGISTRATION_VIEW_NAME;


/**
 * Endpoint(s) to handle registration requests.
 */
@Controller
public class RegistrationController {

    private Validator registrationFormValidator;
    private RegistrationService registrationService;

    public RegistrationController(@Qualifier("registrationFormValidator") Validator registrationFormValidator,
                                  RegistrationService registrationService) {
        this.registrationFormValidator = registrationFormValidator;
        this.registrationService = registrationService;
    }

    @GetMapping(REGISTRATION_PATH)
    public String register(@ModelAttribute RegistrationForm registrationForm, Model model) {
        ControllerHelper.addAttribute(model, TITLE_KEY, REGISTRATION_TITLE);
        return REGISTRATION_VIEW_NAME;
    }

    @PostMapping(REGISTRATION_PATH)
    public String processRegistration(@ModelAttribute RegistrationForm registrationForm, Model model, BindingResult bindingResult) {
        String viewName = LOGIN_VIEW_NAME;

        // validate the form itself.
        registrationFormValidator.validate(registrationForm, bindingResult);
        if(bindingResult.hasErrors()) {
            viewName = REGISTRATION_VIEW_NAME;
        } else {
            // use a try catch block and if we catch an error redirect back to the registration form.
            try {
                registrationService.createUser(registrationForm.getUsername(), registrationForm.getPassword());
            } catch(UserExistsException | AddRoleException exception) {
                System.out.println(exception.getMessage());
                viewName = REGISTRATION_VIEW_NAME;
            }
        }

        ControllerHelper.addAttribute(model, TITLE_KEY, REGISTRATION_TITLE);
        return viewName;
    }
}
