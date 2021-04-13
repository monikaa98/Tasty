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
import project.tastyfood.web.PictureController;


import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.security.Principal;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Transactional

public class PictureControllerTest {

    @Autowired
    private PictureController pictureController;
    @Mock
    private Principal principal;
    @Mock
    private Model model;



    @Test
    public void testAddMethod(){
        String view=this.pictureController.add("id",model,principal);
        Assert.assertEquals("add-town",view);
    }


}
