package com.amr.project.service.abstracts;

import com.amr.project.model.entity.Shop;

import java.util.List;

public interface ShopService extends ReadWriteService<Shop, Long> {
    List<Shop> getMostPopularShops(int count);
    List<Shop> getShopByFoundName(String name);

    List<Shop> findAllModerated();

    List<Shop> findAllUnModerated();
}
