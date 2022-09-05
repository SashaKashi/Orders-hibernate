package by.itstep.ordershibernate.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtils {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("orders-unit");

    public static EntityManager getEntityManager(){
        return ENTITY_MANAGER_FACTORY.createEntityManager();
    }
}
