package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.CartItemReadWriteDao;
import com.amr.project.model.entity.CartItem;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CartItemReadWriteDaoImpl extends ReadWriteDaoImpl<CartItem, Long> implements CartItemReadWriteDao {

    @Override
    public List<CartItem> findByUser(Long id) {
        return em.createQuery("from CartItem where user.id =: id ",CartItem.class)
                .setParameter("id", id).getResultList();
    }
}
