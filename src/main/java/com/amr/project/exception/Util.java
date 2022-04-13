package com.amr.project.exception;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Util {

    private static final Logger logger = Logger.getLogger(Util.class.getName());

    public static <T> T handlerSingleResult(@NotNull TypedQuery<T> query) {
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            logger.log(Level.FINE, "На данный запрос к базе данных, получен ответ null.", e);
            return null;
        } catch (NonUniqueResultException e) {
            logger.log(Level.FINE, "На данный запрос к базе данных, получено более одного результата.", e);
            return null;
        }
    }
}
