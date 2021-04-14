package project.tastyfood.service;

import project.tastyfood.model.service.ReviewServiceModel;
import project.tastyfood.model.view.ReviewViewModel;

import java.util.List;

public interface ReviewService {
    void addReview(ReviewServiceModel reviewServiceModel,String restaurantId);
    List<ReviewViewModel> getAllReviewFromRestaurantById(String id);
}
