package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.CartItemReadWriteDao;
import com.amr.project.model.entity.CartItem;
import com.amr.project.service.abstracts.CartItemService;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl extends ReadWriteServiceImpl<CartItem, Long> implements CartItemService {

    public CartItemServiceImpl(CartItemReadWriteDao dao) {
        super(dao);
    }
}
