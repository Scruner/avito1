package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.UserInfoReadWriteDao;
import com.amr.project.model.entity.UserInfo;
import com.amr.project.service.abstracts.UserInfoService;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl extends ReadWriteServiceImpl<UserInfo, Long> implements UserInfoService {

    public UserInfoServiceImpl(UserInfoReadWriteDao dao) {
        super(dao);
    }
}
