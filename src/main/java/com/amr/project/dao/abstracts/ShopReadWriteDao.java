package com.amr.project.dao.abstracts;

import com.amr.project.model.entity.Shop;

import java.util.List;

public interface ShopReadWriteDao extends ReadWriteDao<Shop, Long> {
    List<Shop> getMostPopularShops(int count);
    List<Shop> getShopByFoundName(String name);

    List<Shop> findallModerated();

    List<Shop> findallUnModerated();
}
