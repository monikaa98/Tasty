package project.tastyfood.service;


import project.tastyfood.model.entity.Restaurant;
import project.tastyfood.model.entity.UserEntity;
import project.tastyfood.model.service.MealServiceModel;
import project.tastyfood.model.service.UserServiceModel;
import project.tastyfood.model.view.MealViewModel;
import project.tastyfood.model.view.UserViewModel;

import java.util.List;

public interface UserService {
    UserViewModel findUser(String email);
    UserViewModel findUserByEmail(String email);
    boolean existsUser(String email);
    UserEntity getOrCreateUser(UserServiceModel userServiceModel);
    void createAndLoginUser(UserServiceModel userServiceModel);
    void loginUser(String email,String password);
    void addMealToUserProduct(MealServiceModel mealServiceModel, String email, Restaurant restaurant);
    List<MealViewModel>getProductBag(String email);
    Double totalPrice(String email);
    void clearBag(String email);
    boolean isAdmin(String email);
    void createAdmin();
    boolean isRestaurateur(String email);
    boolean isUser(String email);












}
