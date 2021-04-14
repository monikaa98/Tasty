package project.tastyfood.controller;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.tastyfood.error.RestaurantNotFoundException;
import project.tastyfood.error.UserNotFoundException;
import project.tastyfood.model.binding.ReviewAddBindingModel;
import project.tastyfood.web.ReviewController;


import javax.transaction.Transactional;
import java.security.Principal;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Transactional
public class ReviewControllerTest {

    @Autowired
    private ReviewController reviewController;
    @Mock
    private Principal principal;
    @Mock
    private Model model;
    @Mock
    private BindingResult bindingResult;
    @Mock
    private RedirectAttributes redirectAttributes;

    @Test
    public void testAddReview(){
        String view=this.reviewController.addReview("id",model);
        Assert.assertEquals("add-review",view);
    }

    @Test(expected = RestaurantNotFoundException.class)
    public void testAddReviewConfirm(){
        ReviewAddBindingModel reviewAddBindingModel = new ReviewAddBindingModel();
        reviewAddBindingModel.setReview("hhhhhhhh");
        reviewAddBindingModel.setAssessment("4");
        String view=this.reviewController.addReviewConfirm("id",reviewAddBindingModel,
                bindingResult,redirectAttributes,model,principal);
        Assert.assertEquals("redirect:/home",view);
    }

    @Test(expected = UserNotFoundException.class)
    public void testListOfReview(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView=this.reviewController.listOfReviews("id",modelAndView,principal);
    }

}
