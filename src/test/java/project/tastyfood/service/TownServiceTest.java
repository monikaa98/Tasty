package project.tastyfood.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import project.tastyfood.error.TownNotFoundException;
import project.tastyfood.model.entity.Picture;
import project.tastyfood.model.entity.Town;
import project.tastyfood.model.service.PictureServiceModel;
import project.tastyfood.model.service.TownServiceModel;
import project.tastyfood.model.view.TownViewModel;
import project.tastyfood.repository.PictureRepository;
import project.tastyfood.repository.TownRepository;
import project.tastyfood.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import javax.transaction.Transactional;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TownServiceTest {
    @Autowired
    private TownService townService;
    @MockBean
    private TownRepository townRepository;
    @Autowired
    private PictureRepository pictureRepository;

    @Test(expected = TownNotFoundException.class)
    public void testFindByIdToBeWithWrongIdAndToThrowException(){
        String townId="InvalidId";
        this.townService.findById(townId);
    }

    @Test(expected = NullPointerException.class)
    public void testFindByIdToWorkCorrectly(){
        Picture picture=new Picture();
        picture.setPictureUrl("/pictures/home.html");
        Picture picture1=this.pictureRepository.save(picture);
        Town town=new Town();
        town.setName("Town");
        town.setPicture(picture1);
        Town town1 = this.townRepository.save(town);
        this.townService.findById(town1.getId());
    }

    @Test
    public void testAddTownToToWorkProperly() {
        TownServiceModel townServiceModel = new TownServiceModel();
        townServiceModel.setName("Plovdiv");
        Picture picture = new Picture();
        PictureServiceModel pictureServiceModel = new PictureServiceModel();
        pictureServiceModel.setPicture(picture);
        townServiceModel.setPictureUrl(picture.getPictureUrl());
        long dbCount = this.townRepository.count();
        this.townService.addTown(townServiceModel,pictureServiceModel);
        Assert.assertEquals(dbCount,this.townRepository.count());
    }

    @Test
    public void testGetAllTownsANdToThrowException(){
        this.townService.getAllTowns();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddTownWhenNullThrow() throws Exception {
        townService.addTown(null,null);
        Mockito.verify(townRepository)
                .save(any());
    }

    @Test(expected = NullPointerException.class)
    public void testGetAllTowns() {
        List<Town> towns = new ArrayList<>();
        towns.add(new Town());
        Mockito.when(townRepository.findAll()).thenReturn(towns);
        List<TownViewModel> allTowns = townService.getAllTowns();
        assertEquals(towns.size(),allTowns.size());
    }
}
