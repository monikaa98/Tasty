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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.tastyfood.error.UserNotFoundException;
import project.tastyfood.model.binding.UserLoginBindingModel;
import project.tastyfood.model.binding.UserRegisterBindingModel;
import project.tastyfood.model.entity.UserEntity;
import project.tastyfood.repository.UserRepository;
import project.tastyfood.web.UserController;


import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Transactional

public class UserControllerTest {

    @Autowired
    private UserController userController;
    @Autowired
    private HttpSession httpSession;
    @Mock
    private Principal principal;
    @Mock
    private Model model;
    @Mock
    private BindingResult bindingResult;
    @Mock
    private RedirectAttributes redirectAttributes;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testRegister(){
        String view=this.userController.register(model);
        Assert.assertEquals("register",view);
    }

    @Test
    public void testLogin(){
        String view=this.userController.login(model);
        Assert.assertEquals("login",view);
    }

    @Test
    public void testRegisterConfirm(){
        UserRegisterBindingModel userRegisterBindingModel=new UserRegisterBindingModel();
        userRegisterBindingModel.setEmail("email@abv.bg");
        userRegisterBindingModel.setPassword("1234");
        userRegisterBindingModel.setConfirmPassword("1234");
        userRegisterBindingModel.setRoleName("User");
        userRegisterBindingModel.setFirstName("User");
        userRegisterBindingModel.setLastName("User");
        String view=this.userController.registerConfirm(
                userRegisterBindingModel,bindingResult,httpSession,redirectAttributes,model);
        Assert.assertEquals("index",view);
    }

    @Test
    public void testRegisterConfirmToHaveAnError(){
        UserRegisterBindingModel userRegisterBindingModel=new UserRegisterBindingModel();
        userRegisterBindingModel.setEmail("email@abv.bg");
        userRegisterBindingModel.setPassword("1234");
        userRegisterBindingModel.setConfirmPassword("123456");
        userRegisterBindingModel.setRoleName("User");
        userRegisterBindingModel.setFirstName("User");
        userRegisterBindingModel.setLastName("User");
        String view=this.userController.registerConfirm(
                userRegisterBindingModel,bindingResult,httpSession,redirectAttributes,model);
        Assert.assertEquals("register",view);
    }

    @Test(expected = UserNotFoundException.class)
    public void testLoginConfirm(){
        UserEntity userEntity=new UserEntity();
        userEntity.setEmail("email@abv.bg");
        userEntity.setPassword("1234");
        userEntity.setRoleName("User");
        userEntity.setLastName("User");
        userEntity.setFirstName("User");
        userEntity.setRoles(new ArrayList<>());
        UserEntity userEntity1=this.userRepository.save(userEntity);
        UserLoginBindingModel userLoginBindingModel=new UserLoginBindingModel();
        userLoginBindingModel.setPassword(userEntity1.getPassword());
        userLoginBindingModel.setEmail(userEntity1.getPassword());
        String view=this.userController.loginConfirm(userLoginBindingModel,bindingResult,redirectAttributes);
    }

    @Test
    public void testLogout(){
        String view=this.userController.logout(httpSession);
        Assert.assertEquals("redirect:/",view);
    }

    @Test(expected = UserNotFoundException.class)
    public void testProfile(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView =this.userController.profile(modelAndView,principal);
        Assert.assertEquals("profile",modelAndView.getViewName());
    }

    @Test(expected = Exception.class)
    public void testProfileInfo(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView=this.userController.profileInfo("admin",modelAndView,principal);
        Assert.assertEquals("user-information",modelAndView.getViewName());
    }

    @Test(expected = Exception.class)
    public void testShoppingBag(){
        String view=this.userController.shoppingBag(model,principal);
        Assert.assertEquals("shopping-bag",view);
    }

}
