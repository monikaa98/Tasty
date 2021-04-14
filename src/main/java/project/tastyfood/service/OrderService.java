package project.tastyfood.service;

import project.tastyfood.model.service.*;
import project.tastyfood.model.view.OrderViewModel;

import java.util.List;

public interface OrderService {
    void addAddress(OrderServiceModel orderServiceModel,String email);
    List<OrderViewModel>getAllOrders(String email);
}
