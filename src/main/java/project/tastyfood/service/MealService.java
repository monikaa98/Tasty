package project.tastyfood.service;

import project.tastyfood.model.entity.enums.CategoryName;
import project.tastyfood.model.service.MealServiceModel;
import project.tastyfood.model.service.PictureServiceModel;
import project.tastyfood.model.view.MealViewModel;


import java.util.List;

public interface MealService {
    void addMeal(MealServiceModel mealServiceModel, PictureServiceModel pictureServiceModel, String restaurantId);
    MealServiceModel findById(String id);
    MealViewModel buyMeal(String id, String email);
    List<MealViewModel> findAllMealsByCategoryNameFromThatRestaurant(CategoryName categoryName,String restaurantId);

}
