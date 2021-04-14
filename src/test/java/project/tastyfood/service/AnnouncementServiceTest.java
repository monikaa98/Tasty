package project.tastyfood.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import project.tastyfood.model.binding.AnnouncementAddBindingModel;
import project.tastyfood.model.entity.Announcement;
import project.tastyfood.repository.AnnouncementRepository;
import javax.transaction.Transactional;
import java.time.Instant;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class AnnouncementServiceTest {
    @Autowired
    private AnnouncementService announcementService;
    @Autowired
    AnnouncementRepository announcementRepository;

    @Test
    public void testFindAll(){
        long dbSize=this.announcementRepository.count();
        Announcement announcement=new Announcement();
        announcement.setCreatedOn(Instant.now());
        announcement.setUpdatedOn(Instant.now());
        announcement.setDescription("fjjjjjjjjjjjjjjjjjj");
        announcement.setTitle("fkkfkkkkk");
        this.announcementRepository.save(announcement);
        Assertions.assertEquals(dbSize+1,this.announcementService.findAll().size());
    }
    
    @Test
    public void testCreateOrUpdate(){
        long dbSize=this.announcementRepository.count();
        AnnouncementAddBindingModel announcement=new AnnouncementAddBindingModel();
        announcement.setCreatedOn(Instant.now());
        announcement.setUpdatedOn(Instant.now());
        announcement.setDescription("vvvvvvvvvvvvvvvvv");
        announcement.setTitle("vvvvvvvvv");
        this.announcementService.createOrUpdateAnnouncement(announcement);
        Assert.assertEquals(dbSize+1,this.announcementRepository.count());
    }
    
    @Test
    public void testDelete(){
        this.announcementService.deleteAll();
        Assert.assertEquals(0,this.announcementRepository.count());
    }

}
