package project.tastyfood.web;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project.tastyfood.service.StatsService;
import project.tastyfood.service.UserService;

import java.security.Principal;


@Controller
public class StatsController {
    private final StatsService statsService;
    private final UserService userService;

    public StatsController(StatsService statsService, UserService userService){
        this.statsService=statsService;
        this.userService = userService;
    }
    @GetMapping("/stats")
    public String stats(Model model, @AuthenticationPrincipal Principal principal){

        if(this.userService.isAdmin(principal.getName())){
            model.addAttribute("requestCount",statsService.getRequestCount());
            model.addAttribute("startedOn",statsService.getStartedOn());
            return "stats";
        }else{
            return "redirect:/home";
        }

    }

}