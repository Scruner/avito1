package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.ReadWriteDao;
import com.amr.project.dao.abstracts.ReviewReadWriteDao;
import com.amr.project.model.entity.Review;
import com.amr.project.service.abstracts.ReviewService;

import java.util.List;

public class ReviewServiceImpl extends ReadWriteServiceImpl<Review, Long> implements ReviewService {

    public final ReviewReadWriteDao dao;

    public ReviewServiceImpl(ReadWriteDao<Review, Long> dao, ReviewReadWriteDao dao1) {
        super(dao);
        this.dao = dao1;
    }

    @Override
    public void saveNewReview(Review review) {
        review.setModerated(false);
        dao.saveReview(review);
    }

    @Override
    public List<Review> getAllReviewsById(Long id) {
        return dao.getAllReviewsById(id);
    }

    @Override
    public List<Review> getAllNotModeratedReviews() {
        return dao.getAllNotModeratedReviews();
    }
}
