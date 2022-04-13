package com.amr.project.dao.abstracts;

import com.amr.project.model.entity.User;

public interface UserReadWriteDao extends ReadWriteDao<User, Long>{
    User getUserByUsername(String name);
    User getUserById(Long id);
}
