package com.amr.project.dao.abstracts;

import com.amr.project.model.entity.CartItem;
import java.util.List;

public interface CartItemReadWriteDao extends ReadWriteDao<CartItem, Long> {
    List<CartItem> findByUser (Long id);
}