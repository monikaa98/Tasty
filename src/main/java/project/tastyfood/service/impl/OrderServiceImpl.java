package project.tastyfood.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.tastyfood.model.entity.*;
import project.tastyfood.model.service.OrderServiceModel;
import project.tastyfood.model.view.MealViewModel;
import project.tastyfood.model.view.OrderViewModel;
import project.tastyfood.model.view.UserViewModel;
import project.tastyfood.repository.OrderRepository;
import project.tastyfood.service.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper,UserService userService) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public void addAddress(OrderServiceModel orderServiceModel,String email) {
        Order order=this.modelMapper.map(orderServiceModel,Order.class);
        List<MealViewModel> productBag = this.userService.getProductBag(email);
        List<Meal> meals =new ArrayList<>();
        double totalPrice=0;
        for (MealViewModel mealViewModel : productBag) {
            Meal meal =this.modelMapper.map(mealViewModel, Meal.class);
            meal.setRestaurant(mealViewModel.getRestaurant());
            totalPrice+= mealViewModel.getPrice();
            meals.add(meal);
        }
        order.setMealList(meals);
        order.setStatus("В процес на изпълнение");
        order.setTotalPrice(totalPrice);
        UserViewModel userByEmail = this.userService.findUserByEmail(email);
        UserEntity userEntity=this.modelMapper.map(userByEmail,UserEntity.class);
        order.setUserEntity(userEntity);
        this.orderRepository.saveAndFlush(order);
        this.userService.clearBag(email);
    }

    @Override
    public List<OrderViewModel> getAllOrders(String email) {
        List<Order>orders=this.orderRepository.findAllByUserEntity_Email(email);
        List<OrderViewModel>ordersViewModels=new ArrayList<>();
        for (Order order : orders) {
            OrderViewModel orderViewModel=new OrderViewModel();
            orderViewModel.setAddress(order.getAddress());
            orderViewModel.setPhoneNumber(order.getPhoneNumber());
            orderViewModel.setTotalPrice(order.getTotalPrice());
            orderViewModel.setStatus(order.getStatus());
            orderViewModel.setId(order.getId());
            String products="";
            int i=0;
            for (Meal meal : order.getMealList()) {
                if(i<order.getMealList().size()-1){
                    products += meal.getName()+", ";
                }else{
                    products += meal.getName()+" ";
                }
                i++;
            }
            orderViewModel.setProduct(products);
            ordersViewModels.add(orderViewModel);

        }
        return ordersViewModels;
    }
}
