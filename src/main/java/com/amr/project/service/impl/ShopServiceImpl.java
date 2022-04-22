package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.ShopReadWriteDao;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("shopServiceImpl")
public class ShopServiceImpl extends ReadWriteServiceImpl<Shop, Long> implements ShopService {

    public final ShopReadWriteDao dao;

    @Autowired
    public ShopServiceImpl(ShopReadWriteDao dao) {
        super(dao);
        this.dao = dao;
    }

    @Override
    public List<Shop> getMostPopularShops(int count) {
        return dao.getMostPopularShops(count);
    }

    @Override
    public List<Shop> getShopByFoundName(String name) {
        return dao.getShopByFoundName(name);
    }

    @Override
    public List<Shop> findAllModerated() {return dao.findallModerated();}

    @Override
    public List<Shop> findAllUnModerated() {return dao.findallUnModerated();}
}
