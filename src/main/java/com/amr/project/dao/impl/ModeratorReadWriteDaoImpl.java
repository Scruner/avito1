package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.ModeratorReadWriteDao;
import org.springframework.stereotype.Repository;

@Repository
public class ModeratorReadWriteDaoImpl extends ReadWriteDaoImpl<Void,Void> implements ModeratorReadWriteDao {
    @Override
    public void accept(Long id, String entity) {
        em.createQuery("update " + entity + " set isModerated = true, isModerateAccept = true, moderatedRejectReason =: reason where id =:id")
                .setParameter("id", id)
                .setParameter("reason", "")
                .executeUpdate();
    }

    @Override
    public void unAccept(Long id, String entity, String reason) {
        em.createQuery("update "+ entity + " set isModerated = true, isModerateAccept = false, moderatedRejectReason =: reason where id =:id")
                .setParameter("id", id)
                .setParameter("reason", reason)
                .executeUpdate();
    }
}
