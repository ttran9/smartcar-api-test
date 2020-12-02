package tran.example.smartcartest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * A controller to display an error page/template with an error message.
 */
@Controller
public class ErrorController {

    @GetMapping("/error")
    public String getError(Model model, Exception ex) {
        System.out.println("error is: " + ex.getMessage());
        model.addAttribute("error", ex.getMessage());
        return "error";
    }
}
