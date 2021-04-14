package project.tastyfood.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.ModelAndView;
import project.tastyfood.web.HomeController;
import javax.transaction.Transactional;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Transactional
public class HomeControllerTest {
    @Autowired
    private HomeController homeController;

    @Test
    public void testIndexMethod(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView=this.homeController.index(modelAndView);
        Assert.assertEquals("index",modelAndView.getViewName());
    }
}
