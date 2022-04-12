package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.CountryReadWriteDao;
import com.amr.project.model.entity.Country;
import org.springframework.stereotype.Repository;

@Repository
public class CountryReadWriteDaoImpl extends ReadWriteDaoImpl<Country, Long> implements CountryReadWriteDao {
}
