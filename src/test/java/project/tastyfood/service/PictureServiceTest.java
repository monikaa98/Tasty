package project.tastyfood.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import project.tastyfood.model.service.PictureServiceModel;
import project.tastyfood.repository.PictureRepository;

import javax.transaction.Transactional;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class PictureServiceTest {
    @Autowired
    private PictureService pictureService;
    @Autowired
    private PictureRepository pictureRepository;

    @Test
    public void testAddPictureToWorkCorrectly(){
        PictureServiceModel pictureServiceModel=new PictureServiceModel();
        pictureServiceModel.setFile(null);
        long dbSize=this.pictureRepository.count();
        this.pictureService.addPicture(pictureServiceModel);
        Assert.assertEquals(dbSize+1,this.pictureRepository.count());
    }
}
