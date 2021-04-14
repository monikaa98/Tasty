package project.tastyfood.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.tastyfood.model.binding.MealAddBindingModel;
import project.tastyfood.model.entity.enums.CategoryName;
import project.tastyfood.model.service.MealServiceModel;
import project.tastyfood.model.service.PictureServiceModel;
import project.tastyfood.service.MealService;
import project.tastyfood.service.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/meal")
public class MealController {
    private final MealService mealService;
    private final ModelMapper modelMapper;
    private final UserService userService;

@Autowired
    public MealController(MealService mealService, ModelMapper modelMapper, UserService userService) {
        this.mealService = mealService;
        this.modelMapper = modelMapper;
        this.userService = userService;
}

    @GetMapping("/add")
    public String addMeal(@RequestParam("restaurant_id")String restaurantId,Model model){
        if(!model.containsAttribute("mealAddBindingModel")){
            model.addAttribute("mealAddBindingModel",new MealAddBindingModel());
            model.addAttribute("restaurantId",restaurantId);
        }

        return "add-meal";
    }

    @PostMapping("/add")
    public String addMealConfirm(@RequestParam("restaurant_id")String id,@RequestParam("picture") MultipartFile file,
                             @Valid @ModelAttribute("mealAddBindingModel")
                                     MealAddBindingModel mealAddBindingModel,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model,Principal principal){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("mealAddBindingModel", mealAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.mealAddBindingModel",
                    bindingResult);

            model.addAttribute("restaurantId",id);
            return "add-meal";
        }
        PictureServiceModel pictureServiceModel =new PictureServiceModel();
        pictureServiceModel.setFile(file);
        this.mealService.addMeal(this.modelMapper.map(mealAddBindingModel,
                MealServiceModel.class),pictureServiceModel,id);
        model.addAttribute("restaurant_id",id);
        model.addAttribute("totalPrice");
        model.addAttribute("salads", mealService
                .findAllMealsByCategoryNameFromThatRestaurant((CategoryName.САЛАТИ),id));
        model.addAttribute("desserts", mealService
                .findAllMealsByCategoryNameFromThatRestaurant((CategoryName.ДЕСЕРТИ),id));
        model.addAttribute("mainDishes", mealService
                .findAllMealsByCategoryNameFromThatRestaurant((CategoryName.ОСНОВНИ_ЯСТИЯ),id));
        model.addAttribute("appetizers", mealService
                .findAllMealsByCategoryNameFromThatRestaurant((CategoryName.ПРЕДЯСТИЯ),id));
        model.addAttribute("soups", mealService
                .findAllMealsByCategoryNameFromThatRestaurant((CategoryName.СУПИ),id));
        model.addAttribute("isRestaurateur",userService.isRestaurateur(principal.getName()));
        model.addAttribute("isUser",userService.isUser(principal.getName()));

        return "meals";
    }

    @GetMapping("restaurant-meals")
    public String mealsInRestaurant(@RequestParam("id")String id, Model model,@AuthenticationPrincipal Principal principal){
        model.addAttribute("restaurant_id",id);
        model.addAttribute("salads", mealService
                .findAllMealsByCategoryNameFromThatRestaurant((CategoryName.САЛАТИ),id));
        model.addAttribute("desserts", mealService
                .findAllMealsByCategoryNameFromThatRestaurant((CategoryName.ДЕСЕРТИ),id));
        model.addAttribute("mainDishes", mealService
                .findAllMealsByCategoryNameFromThatRestaurant((CategoryName.ОСНОВНИ_ЯСТИЯ),id));
        model.addAttribute("appetizers", mealService
                .findAllMealsByCategoryNameFromThatRestaurant((CategoryName.ПРЕДЯСТИЯ),id));
        model.addAttribute("soups", mealService
                .findAllMealsByCategoryNameFromThatRestaurant((CategoryName.СУПИ),id));
        model.addAttribute("isRestaurateur",userService.isRestaurateur(principal.getName()));
        model.addAttribute("isUser",userService.isUser(principal.getName()));

        return "meals";
    }

}
