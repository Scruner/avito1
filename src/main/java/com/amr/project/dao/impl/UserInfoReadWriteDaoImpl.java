package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.UserInfoReadWriteDao;
import com.amr.project.model.entity.UserInfo;
import org.springframework.stereotype.Repository;

@Repository
public class UserInfoReadWriteDaoImpl extends ReadWriteDaoImpl<UserInfo, Long> implements UserInfoReadWriteDao {
}
