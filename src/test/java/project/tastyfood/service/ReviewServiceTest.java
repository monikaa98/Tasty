package project.tastyfood.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import project.tastyfood.error.RestaurantNotFoundException;
import project.tastyfood.model.service.ReviewServiceModel;
import project.tastyfood.repository.ReviewRepository;

import javax.transaction.Transactional;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ReviewServiceTest {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ReviewService reviewService;

    @Test(expected = RestaurantNotFoundException.class)
    public void testAddReviewToWorkProperly(){
        ReviewServiceModel reviewServiceModel=new ReviewServiceModel();
        reviewServiceModel.setReview("review");
        reviewServiceModel.setAssessment("4");
        long dbCount=this.reviewRepository.count();
        this.reviewService.addReview(reviewServiceModel,"id");
        Assert.assertEquals(dbCount+1,this.reviewRepository.count());
    }

    @Test(expected = RestaurantNotFoundException.class)
    public void testAddReviewToThrowExceptionWithRestaurant() {
        ReviewServiceModel reviewServiceModel = new ReviewServiceModel();
        reviewServiceModel.setReview("restaurant");
        reviewServiceModel.setAssessment("4");
        reviewServiceModel.setRestaurant_id("id");
        long dbCount = this.reviewRepository.count();
        String restaurantId = reviewServiceModel.getRestaurant_id();
        this.reviewService.addReview(reviewServiceModel, restaurantId);
        Assert.assertEquals(dbCount + 1, this.reviewRepository.count());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddReviewWhenNullThrow() throws Exception {
        reviewService.addReview(null, null);
        Mockito.verify(reviewRepository)
                .save(any());
    }

}
