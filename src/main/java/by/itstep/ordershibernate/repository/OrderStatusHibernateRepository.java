package by.itstep.ordershibernate.repository;

import by.itstep.ordershibernate.entity.OrderStatusEntity;
import by.itstep.ordershibernate.entity.ShipperEntity;
import by.itstep.ordershibernate.utils.EntityManagerUtils;

import javax.persistence.EntityManager;
import java.util.List;

public class OrderStatusHibernateRepository implements OrderStatusRepository{


    @Override
    public OrderStatusEntity findById(int id) {
        EntityManager em=EntityManagerUtils.getEntityManager();
        em.getTransaction().begin();
        OrderStatusEntity foundOrderStatus = em.find(OrderStatusEntity.class, id);
        em.getTransaction().commit();
        em.close();
        return foundOrderStatus;
    }

    @Override
    public List<OrderStatusEntity> findAll() {
        EntityManager em = EntityManagerUtils.getEntityManager();
        em.getTransaction().begin();
        List <OrderStatusEntity> allOrderStatuses = em.createNativeQuery("SELECT * FROM order_statuses", OrderStatusEntity.class).getResultList();
        em.getTransaction().commit();
        em.close();
        return allOrderStatuses;
    }

    @Override
    public OrderStatusEntity create(OrderStatusEntity entity) {
        EntityManager em = EntityManagerUtils.getEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
        return entity;
    }

    @Override
    public OrderStatusEntity update(OrderStatusEntity entity) {
            EntityManager em = EntityManagerUtils.getEntityManager();
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
            em.close();
            return entity;
    }

    @Override
    public void deleteById(int id) {
        EntityManager em = EntityManagerUtils.getEntityManager();
        em.getTransaction().begin();
        OrderStatusEntity entityToDelete = em.find(OrderStatusEntity.class,id);
        em.remove(entityToDelete);
        em.getTransaction().commit();
        em.close();
    }


    @Override
    public void deleteAll() {
        EntityManager em = EntityManagerUtils.getEntityManager();
        em.getTransaction().begin();
        em.createNativeQuery("DELETE FROM order_statuses").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
}
