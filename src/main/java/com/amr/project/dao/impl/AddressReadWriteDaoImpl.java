package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.AddressReadWriteDao;
import com.amr.project.model.entity.Address;
import org.springframework.stereotype.Repository;

@Repository
public class AddressReadWriteDaoImpl extends ReadWriteDaoImpl<Address, Long> implements AddressReadWriteDao {
}
