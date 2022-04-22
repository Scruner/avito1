package com.amr.project.dao.abstracts;

import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.Shop;

import java.util.List;

public interface ItemReadWriteDao extends ReadWriteDao<Item, Long> {
    List<Item> getMostPopularItems(int count);
    List<Item> getItemByFoundName(String name);
    List<Item> getItemsByShop(Shop shop);
    List<Item> findAllUnModerated();
}

