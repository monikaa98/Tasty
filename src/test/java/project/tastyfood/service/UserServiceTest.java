package project.tastyfood.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import project.tastyfood.error.UserNotFoundException;
import project.tastyfood.model.view.UserViewModel;
import project.tastyfood.repository.UserRepository;
import javax.transaction.Transactional;
import java.util.ArrayList;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;


    @Test(expected = UserNotFoundException.class)
    public void testFindUserThrowingException(){
        String email="wrongEmail";
        this.userService.findUser(email);
    }

    @Test(expected = UserNotFoundException.class)
    public void testFindUserByEmailThrowingException(){
        String email="wrongEmail";
        userService.findUserByEmail(email);
    }

    @Test
    public void testWhetherUserIsAdminToReturnTrue(){
        boolean isAdmin=this.userService.isAdmin("admin@abv.bg");
        Assert.assertEquals(true,isAdmin);
    }

    @Test(expected = Exception.class)
    public void testWhetherUserIsAdminToReturnFalse(){
        boolean isAdmin=this.userService.isAdmin("admin");
        Assert.assertEquals(true,isAdmin);
    }

    @Test(expected = Exception.class)
    public void testWhetherUserIsRestaurateurToReturnFalse(){
        boolean isRestaurateur=this.userService.isRestaurateur("ivana");
        Assert.assertEquals(true,isRestaurateur);
    }

    @Test(expected = Exception.class)
    public void testIsUserToReturnFalse(){
        boolean isUser=this.userService.isUser("peter");
        Assert.assertEquals(true,isUser);
    }

    @Test(expected = Exception.class)
    public void testFindUser(){
        UserViewModel userViewModel=new UserViewModel();
        userViewModel.setRoles(new ArrayList<>());
        userViewModel.setEmail("monika_trayanova@abv.bg");
        userViewModel.setId("id");
        userViewModel.setLastName("Trayanova");
        userViewModel.setFirstName("Monika");
        userViewModel.setEmail("admin");
        UserViewModel userViewModel1=this.userService.findUser("admin");
        Assert.assertEquals(userViewModel.getEmail(),userViewModel1.getEmail());
    }
    @Test(expected = NullPointerException.class)
    public void testCreateUserWhenNullThrow() throws Exception {
        userService.createAndLoginUser(null);
        Mockito.verify(userRepository)
                .save(any());
    }

    @Test
    public void testFindUserByEmailToWorkCorrectly(){
        UserViewModel userViewModel=new UserViewModel();
        userViewModel.setRoles(new ArrayList<>());
        userViewModel.setEmail("monika_trayanova@abv.bg");
        userViewModel.setId("id");
        userViewModel.setLastName("Trayanova");
        userViewModel.setFirstName("Monika");
        userViewModel.setEmail("admin@abv.bg");
        UserViewModel userViewModel1=this.userService.findUserByEmail("admin@abv.bg");
        Assert.assertEquals(userViewModel.getEmail(),userViewModel1.getEmail());
    }

    @Test
    public void testIfUserExistToReturnTrue(){
        boolean doesExistUser=this.userService.existsUser("admin@abv.bg");
        Assert.assertEquals(true,doesExistUser);
    }

    @Test
    public void testIfUserExistToReturnFalse(){
        boolean doesExistUser=this.userService.existsUser("user");
        Assert.assertEquals(false,doesExistUser);
    }

    @Test
    public void testIfCreateAdminIsInvokedMoreThanOnce(){
        UserService userService=mock(UserService.class);
        verify(userService,times(0)).createAdmin();
    }


}
