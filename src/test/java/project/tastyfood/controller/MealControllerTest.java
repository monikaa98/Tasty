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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.tastyfood.error.UserNotFoundException;
import project.tastyfood.model.binding.MealAddBindingModel;
import project.tastyfood.model.entity.enums.CategoryName;
import project.tastyfood.repository.MealRepository;
import project.tastyfood.web.MealController;

import javax.transaction.Transactional;
import java.security.Principal;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Transactional
public class MealControllerTest {
    @Autowired
    private MealController mealController;
    @Mock
    private Principal principal;
    @Mock
    private Model model;
    @Mock
    private BindingResult bindingResult;
    @Mock
    private MultipartFile file;
    @Mock
    private RedirectAttributes redirectAttributes;

    @Test
    public void testAddMeal(){
        String view=this.mealController.addMeal("id",model);
        Assert.assertEquals("add-meal",view);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddMealConfirm(){
        MealAddBindingModel mealAddBindingModel = new MealAddBindingModel();
        mealAddBindingModel.setName("Mealll");
        mealAddBindingModel.setGrams(450);
        mealAddBindingModel.setPrice(45.00);
        mealAddBindingModel.setIngredients("gggggggggggg");
        mealAddBindingModel.setCategory(CategoryName.САЛАТИ);
        String view=this.mealController.addMealConfirm("id",file,mealAddBindingModel,bindingResult,redirectAttributes,model,principal);
        Assert.assertEquals("meals",view);
    }

    @Test(expected = UserNotFoundException.class)
    public void testMealsInRestaurant(){
        String view=this.mealController.mealsInRestaurant("id",model,principal);
        Assert.assertEquals("meals",view);
    }
}
