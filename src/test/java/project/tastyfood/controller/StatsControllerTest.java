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
import project.tastyfood.error.UserNotFoundException;
import project.tastyfood.web.StatsController;

import javax.transaction.Transactional;
import java.security.Principal;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Transactional
public class StatsControllerTest {

    @Autowired
    private StatsController statsController;
    @Mock
    private Model model;
    @Mock
    private Principal principal;

    @Test(expected = UserNotFoundException.class)
    public void testStats(){
        String view=this.statsController.stats(model,principal);
        Assert.assertEquals("stats",view);
    }

}