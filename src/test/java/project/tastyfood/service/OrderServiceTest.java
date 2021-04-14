package project.tastyfood.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import project.tastyfood.error.UserNotFoundException;
import project.tastyfood.model.entity.Order;
import project.tastyfood.model.service.OrderServiceModel;
import project.tastyfood.model.service.UserServiceModel;
import project.tastyfood.model.view.OrderViewModel;
import project.tastyfood.repository.OrderRepository;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;
    @MockBean
    private OrderRepository orderRepository;

    @Test(expected = IllegalArgumentException.class)
    public void testAddAddressWhenNullThrow() throws Exception {
        orderService.addAddress(null,null);
        Mockito.verify(orderRepository)
                .save(any());
    }

    @Test(expected = UserNotFoundException.class)
    public void testAddAddressToWorkCorrectly(){
        OrderServiceModel orderServiceModel = new OrderServiceModel();
        orderServiceModel.setAddress("Address");
        orderServiceModel.setPhoneNumber("0885676456");
        long dbSize=this.orderRepository.count();
        UserServiceModel userServiceModel = new UserServiceModel();
        userServiceModel.setEmail("monika_trayanova@abv.bg");
        this.orderService.addAddress(orderServiceModel,userServiceModel.getEmail());
        Assert.assertEquals(dbSize+1,this.orderRepository.count());
    }

    @Test
    public  void getAllOrders(){
        List<Order> orders = new ArrayList<>();
        orders.add(new Order());
        Mockito.when(orderRepository.findAll()).thenReturn(orders);
        UserServiceModel userServiceModel = new UserServiceModel();
        userServiceModel.setEmail("monika_trayanova@abv.bg");
        String email=userServiceModel.getEmail();
        List<OrderViewModel> allOrders = orderService.getAllOrders(email);
        assertEquals(0,allOrders.size());
    }

}
