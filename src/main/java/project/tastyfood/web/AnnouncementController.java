package project.tastyfood.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.tastyfood.model.binding.AnnouncementAddBindingModel;
import project.tastyfood.service.AnnouncementService;
import project.tastyfood.service.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/announcements")
public class AnnouncementController {
    private final AnnouncementService announcementService;
    private final UserService userService;
    @Autowired
    public AnnouncementController(AnnouncementService announcementService, UserService userService) {
        this.announcementService = announcementService;
        this.userService = userService;
    }
    @GetMapping
    public String announcement(Model model,@AuthenticationPrincipal Principal principal){
        model.addAttribute("email",principal.getName());
        model.addAttribute("announcements",announcementService.findAll());
        model.addAttribute("isAdmin",this.userService.isAdmin(principal.getName()));
        return "announcements";
    }

    @GetMapping("/new")
    public String newAnnouncement(Model model,@AuthenticationPrincipal Principal principal){
        if(this.userService.isAdmin(principal.getName())){
            AnnouncementAddBindingModel announcementAddBindingModel;
            if(model.containsAttribute("announcementAddBindingModel")){
                announcementAddBindingModel=(AnnouncementAddBindingModel)model.
                        getAttribute("announcementAddBindingModel");
            }else{
                announcementAddBindingModel=new AnnouncementAddBindingModel();
            }
            model.addAttribute("announcementAddBindingModel",announcementAddBindingModel);
            model.addAttribute("email",principal.getName());
            return "new";
        }
        return "redirect:/home";
    }
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("announcementAddBindingModel")
                               AnnouncementAddBindingModel announcementAddBindingModel,
                       BindingResult bindingResult, RedirectAttributes redirectAttributes,
                       @AuthenticationPrincipal Principal principal){
        if(this.userService.isAdmin(principal.getName())){
            if(bindingResult.hasErrors()){
                redirectAttributes.addFlashAttribute("announcementAddBindingModel",announcementAddBindingModel);
                redirectAttributes.
                        addFlashAttribute("org.springframework.validation.BindingResult.announcementAddBindingModel"
                                ,bindingResult);
                return "redirect:/announcements/new";
            }
            announcementService.createOrUpdateAnnouncement(announcementAddBindingModel);
            return "redirect:/announcements";
        }
        return "redirect:/home";
    }
    @GetMapping("/delete")
    public String delete(@AuthenticationPrincipal Principal principal){
        if(this.userService.isAdmin(principal.getName())){
            announcementService.deleteAll();
            return "redirect:/announcements";
        }
        return "redirect:/home";
    }

}