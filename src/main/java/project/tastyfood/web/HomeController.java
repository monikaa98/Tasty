package project.tastyfood.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import project.tastyfood.model.view.TownViewModel;
import project.tastyfood.service.TownService;
import project.tastyfood.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {
    private final TownService townService;
    private final UserService userService;

    @Autowired
    public HomeController(TownService townService, UserService userService) {
        this.townService = townService;
        this.userService = userService;
    }

    @GetMapping("/")
    public ModelAndView index(ModelAndView modelAndView){
        modelAndView.setViewName("index");
        return modelAndView;
    }
    @GetMapping("/home")
    public ModelAndView home(ModelAndView modelAndView, @AuthenticationPrincipal Principal principal){
        modelAndView.setViewName("home");
        modelAndView.addObject("user",principal);
        List<TownViewModel> towns=this.townService.getAllTowns();
        modelAndView.addObject("towns",towns);
        modelAndView.addObject("isRestaurateur",userService.isRestaurateur(principal.getName()));
        modelAndView.addObject("isAdmin",userService.isAdmin(principal.getName()));
        modelAndView.addObject("isUser",userService.isUser(principal.getName()));
        return modelAndView;
    }

}
