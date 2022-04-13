package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.UserReadWriteDao;
import com.amr.project.model.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserReadWriteDaoImpl extends ReadWriteDaoImpl<User, Long> implements UserReadWriteDao {
    public User getUserByUsername(String name) {
        return em.createQuery("select u from User u where u.username = :name", User.class).setParameter("name", name).getSingleResult();
    }

    @Override
    public User getUserById(Long id) {
        return findById(id);
    }

}
