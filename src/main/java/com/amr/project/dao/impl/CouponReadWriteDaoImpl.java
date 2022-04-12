package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.CouponReadWriteDao;
import com.amr.project.model.entity.Coupon;
import org.springframework.stereotype.Repository;

@Repository
public class CouponReadWriteDaoImpl extends ReadWriteDaoImpl<Coupon, Long> implements CouponReadWriteDao {
}
