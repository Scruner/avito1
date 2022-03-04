package com.amr.project.dao.abstracts;

import com.amr.project.model.entity.Item;

import java.util.List;

public interface ItemReadWriteDao extends ReadWriteDao<Item, Long> {
    List<Item> getMostPopularItems(int count);
}
