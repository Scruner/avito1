package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.CityReadWriteDao;
import com.amr.project.model.entity.City;
import org.springframework.stereotype.Repository;

@Repository
public class CityReadWriteDaoImpl extends ReadWriteDaoImpl<City, Long> implements CityReadWriteDao {
}
