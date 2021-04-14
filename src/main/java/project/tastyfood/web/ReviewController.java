package project.tastyfood.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.tastyfood.model.binding.ReviewAddBindingModel;
import project.tastyfood.model.service.ReviewServiceModel;
import project.tastyfood.model.view.ReviewViewModel;
import project.tastyfood.service.ReviewService;
import project.tastyfood.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;
    private final ModelMapper modelMapper;
    private final UserService userService;

   @Autowired
    public ReviewController(ReviewService reviewService, ModelMapper modelMapper, UserService userService) {
        this.reviewService = reviewService;
        this.modelMapper = modelMapper;
        this.userService = userService;
   }

    @GetMapping("/add")
    public String addReview(@RequestParam("restaurant_id")String restaurantId, Model model){
        if(!model.containsAttribute("reviewAddBindingModel")){
            model.addAttribute("reviewAddBindingModel",new ReviewAddBindingModel());
            model.addAttribute("restaurantId",restaurantId);

        }

        return "add-review";
    }

    @PostMapping("/add")
    public String addReviewConfirm(@RequestParam("restaurant_id")String id,
                             @Valid @ModelAttribute("reviewAddBindingModel")
                                     ReviewAddBindingModel reviewAddBindingModel,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model,@AuthenticationPrincipal Principal principal){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("reviewAddBindingModel", reviewAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.reviewAddBindingModel",
                    bindingResult);

            model.addAttribute("restaurantId",id);
            return "add-review";
        }


        List<ReviewViewModel> reviewViewModelList =this.reviewService.getAllReviewFromRestaurantById(id);
        model.addAttribute("review", reviewViewModelList);
        model.addAttribute("restaurant_id",id);
        ReviewServiceModel reviewServiceModel=this.modelMapper.map(reviewAddBindingModel,
                ReviewServiceModel.class);
        reviewServiceModel.setEmail(principal.getName());

        this.reviewService.addReview(reviewServiceModel,id);

        return "redirect:/home";
    }
    @GetMapping("/seeReviews")
    public ModelAndView listOfReviews(@RequestParam("id")String id, ModelAndView modelAndView,Principal principal){
        modelAndView.setViewName("view-reviews");
        List<ReviewViewModel> reviewViewModelList =this.reviewService.getAllReviewFromRestaurantById(id);
        modelAndView.addObject("reviews", reviewViewModelList);
        modelAndView.addObject("id", reviewViewModelList);
        modelAndView.addObject("isUser",userService.isUser(principal.getName()));
        return modelAndView;
    }


}
