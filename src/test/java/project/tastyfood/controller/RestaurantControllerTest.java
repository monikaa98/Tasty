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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.tastyfood.error.RestaurantNotFoundException;
import project.tastyfood.error.UserNotFoundException;
import project.tastyfood.model.binding.RestaurantAddBindingModel;
import project.tastyfood.web.RestaurantController;

import javax.transaction.Transactional;

import java.security.Principal;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Transactional
public class RestaurantControllerTest {
    @Autowired
    private RestaurantController restaurantController;
    @Mock
    private Principal principal;
    @Mock
    private Model model;
    @Mock
    private MultipartFile file;
    @Mock
    private BindingResult bindingResult;
    @Mock
    private RedirectAttributes redirectAttributes;

    @Test
    public void testAddRestaurant(){
        String view=this.restaurantController.addRestaurant("id",model);
        Assert.assertEquals("add-restaurant",view);
    }

    @Test
    public void testAddRestaurantToHaveAnError(){
        String view=this.restaurantController.addRestaurant(null,model);
        Assert.assertEquals("add-restaurant",view);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddRestaurantConfirm(){
        RestaurantAddBindingModel restaurantAddBindingModel = new RestaurantAddBindingModel();
        restaurantAddBindingModel.setName("gggggg");
        restaurantAddBindingModel.setAddress("jjjjjjjjjjjjjj");
        String view=this.restaurantController.addRestaurantConfirm("id",file,restaurantAddBindingModel,bindingResult,
                redirectAttributes,model,principal);
        Assert.assertEquals("restaurants",view);
    }

    @Test(expected = RestaurantNotFoundException.class)
    public void testRestaurantDetails(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView=this.restaurantController.restaurantDetails(model,"id",modelAndView,principal);
        Assert.assertEquals("restaurants-details",modelAndView);
    }

    @Test(expected = UserNotFoundException.class)
    public void testRestaurantsInTown(){
        String view=this.restaurantController.restaurantsInTown("id",model,principal);
        Assert.assertEquals("restaurants",view);
    }

}
