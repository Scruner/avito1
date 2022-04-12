package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.CityReadWriteDao;
import com.amr.project.model.entity.City;
import com.amr.project.service.abstracts.CityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CityServiceImpl extends ReadWriteServiceImpl<City, Long> implements CityService {

    public CityServiceImpl(CityReadWriteDao dao) {
        super(dao);
    }


}
