package project.tastyfood.web;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;

import java.security.Principal;

public abstract class CustomErrorController implements ErrorController {
    public String getErrorPath(Model model, @AuthenticationPrincipal Principal principal) {
        model.addAttribute("username",principal.getName());
        return "/error";
    }
}
