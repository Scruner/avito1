package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.CartItemReadWriteDao;
import com.amr.project.model.entity.CartItem;
import org.springframework.stereotype.Repository;

@Repository
public class CartItemReadWriteDaoImpl extends ReadWriteDaoImpl<CartItem, Long> implements CartItemReadWriteDao {
}
