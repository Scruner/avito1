package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.ReviewReadWriteDao;
import com.amr.project.model.entity.Review;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewReadWriteDaoImpl extends ReadWriteDaoImpl<Review, Long> implements ReviewReadWriteDao {

    @Override
    public void saveReview(Review review) {
      persist(review);
    }

    @Override
    public void updateReview(Review review) {
        update(review);
    }

    @Override
    public void deleteReview(Long id) {
        deleteByIdCascadeIgnore(id);
    }



    @Override
    public List<Review> getAllReviewsById(Long id) {
        //выбрать из БД все отзывы про вещь по её id, которые прошли модерацию
        return em.createQuery("select r from Review r where r.item = ?1 AND r.isModerateAccept = true ", Review.class)
                .setParameter(1, id).getResultList();
    }

    @Override
    public List<Review> findAllUnModerated() {
        //выбрать из БД все  отзывы про вещь по её id, которые ещё не модерировались
        return em.createQuery("select r from Review r where r.isModerated = false ", Review.class)
                .getResultList();
    }
}
