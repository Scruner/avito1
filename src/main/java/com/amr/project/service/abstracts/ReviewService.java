package com.amr.project.service.abstracts;

import com.amr.project.model.entity.Review;

import java.util.List;

public interface ReviewService extends ReadWriteService<Review, Long> {
    void saveReview(Review review);
    void updateReview(Review review);
    void deleteReview(Long id);
    List<Review> getAllReviewsById( Long id);
    List<Review> findAllUnModerated();
}
