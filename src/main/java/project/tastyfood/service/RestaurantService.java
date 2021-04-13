package project.tastyfood.service;

import project.tastyfood.model.service.PictureServiceModel;
import project.tastyfood.model.service.RestaurantServiceModel;
import project.tastyfood.model.view.RestaurantViewModel;

import java.util.List;

public interface RestaurantService {
    void addRestaurant(RestaurantServiceModel restaurantServiceModel, PictureServiceModel pictureServiceModel,String email,String townId);
    RestaurantServiceModel findById(String id);
    List<RestaurantViewModel>getAllRestaurantsFromTownById(String id);
    RestaurantViewModel getRestaurantFromTownById(String id);








}
