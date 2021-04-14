package project.tastyfood.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.tastyfood.model.entity.Restaurant;
import project.tastyfood.model.entity.Review;
import project.tastyfood.model.service.RestaurantServiceModel;
import project.tastyfood.model.service.ReviewServiceModel;
import project.tastyfood.model.view.ReviewViewModel;
import project.tastyfood.repository.ReviewRepository;
import project.tastyfood.service.RestaurantService;
import project.tastyfood.service.ReviewService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {
    private final ModelMapper modelMapper;
    private final ReviewRepository reviewRepository;
    private final RestaurantService restaurantService;


    public ReviewServiceImpl(ModelMapper modelMapper, ReviewRepository reviewRepository, RestaurantService restaurantService) {
        this.modelMapper = modelMapper;
        this.reviewRepository = reviewRepository;
        this.restaurantService = restaurantService;
    }

    @Override
    public void addReview(ReviewServiceModel reviewServiceModel, String restaurantId) {
        Review review=this.modelMapper.map(reviewServiceModel,Review.class);
        RestaurantServiceModel restaurantServiceModel = this.restaurantService.findById(restaurantId);
        Restaurant restaurant= this.modelMapper.map(restaurantServiceModel, Restaurant.class);
        review.setRestaurant(restaurant);
        reviewServiceModel.setId(review.getId());
        this.reviewRepository.saveAndFlush(review);
    }

    @Override
    public List<ReviewViewModel> getAllReviewFromRestaurantById(String id) {
        List<Review> reviews =this.reviewRepository.findAllByRestaurant_Id(id);
        List<ReviewViewModel> reviewViewModels =new ArrayList<>();
        for(Review review : reviews) {
            ReviewViewModel reviewViewModel =new ReviewViewModel();
            reviewViewModel.setReview(review.getReview());
            reviewViewModel.setEmail(review.getEmail());
            reviewViewModel.setAssessment(review.getAssessment());
            reviewViewModels.add(reviewViewModel);

        }

        return reviewViewModels;
    }

}
