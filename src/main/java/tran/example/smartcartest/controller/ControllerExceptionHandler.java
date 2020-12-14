package tran.example.smartcartest.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import tran.example.smartcartest.exception.AddRoleException;
import tran.example.smartcartest.exception.CustomAuthenticationException;
import tran.example.smartcartest.exception.UserExistsException;

import static tran.example.smartcartest.utility.constants.KeyConstants.ERROR_KEY;
import static tran.example.smartcartest.utility.constants.view.ViewConstants.*;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({UserExistsException.class})
    public ModelAndView handleUserExists(UserExistsException exception) {
        ModelAndView modelAndView = new ModelAndView(REGISTRATION_VIEW_NAME);
        modelAndView.addObject(ERROR_KEY, exception.getMessage());
        return modelAndView;
    }

    @ExceptionHandler({AddRoleException.class})
    public ModelAndView handleRoleExists(AddRoleException exception) {
        ModelAndView modelAndView = new ModelAndView(HOME_VIEW_NAME);
        modelAndView.addObject(ERROR_KEY, exception.getMessage());
        return modelAndView;
    }

    @ExceptionHandler({CustomAuthenticationException.class})
    public ModelAndView handleIncorrectCredentials(CustomAuthenticationException exception) {
        ModelAndView modelAndView = new ModelAndView(LOGIN_VIEW_NAME);
        modelAndView.addObject(ERROR_KEY, exception.getMessage());
        return modelAndView;
    }

}

