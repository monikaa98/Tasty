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
import project.tastyfood.model.binding.RestaurantAddBindingModel;
import project.tastyfood.model.binding.ReviewAddBindingModel;
import project.tastyfood.model.service.PictureServiceModel;
import project.tastyfood.model.service.RestaurantServiceModel;
import project.tastyfood.model.view.RestaurantViewModel;
import project.tastyfood.service.RestaurantService;
import project.tastyfood.service.UserService;


import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/restaurant")
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final ModelMapper modelMapper;
    private final UserService userService;


    @Autowired
    public RestaurantController(RestaurantService restaurantService, ModelMapper modelMapper, UserService userService) {
        this.restaurantService = restaurantService;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping("/add")
    public String addRestaurant(@RequestParam("town_id") String townId, Model model) {

        if (!model.containsAttribute("restaurantAddBindingModel")) {
            model.addAttribute("restaurantAddBindingModel", new RestaurantAddBindingModel());
            model.addAttribute("townId", townId);

        }
        return "add-restaurant";
    }

    @PostMapping("/add")
    public String addRestaurantConfirm(@RequestParam("town_id") String id, @RequestParam("picture") MultipartFile file,
                             @Valid @ModelAttribute("restaurantAddBindingModel")
                                     RestaurantAddBindingModel restaurantAddBindingModel,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model, @AuthenticationPrincipal Principal principal) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("restaurantAddBindingModel", restaurantAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.restaurantAddBindingModel",
                    bindingResult);
            model.addAttribute("townId", id);
            return "add-restaurant";
        }
        PictureServiceModel pictureServiceModel = new PictureServiceModel();
        pictureServiceModel.setFile(file);
        this.restaurantService.addRestaurant(this.modelMapper.map(restaurantAddBindingModel,
                RestaurantServiceModel.class), pictureServiceModel, principal.getName(), id);
        List<RestaurantViewModel> restaurantViewModelList = this.restaurantService.getAllRestaurantsFromTownById(id);
        model.addAttribute("restaurants", restaurantViewModelList);
        model.addAttribute("town_id", id);
        
        model.addAttribute("user", principal.getName());
        model.addAttribute("isRestaurateur",userService.isRestaurateur(principal.getName()));
        return "restaurants";
    }

    @GetMapping("/restaurants-details")
    public ModelAndView restaurantDetails(Model model, @RequestParam("id") String id, ModelAndView modelAndView,
                                           @AuthenticationPrincipal Principal principal) {
        RestaurantViewModel restaurantViewModel = this.restaurantService.getRestaurantFromTownById(id);
        model.addAttribute("restaurant", restaurantViewModel);
        model.addAttribute("restaurant_id", id);

        RestaurantServiceModel restaurantServiceModel = this.restaurantService.findById(id);
        RestaurantViewModel restaurantViewModel1 = this.modelMapper.map(restaurantServiceModel, RestaurantViewModel.class);

        restaurantViewModel.setPictureUrl(restaurantServiceModel.getPictureUrl());
        modelAndView.addObject("restaurant", restaurantViewModel);
        modelAndView.setViewName("restaurants-details");
        modelAndView.addObject("email", principal.getName());
        modelAndView.addObject("reviewAddBindingModel", new ReviewAddBindingModel());
        model.addAttribute("isUser",userService.isUser(principal.getName()));
        return modelAndView;

    }

    @GetMapping("town-restaurants")
    public String restaurantsInTown(@RequestParam("id") String id, Model model, @AuthenticationPrincipal Principal principal) {
        List<RestaurantViewModel> restaurantViewModelList = this.restaurantService.getAllRestaurantsFromTownById(id);
        model.addAttribute("restaurants", restaurantViewModelList);
        model.addAttribute("town_id", id);
        model.addAttribute("user", principal.getName());
        model.addAttribute("isRestaurateur",userService.isRestaurateur(principal.getName()));
        model.addAttribute("isUser",userService.isUser(principal.getName()));
        return "restaurants";
    }



}