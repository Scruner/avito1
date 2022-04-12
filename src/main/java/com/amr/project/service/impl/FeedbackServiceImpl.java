package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.FeedbackReadWriteDao;
import com.amr.project.model.entity.Feedback;
import com.amr.project.service.abstracts.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl extends ReadWriteServiceImpl<Feedback, Long> implements FeedbackService {

    @Autowired
    public FeedbackServiceImpl(FeedbackReadWriteDao dao) {
        super(dao);
    }
}
