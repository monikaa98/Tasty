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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.tastyfood.error.UserNotFoundException;
import project.tastyfood.model.binding.AnnouncementAddBindingModel;
import project.tastyfood.model.entity.Announcement;
import project.tastyfood.repository.AnnouncementRepository;
import project.tastyfood.web.AnnouncementController;
import javax.transaction.Transactional;
import java.security.Principal;
import java.time.Instant;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Transactional
public class AnnouncementControllerTest {
    @Autowired
    private AnnouncementController announcementController;
    @Mock
    private Model model;
    @Mock
    private Principal principal;
    @Mock
    private RedirectAttributes redirectAttributes;
    @Mock
    private BindingResult bindingResult;
    @Autowired
    private AnnouncementRepository announcementRepository;

    @Test(expected = UserNotFoundException.class)
    public void testFindAll(){
        String view=this.announcementController.announcement(model,principal);
        Assert.assertEquals("announcements",view);
    }
    
    @Test(expected = UserNotFoundException.class)
    public void testNewToWork(){
        String view=this.announcementController.newAnnouncement(model,principal);
        Assert.assertEquals("new",view);
    }
    
    @Test(expected = UserNotFoundException.class)
    public void testNewToForbidAccess(){
        String view=this.announcementController.newAnnouncement(model,principal);
        Assert.assertEquals("redirect:/home",view);
    }
    
    @Test(expected = UserNotFoundException.class)
    public void testSave(){
        AnnouncementAddBindingModel announcementAddBindingModel=new AnnouncementAddBindingModel();
        announcementAddBindingModel.setTitle("mmmmmmmmmmmmmmmm");
        announcementAddBindingModel.setDescription("mmmmmmmmmmmmmmmmmmmmmmmmmm");
        String view=this.announcementController.save(announcementAddBindingModel,bindingResult,redirectAttributes,principal);
        Assert.assertEquals("redirect:/announcements",view);
    }
    
    @Test(expected = UserNotFoundException.class)
    public void testSaveToForbidAccess(){
        AnnouncementAddBindingModel announcementAddBindingModel=new AnnouncementAddBindingModel();
        announcementAddBindingModel.setTitle("vvvvvvvvvvvvvvvvvvvvvv");
        announcementAddBindingModel.setDescription("vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv");
        String view=this.announcementController.save(announcementAddBindingModel,bindingResult,redirectAttributes,principal);
        Assert.assertEquals("redirect:/home",view);
    }
    
    @Test(expected = UserNotFoundException.class)
    public void testDelete(){
        Announcement announcement=new Announcement();
        announcement.setTitle("gggggggggggggggg");
        announcement.setDescription("ggggggggggggggggggggggggggggggggggg");
        announcement.setUpdatedOn(Instant.now());
        announcement.setCreatedOn(Instant.now());
        this.announcementRepository.save(announcement);
        this.announcementController.delete(principal);
        Assert.assertEquals(0,this.announcementRepository.count());
    }

}
