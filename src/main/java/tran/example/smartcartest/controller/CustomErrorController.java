package tran.example.smartcartest.controller;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tran.example.smartcartest.utility.controller.ControllerHelper;

import javax.servlet.http.HttpServletRequest;
import static tran.example.smartcartest.utility.constants.KeyConstants.ERROR_KEY;
import static tran.example.smartcartest.utility.constants.KeyConstants.TITLE_KEY;
import static tran.example.smartcartest.utility.constants.PathConstants.ERROR_PATH;
import static tran.example.smartcartest.utility.constants.ValueConstants.ERROR_TITLE;
import static tran.example.smartcartest.utility.constants.view.ViewConstants.ERROR_VIEW_NAME;


/*
 * A controller to display an error page/template with an error message.
 */
@Controller
public class CustomErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @GetMapping(ERROR_PATH)
    public String getError(Model model, Exception ex, HttpServletRequest request) {
        ControllerHelper.addAttribute(model, TITLE_KEY, ERROR_TITLE);
        String errorMessage = request.getAttribute("javax.servlet.error.status_code") + " (" +
                request.getAttribute("javax.servlet.error.message") + ")";
        ControllerHelper.addAttribute(model, ERROR_KEY, errorMessage);
        return ERROR_VIEW_NAME;
    }
}


