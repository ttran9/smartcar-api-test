package tran.example.smartcartest.utility.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

/**
 * Defines methods used across multiple controllers
 */
public class ControllerHelper {

    /**
     * Sets and adds the specified attribute to the view's Model object.
     * @param model The UI object which will hold view information
     * @param attributeName The name of the attribute to be added to the model.
     * @param value The value of the 'attributeName' key.
     */
    public static void addAttribute(Model model, String attributeName, String value) {
        model.addAttribute(attributeName, value);
    }

    public static String getLoggedInUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
