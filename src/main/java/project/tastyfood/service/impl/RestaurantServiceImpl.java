package project.tastyfood.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.tastyfood.error.RestaurantNotFoundException;
import project.tastyfood.model.entity.*;
import project.tastyfood.model.service.*;
import project.tastyfood.model.view.RestaurantViewModel;
import project.tastyfood.model.view.UserViewModel;
import project.tastyfood.repository.RestaurantRepository;
import project.tastyfood.service.*;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class RestaurantServiceImpl implements RestaurantService{
    private final RestaurantRepository restaurantRepository;
    private final ModelMapper modelMapper;
    private final PictureService pictureService;
    private final UserService userService;
    private final TownService townService;

@Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, ModelMapper modelMapper, PictureService pictureService, UserService userService, TownService townService) {
        this.restaurantRepository = restaurantRepository;
        this.modelMapper = modelMapper;
        this.pictureService = pictureService;
        this.userService = userService;
        this.townService = townService;
}

    @Override
    public void addRestaurant(RestaurantServiceModel restaurantServiceModel, PictureServiceModel pictureServiceModel,
                              String email,String townId) {
        PictureServiceModel pictureServiceModel1=this.pictureService.
                addPicture(pictureServiceModel);
        Restaurant restaurant = this.modelMapper.map(restaurantServiceModel, Restaurant.class);
        restaurant.setPicture(pictureServiceModel1.getPicture());
        UserViewModel userViewModel=this.userService.findUserByEmail(email);
        UserEntity userEntity=this.modelMapper.map(userViewModel,UserEntity.class);
        restaurant.setUserEntity(userEntity);
        Restaurant restaurantCheck=this.restaurantRepository.findByName(restaurantServiceModel.getName()).orElse(null);
        if(restaurantCheck!=null){
            throw new EntityExistsException(String.format("Restaurant with %s as name, already exists",
                    restaurantServiceModel.getName()));
        }
        TownServiceModel townServiceModel = this.townService.findById(townId);
        Town town = this.modelMapper.map(townServiceModel, Town.class);
        restaurant.setTown(town);
        this.restaurantRepository.saveAndFlush(restaurant);
    }

    @Override
    public RestaurantServiceModel findById(String id) {
        Restaurant restaurant = this.restaurantRepository.findById(id).orElse(null);
        if(restaurant!=null){
            RestaurantServiceModel restaurantServiceModel=this.modelMapper.map(restaurant,RestaurantServiceModel.class);
            restaurantServiceModel.setEmail(restaurant.getUserEntity().getEmail());
            restaurantServiceModel.setPictureUrl(restaurant.getPicture().getPictureUrl());
            return restaurantServiceModel;

        }
        throw new RestaurantNotFoundException(id);

}

    @Override
    public List<RestaurantViewModel> getAllRestaurantsFromTownById(String id) {
        List<Restaurant>restaurants=this.restaurantRepository.findAllByTown_Id(id);
        List<RestaurantViewModel>restaurantViewModels=new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            RestaurantViewModel restaurantViewModel=new RestaurantViewModel();
            restaurantViewModel.setName(restaurant.getName());
            restaurantViewModel.setId(restaurant.getId());
            restaurantViewModel.setPictureUrl(restaurant.getPicture().getPictureUrl());
            restaurantViewModels.add(restaurantViewModel);
        }

        return restaurantViewModels;
    }

    @Override
    public RestaurantViewModel getRestaurantFromTownById(String id) {
        Restaurant restaurant = this.restaurantRepository.findById(id).orElse(null);
        RestaurantViewModel restaurantViewModel=new RestaurantViewModel();
        if(restaurant!=null){
        restaurantViewModel.setName(restaurant.getName());
        restaurantViewModel.setId(restaurant.getId());
        restaurantViewModel.setPictureUrl(restaurant.getPicture().getPictureUrl());
        restaurantViewModel.setAddress(restaurant.getAddress());
        return restaurantViewModel;
        }
        throw new RestaurantNotFoundException(id);
    }

}
