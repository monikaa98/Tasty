package project.tastyfood.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import project.tastyfood.error.MealNotFoundException;
import project.tastyfood.model.entity.Category;
import project.tastyfood.model.entity.Meal;
import project.tastyfood.model.entity.Picture;
import project.tastyfood.model.entity.Restaurant;
import project.tastyfood.model.entity.enums.CategoryName;
import project.tastyfood.model.view.MealViewModel;
import project.tastyfood.repository.MealRepository;
import project.tastyfood.repository.PictureRepository;
import static org.mockito.ArgumentMatchers.any;
import javax.transaction.Transactional;
import java.io.IOException;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class MealServiceTest {
    @Autowired
    private MealService mealService;
    @Autowired
    private PictureRepository pictureRepository;
    @MockBean
    private MealRepository mealRepository;

    @Test(expected = MealNotFoundException.class)
    public void testFindByIdToBeWithWrongIdAndToThrowException(){
        String mealId="InvalidId";
        this.mealService.findById(mealId);
    }

    @Test(expected = MealNotFoundException.class)
    public void testFindByIdToWorkCorrectly(){
        Picture picture=new Picture();
        picture.setPictureUrl("/pictures/meals.html");
        Picture picture1=this.pictureRepository.save(picture);
        Meal meal = new Meal();
        meal.setName("Meal");
        meal.setGrams(350);
        meal.setPicture(picture1);
        meal.setPrice(23.00);
        meal.setIngredients("Ingredients");
        meal.setCategory(new Category());
        Meal meal1 = this.mealRepository.save(meal);
        this.mealService.findById(meal.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddMealWhenNullThrow() throws Exception {
        mealService.addMeal(null,null,null);
        Mockito.verify(mealRepository)
                .save(any());
    }

    @Test
   public void testAddMeal() throws IOException {
        Meal meal = new Meal() ;
           meal.setName("Meal");
           meal.setGrams(350);
           meal.setPrice(45.00);
           meal.setIngredients("Ingredients");
           meal.setRestaurant(new Restaurant());
           meal.setPicture(new Picture());
           meal.setCategory(new Category());
           Mockito.when(mealRepository.save(any())).thenReturn(meal);
    }

    @Test
    public void testGetMealsByCategoryName() {
        Meal meal = new Meal() ;
        meal.setName("Meal");
        meal.setGrams(350);
        meal.setPrice(45.00);
        meal.setIngredients("Ingredients");
        meal.setRestaurant(new Restaurant());
        meal.setPicture(new Picture());
        meal.setCategory(new Category());
    }

    @Test
    public void testGetAllMealFromRestaurantById() {
        MealViewModel mealViewModel = new MealViewModel();
        mealViewModel.setName("Name");
        mealViewModel.setGrams(350);
        mealViewModel.setId("id");
        mealViewModel.setPrice(34.00);
        mealViewModel.setCategory(CategoryName.САЛАТИ);
        mealViewModel.setIngredients("Ingredients");
        mealViewModel.setRestaurantId("id");
        Picture picture=new Picture();
        picture.setPictureUrl("/meals.html");
        mealViewModel.setPictureUrl(picture.getPictureUrl());
    }

    @Test
    public void testBuyMeal(){
        this.mealService.buyMeal("id","monika@abv.bg");
    }

    @Test
    public void testFindAllMealsByCategoryNameFromThatRestaurant(){
    }

}
