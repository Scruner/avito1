package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.CategoryReadWriteDao;
import com.amr.project.dao.abstracts.ItemReadWriteDao;
import com.amr.project.model.entity.Category;
import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl extends ReadWriteServiceImpl<Item, Long> implements ItemService {

    public final ItemReadWriteDao itemDao;
    public final CategoryReadWriteDao categoryDao;

    @Autowired
    public ItemServiceImpl(ItemReadWriteDao dao, CategoryReadWriteDao categoryDao) {
        super(dao);
        itemDao = dao;
        this.categoryDao = categoryDao;
    }

    @Override
    public List<Item> getMostPopularItems(int count) {
        return itemDao.getMostPopularItems(count);
    }

    @Override
    public List<Item> getItemByFoundName(String name) {
        return itemDao.getItemByFoundName(name);
    }

    @Override
    public List<Item> getItemsByShop(Shop shop) {
        return itemDao.getItemsByShop(shop);
    }

    public Double getAverageRatingItemsByShop(Shop shop) {
        double averageRating = 0.0;
        List<Item> itemsByShop = itemDao.getItemsByShop(shop);
        for (Item item : itemsByShop) {
            averageRating += item.getRating();
        }
        return averageRating;
    }

    public List<Category> getCategoryByShop(Shop shop) {
        List<Category> itemCategory = new ArrayList<>();
        List<Item> itemsByShop = itemDao.getItemsByShop(shop);
        for (Item item : itemsByShop) {
            itemCategory.add(categoryDao.findById(item.getCategoryId()));
        }
        return itemCategory.stream().distinct().collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Item> findAllUnModerated() {
        return itemDao.findAllUnModerated();
    }
}
