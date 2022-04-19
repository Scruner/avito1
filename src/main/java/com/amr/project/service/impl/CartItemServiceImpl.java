package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.CartItemReadWriteDao;
import com.amr.project.model.entity.CartItem;
import com.amr.project.service.abstracts.CartItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CartItemServiceImpl extends ReadWriteServiceImpl<CartItem, Long> implements CartItemService {

    private final CartItemReadWriteDao cartItemReadWriteDao;

    public CartItemServiceImpl(CartItemReadWriteDao dao) {
        super(dao);
        this.cartItemReadWriteDao = dao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CartItem> findByUser(Long id) {
        return cartItemReadWriteDao.findByUser(id);
    }
}
