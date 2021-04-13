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
import project.tastyfood.model.binding.TownAddBindingModel;
import project.tastyfood.web.TownController;



import javax.transaction.Transactional;



@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Transactional


public class TownControllerTest {
    @Autowired
    private TownController townController;
    @Mock
    private BindingResult bindingResult;
    @Mock
    private Model model;
    @Mock
    private MultipartFile file;
    @Mock
    private RedirectAttributes redirectAttributes;


    @Test
    public void testAddTown(){
        String view=this.townController.addTown(model);
        Assert.assertEquals("add-town",view);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddTownConfirm(){
        TownAddBindingModel townAddBindingModel = new TownAddBindingModel();
        townAddBindingModel.setName("Varna");
        String view=this.townController.addTownConfirm(townAddBindingModel,bindingResult,file,redirectAttributes);
        Assert.assertEquals("redirect:/home",view);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddTownConfirmToHaveAnError(){
        TownAddBindingModel townAddBindingModel = new TownAddBindingModel();
        townAddBindingModel.setName("");
        String view=this.townController.addTownConfirm(townAddBindingModel,bindingResult,file,redirectAttributes);
        Assert.assertEquals("redirect:/home",view);
    }

}
