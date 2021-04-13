package project.tastyfood.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.tastyfood.model.binding.TownAddBindingModel;

import project.tastyfood.model.service.PictureServiceModel;
import project.tastyfood.model.service.TownServiceModel;
import project.tastyfood.service.TownService;



import javax.validation.Valid;


@Controller
@RequestMapping("/town")
public class TownController {
    private final TownService townService;
    private final ModelMapper modelMapper;



    @Autowired
    public TownController(TownService townService, ModelMapper modelMapper) {
        this.townService = townService;
        this.modelMapper = modelMapper;


    }

    @GetMapping("/add")
    public String addTown(Model model) {
        if (!model.containsAttribute("townAddBindingModel")) {
            model.addAttribute("townAddBindingModel", new TownAddBindingModel());
        }
        return "add-town";
    }


    @PostMapping("/add")
    public String addTownConfirm(@Valid @ModelAttribute("townAddBindingModel")
            TownAddBindingModel townAddBindingModel, BindingResult bindingResult,
                             @RequestParam("picture") MultipartFile file,
                             RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("townAddBindingModel",townAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.townAddBindingModel"
                    ,bindingResult);
            return "add-town";
        }
        PictureServiceModel pictureServiceModel=new PictureServiceModel();
        pictureServiceModel.setFile(file);
        this.townService.addTown(this.modelMapper.map(townAddBindingModel,
                TownServiceModel.class),pictureServiceModel);

        return "redirect:/home";
    }

}
