package com.amr.project.service.abstracts;

import com.amr.project.model.entity.Category;
import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.Shop;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Set;

public interface ItemService extends ReadWriteService<Item, Long> {
    List<Item> getMostPopularItems(int count);
    List<Item> getItemByFoundName(String name);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteByIdCascadeEnable(Long id);
    List<Item> getItemsByShop(Shop shop);
    Double getAverageRatingItemsByShop(Shop shop);
    List<Category> getCategoryByShop(Shop shop);
    List<Item> findAllUnModerated();

}
