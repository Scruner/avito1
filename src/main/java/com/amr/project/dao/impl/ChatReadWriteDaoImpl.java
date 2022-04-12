package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.ChatReadWriteDao;
import com.amr.project.model.entity.Chat;
import org.springframework.stereotype.Repository;

@Repository
public class ChatReadWriteDaoImpl extends ReadWriteDaoImpl<Chat, Long> implements ChatReadWriteDao {
}
