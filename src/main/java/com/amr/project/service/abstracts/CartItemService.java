package com.amr.project.service.abstracts;

import com.amr.project.model.entity.CartItem;
import java.util.List;

public interface CartItemService extends ReadWriteService<CartItem, Long> {
    List<CartItem> findByUser(Long id);
}
