package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.ReviewReadWriteDao;
import com.amr.project.model.entity.Review;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewReadWriteDaoImpl extends ReadWriteDaoImpl<Review, Long> implements ReviewReadWriteDao {
}
