package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.ReadWriteDao;
import com.amr.project.dao.abstracts.ReviewReadWriteDao;
import com.amr.project.model.entity.Review;
import com.amr.project.service.abstracts.ReviewService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewServiceImpl extends ReadWriteServiceImpl<Review, Long> implements ReviewService {

    public final ReviewReadWriteDao dao;

    public ReviewServiceImpl(ReadWriteDao<Review, Long> dao, ReviewReadWriteDao dao1) {
        super(dao);
        this.dao = dao1;
    }

    @Override
    public void saveReview(Review review) {
        review.setModerated(false);
        dao.saveReview(review);
    }

    @Override
    public void updateReview(Review review) {
        dao.updateReview(review);
    }

    @Override
    public void deleteReview(Long id) {
        dao.deleteReview(id);
    }

    @Override
    public List<Review> getAllReviewsById(Long id) {
        return dao.getAllReviewsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Review> findAllUnModerated() {
        return dao.findAllUnModerated();
    }
}
