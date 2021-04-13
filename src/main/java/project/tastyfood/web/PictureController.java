package project.tastyfood.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.tastyfood.model.binding.PictureAddBindingModel;
import project.tastyfood.model.service.PictureServiceModel;
import project.tastyfood.service.UserService;


import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/picture")
public class PictureController {
    private final ModelMapper modelMapper;

    @Autowired
    public PictureController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

    }

    @GetMapping("/add")
    public String add(@RequestParam("id")String id, Model model, @AuthenticationPrincipal Principal principal ){
        if(!model.containsAttribute("pictureAddBindingModel")){
            model.addAttribute("pictureAddBindingModel",new PictureAddBindingModel());
            model.addAttribute("id",id);
            model.addAttribute("email",principal.getName());
        }
        return "add-town";
    }

    @PostMapping("/add")
    public ModelAndView addConfirm(@RequestParam("id")String id, @RequestParam("picture") MultipartFile file, @Valid
    @ModelAttribute("pictureAddBindingModel")
            PictureAddBindingModel pictureAddBindingModel,
                                   BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                   ModelAndView modelAndView,Principal principal){
        if(bindingResult.hasErrors()){

            redirectAttributes.addFlashAttribute("pictureAddBindingModel",pictureAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.pictureAddBindingModel",
                    bindingResult);
            modelAndView.setViewName("add-town");
        }else{
            PictureServiceModel pictureServiceModel=this.modelMapper.map(pictureAddBindingModel, PictureServiceModel.class);
            pictureServiceModel.setFile(file);

            modelAndView.setViewName("redirect:/home");

        }
        return modelAndView;
    }

}
