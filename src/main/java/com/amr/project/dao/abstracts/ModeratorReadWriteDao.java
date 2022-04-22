package com.amr.project.dao.abstracts;

public interface ModeratorReadWriteDao extends ReadWriteDao<Void,Void> {
    void accept(Long id, String entity);
    void unAccept(Long id, String entity, String reason);
}
