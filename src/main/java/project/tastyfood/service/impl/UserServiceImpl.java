package project.tastyfood.service.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.tastyfood.error.PasswordNotProvidedException;
import project.tastyfood.error.UserNotFoundException;
import project.tastyfood.model.entity.*;
import project.tastyfood.model.service.*;

import project.tastyfood.model.view.*;

import project.tastyfood.repository.UserRepository;
import project.tastyfood.service.CategoryService;
import project.tastyfood.service.PictureService;
import project.tastyfood.service.UserService;
import javax.transaction.Transactional;
import java.util.*;


@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final PictureService pictureService;
    private final CategoryService categoryService;


    private static final Logger LOGGER= LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper,
                           PasswordEncoder passwordEncoder, @Qualifier("userDetailsServiceImpl")
                                   UserDetailsService userDetailsService, PictureService pictureService, CategoryService categoryService) {
        this.userRepository = userRepository;
        this.modelMapper=modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.pictureService = pictureService;
        this.categoryService = categoryService;
    }

    @Override
    public boolean existsUser(String username) {
        Objects.requireNonNull(username);
        return userRepository.findByEmail(username).isPresent();
    }

    @Override
    public UserEntity getOrCreateUser(UserServiceModel userServiceModel) {
        Objects.requireNonNull(userServiceModel.getPassword());
        Optional<UserEntity> userEntityOpt=userRepository.findByEmail(userServiceModel.getEmail());
        return userEntityOpt.orElseGet(()->createUser(userServiceModel));
    }

    @Override
    public void createAndLoginUser(UserServiceModel userServiceModel) {
        UserEntity newUser=createUser(userServiceModel);
        UserDetails userDetails=userDetailsService.loadUserByUsername(newUser.getEmail());
        Authentication authentication=new UsernamePasswordAuthenticationToken(userDetails,userServiceModel.getPassword(),
                userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public void loginUser(String username,String password) {
        UserDetails userDetails=userDetailsService.loadUserByUsername(username);
        Authentication authentication=new UsernamePasswordAuthenticationToken(userDetails,password,
                userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

    }

    @Override
    public void addMealToUserProduct(MealServiceModel mealServiceModel, String email,Restaurant restaurant) {
        UserEntity userEntity=this.userRepository.findByEmail(email).orElse(null);
        if(userEntity!=null){
            List<Meal>bag=new ArrayList<>();
            bag=userEntity.getBag();
            Meal meal=this.modelMapper.map(mealServiceModel,Meal.class);
            PictureServiceModel pictureServiceModel=this.pictureService.getPicture(mealServiceModel.getPictureUrl());
            meal.setPicture(pictureServiceModel.getPicture());
            meal.setCategory(categoryService.findByName(mealServiceModel.getCategory()));
            meal.setRestaurant(restaurant);
            bag.add(meal);
            userEntity.setBag(bag);
            this.userRepository.save(userEntity);
        }


    }

    @Override
    public List<MealViewModel> getProductBag(String email) {
        UserEntity userEntity=this.userRepository.findByEmail(email).orElse(null);

        if(userEntity!=null){
            List<Meal>bag=userEntity.getBag();
            List<MealViewModel>mealViewModels=new ArrayList<>();
            for(Meal meal : bag){
                MealViewModel mealViewModel=this.modelMapper.map(meal,MealViewModel.class);
                mealViewModel.setPictureUrl(meal.getPicture().getPictureUrl());

                mealViewModel.setRestaurant(meal.getRestaurant());
                mealViewModels.add(mealViewModel);
            }
            return mealViewModels;
        }

        throw new UserNotFoundException(email);
    }

    @Override
    public Double totalPrice(String email) {
        UserEntity userEntity=this.userRepository.findByEmail(email).orElse(null);
        if(userEntity!=null){
            List<Meal>bag=userEntity.getBag();

            double totalPrice=0;
            for(Meal meal : bag){
                totalPrice += meal.getPrice();
            }


            return totalPrice;
        }

        throw new UserNotFoundException(email);

    }

    @Override
    public void clearBag(String email) {
        UserEntity userEntity=this.userRepository.findByEmail(email).orElse(null);
        if(userEntity!=null){
            List<Meal>bag=userEntity.getBag();
            userEntity.setBag(new ArrayList<>());
            List<Meal>products=userEntity.getProducts();
            for (Meal meal : bag) {
                products.add(meal);
            }
            userEntity.setProducts(products);
            this.userRepository.save(userEntity);
        }else{
            throw new UserNotFoundException("User not found!");
        }
    }

    @Override
    public boolean isAdmin(String email) {
        List<String>roles=new ArrayList<>();
        UserEntity userEntity=this.userRepository.findByEmail(email).orElse(null);
        if(userEntity==null){
            throw new UserNotFoundException(email);
        }
        for (RoleEntity role : userEntity.getRoles()) {
            roles.add(role.getRole());
        }
        if(roles.contains("ADMIN")){
            return true;
        }
        return false;
    }

    @Override
    public void createAdmin() {
        if(this.userRepository.count()==0){
            UserEntity userEntity=new UserEntity();
            userEntity.setEmail("admin@abv.bg");
            userEntity.setPassword("admin");
            List<RoleEntity> roles = new ArrayList<>();
            RoleEntity roleEntity=new RoleEntity();
            roleEntity.setRole("ADMIN");
            roles.add(roleEntity);
            userEntity.setRoles(roles);
            userEntity.setFirstName("Admin");
            userEntity.setLastName("Admin");
            userEntity.setRoleName("Admin");
            this.userRepository.saveAndFlush(userEntity);
        }
    }

    @Override
    public boolean isRestaurateur(String email) {

        List<String>roles=new ArrayList<>();
        UserEntity userEntity=this.userRepository.findByEmail(email).orElse(null);
        if(userEntity==null){
            throw new UserNotFoundException(email);
        }
        for (RoleEntity role : userEntity.getRoles()) {
            roles.add(role.getRole());
        }
        if(roles.contains("РЕСТОРАНТЬОР")){
            return true;
        }
        return false;
    }

    @Override
    public boolean isUser(String email) {
        List<String>roles=new ArrayList<>();
        UserEntity userEntity=this.userRepository.findByEmail(email).orElse(null);
        if(userEntity==null){
            throw new UserNotFoundException(email);
        }
        for (RoleEntity role : userEntity.getRoles()) {
            roles.add(role.getRole());
        }
        if(roles.contains("ПОТРЕБИТЕЛ")){
            return true;
        }
        return false;
    }

    @Override
    public UserViewModel findUser(String email) {
        UserEntity userEntity =this.userRepository.findByEmail(email).orElse(null);
        UserViewModel userViewModel=new UserViewModel();
        if(userEntity ==null){
            throw new UserNotFoundException(email);
        }else{
            userViewModel=this.modelMapper.map(userEntity,UserViewModel.class);
            List<RoleViewModel>roles=new ArrayList<>();
            for (RoleEntity role : userEntity.getRoles()) {
                RoleViewModel roleViewModel=this.modelMapper.map(role,RoleViewModel.class);
                roles.add(roleViewModel);
            }
            userViewModel.setRoles(roles);
        }
        return userViewModel;
    }

    @Override
    public UserViewModel findUserByEmail(String username) {
        UserEntity userEntity=this.userRepository.findByEmail(username).orElse(null);
        if(userEntity!=null){
            UserViewModel userViewModel=this.modelMapper.map(userEntity,UserViewModel.class);
            userViewModel.setEmail(userEntity.getEmail());
            return userViewModel;
        }
        throw new UserNotFoundException(username);
    }

    private UserEntity createUser(UserServiceModel userServiceModel){
        UserEntity userEntity=new UserEntity();
        LOGGER.info("Creating a new user with email [GDPR].");
        userEntity=this.createUserWithRoles(userServiceModel,userServiceModel.getRoleName());
        return userRepository.save(userEntity);
    }
    private UserEntity createUserWithRoles(UserServiceModel userServiceModel,String role){
        UserEntity userEntity = this.modelMapper.map(userServiceModel, UserEntity.class);


        if(userServiceModel.getPassword()!=null){
            userEntity.setPassword(passwordEncoder.encode(userServiceModel.getPassword()));
        }else{
            throw new PasswordNotProvidedException();
        }
        RoleEntity userRole=new RoleEntity();
        userRole.setRole(role);
        userEntity.setRoles(List.of(userRole));
        return userEntity;
    }

}

