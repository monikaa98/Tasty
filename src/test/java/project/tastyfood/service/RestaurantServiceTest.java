package project.tastyfood.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import project.tastyfood.error.RestaurantNotFoundException;
import project.tastyfood.model.entity.Picture;
import project.tastyfood.model.entity.Restaurant;
import project.tastyfood.repository.PictureRepository;
import project.tastyfood.repository.RestaurantRepository;



import javax.transaction.Transactional;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Transactional
public class RestaurantServiceTest {
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private PictureRepository pictureRepository;
    @MockBean
    private RestaurantRepository restaurantRepository;

    @Test(expected = RestaurantNotFoundException.class)
    public void testFindByIdToBeWithWrongIdAndToThrowException(){
        String restaurantId="InvalidId";
        this.restaurantService.findById(restaurantId);
    }

    @Test(expected = NullPointerException.class)
    public void testFindByIdToWorkCorrectly(){
        Picture picture=new Picture();
        picture.setPictureUrl("/pictures/restaurants.html");
        Picture picture1=this.pictureRepository.save(picture);
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Restaurant");
        restaurant.setPicture(picture1);
        restaurant.setAddress("Description");
        Restaurant restaurant1 = this.restaurantRepository.save(restaurant);
        this.restaurantService.findById(restaurant1.getId());
    }

    @Test(expected = RestaurantNotFoundException.class)
    public void testGetAllRestaurantFromThatTownToBeWithWrongIdAndToThrowException(){
        String restaurantId="InvalidId";
        this.restaurantService.getRestaurantFromTownById(restaurantId);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddRestaurantWhenNullThrow() throws Exception {
        restaurantService.addRestaurant(null,null,null,null);
        Mockito.verify(restaurantRepository)
                .save(any());
    }

    @Test
    public void testGetAllRestaurantsByTownId() {
    }

}
