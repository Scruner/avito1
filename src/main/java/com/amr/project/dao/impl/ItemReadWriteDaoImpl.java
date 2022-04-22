package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.ItemReadWriteDao;
import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.Shop;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ItemReadWriteDaoImpl extends ReadWriteDaoImpl<Item, Long> implements ItemReadWriteDao {
    @Override
    public List<Item> getMostPopularItems(int count) {
        return em.createQuery("select i from Item i order by i.rating desc", Item.class).setMaxResults(count).getResultList();
    }

    @Override
    public List<Item> getItemByFoundName(String name) {
        return em.createQuery("select i from Item i where i.name = ?1", Item.class)
                .setParameter(1, name).getResultList();
    }

    @Override
    public List<Item> getItemsByShop(Shop shop) {
        return em.createQuery("select i from Item i where i.shop = :s", Item.class)
                .setParameter("s", shop)
                .setMaxResults(5)
                .getResultList();
    }

    @Override
    public List<Item> findAllUnModerated() {
        return em.createQuery("select i from Item i where i.isModerated = false").getResultList();
    }
}
