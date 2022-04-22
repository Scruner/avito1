package com.amr.project.service.impl;

import com.amr.project.converter.ItemMapper;
import com.amr.project.converter.ReviewMapper;
import com.amr.project.converter.ShopMapper;
import com.amr.project.dao.abstracts.ModeratorReadWriteDao;
import com.amr.project.model.dto.ModeratorListDto;
import com.amr.project.model.entity.Moderator;
import com.amr.project.service.abstracts.ItemService;
import com.amr.project.service.abstracts.ModeratorService;
import com.amr.project.service.abstracts.ReviewService;
import com.amr.project.service.abstracts.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ModeratorServiceImpl implements ModeratorService {

    private final ShopService shopService;
    private final ShopMapper shopMapper;
    private final ItemService itemService;
    private final ItemMapper itemMapper;
    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;
    private final ModeratorReadWriteDao moderatorReadWriteDao;

    @Autowired
    public ModeratorServiceImpl(ShopService shopService, ShopMapper shopMapper,ItemService itemService,
                                ItemMapper itemMapper, ReviewService reviewService, ReviewMapper reviewMapper,
                                ModeratorReadWriteDao moderatorReadWriteDao) {
        this.shopService = shopService;
        this.shopMapper = shopMapper;
        this.itemService = itemService;
        this.itemMapper = itemMapper;
        this.reviewService = reviewService;
        this.reviewMapper = reviewMapper;
        this.moderatorReadWriteDao = moderatorReadWriteDao;
    }

    @Override
    public ModeratorListDto findAllUnModerated() {
        ModeratorListDto moderatorDto = new ModeratorListDto();
        moderatorDto.setItem(itemMapper.toDtoList(itemService.findAllUnModerated()));
        moderatorDto.setShop(shopMapper.toDtoList(shopService.findAllUnModerated()));
        moderatorDto.setReview(reviewMapper.toDtoList(reviewService.findAllUnModerated()));
        return moderatorDto;
    }

    @Override
    @Transactional
    public void accept(Moderator moderator) {
        moderatorReadWriteDao.accept(moderator.getId(), moderator.getType());
    }

    @Override
    @Transactional
    public void unAccept(Moderator moderator) {
        moderatorReadWriteDao.unAccept(moderator.getId(), moderator.getType(), moderator.getReason());
    }
}
