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
import project.tastyfood.model.binding.AddressAddBindingModel;
import project.tastyfood.repository.OrderRepository;
import project.tastyfood.web.OrderController;

import javax.transaction.Transactional;
import java.security.Principal;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Transactional
public class OrderControllerTest {

    @Autowired
    private OrderController orderController;
    @Mock
    private Principal principal;
    @Mock
    private Model model;
    @Mock
    private BindingResult bindingResult;
    @Mock
    private RedirectAttributes redirectAttributes;

    @Test(expected = UserNotFoundException.class)
    public void testShoppingBag(){
        String view=this.orderController.shoppingBag(model,"id",principal);
        Assert.assertEquals("meals",view);
    }

    @Test
    public void testAddAddress(){
        String view=this.orderController.addAddress(model);
        Assert.assertEquals("add-address",view);
    }

    @Test(expected = UserNotFoundException.class)
    public void testAddAddressConfirm(){
        AddressAddBindingModel addressAddBindingModel = new AddressAddBindingModel();
        addressAddBindingModel.setAddress("gggggggggggggggggg");
        addressAddBindingModel.setPhoneNumber("0886545123");
        String view=this.orderController.addAddressConfirm(addressAddBindingModel,bindingResult,redirectAttributes,principal);
        Assert.assertEquals("successful-order",view);
    }

    @Test
    public void testOrders(){
        String view=this.orderController.orders(model,principal);
        Assert.assertEquals("orders",view);
    }

}
