package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.ImageReadWriteDao;
import com.amr.project.model.entity.Image;
import com.amr.project.service.impl.ImageServiceImpl;
import org.springframework.stereotype.Repository;

@Repository
public class ImageReadWriteDaoImpl extends ReadWriteDaoImpl<Image, Long> implements ImageReadWriteDao {
}
