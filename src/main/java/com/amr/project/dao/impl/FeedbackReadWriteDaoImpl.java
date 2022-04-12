package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.FeedbackReadWriteDao;
import com.amr.project.model.entity.Feedback;
import org.springframework.stereotype.Repository;

@Repository
public class FeedbackReadWriteDaoImpl extends ReadWriteDaoImpl<Feedback, Long> implements FeedbackReadWriteDao {
}
