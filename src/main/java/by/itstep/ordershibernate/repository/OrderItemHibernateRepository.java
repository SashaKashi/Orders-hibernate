package by.itstep.ordershibernate.repository;

import by.itstep.ordershibernate.entity.OrderEntity;
import by.itstep.ordershibernate.entity.OrderItemEntity;
import by.itstep.ordershibernate.utils.EntityManagerUtils;

import javax.persistence.EntityManager;
import java.util.List;

public class OrderItemHibernateRepository implements OrderItemRepository{


    @Override
    public OrderItemEntity findById(int id) {
        EntityManager em=EntityManagerUtils.getEntityManager();
        em.getTransaction().begin();
        OrderItemEntity foundOrderItems = em.find(OrderItemEntity.class, id);
        em.getTransaction().commit();
        em.close();
        return foundOrderItems;
    }

    @Override
    public List<OrderItemEntity> findAll() {
        EntityManager em = EntityManagerUtils.getEntityManager();
        em.getTransaction().begin();
        List <OrderItemEntity> allOrderItems = em.createNativeQuery("SELECT * FROM order_items", OrderItemEntity.class).getResultList();
        em.getTransaction().commit();
        em.close();
        return allOrderItems;
    }

    @Override
    public OrderItemEntity create(OrderItemEntity entity) {
        EntityManager em = EntityManagerUtils.getEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
        return entity;
    }

    @Override
    public OrderItemEntity update(OrderItemEntity entity) {
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
        OrderItemEntity entityToDelete = em.find(OrderItemEntity.class,id);
        em.remove(entityToDelete);
        em.getTransaction().commit();
        em.close();
    }


    @Override
    public void deleteAll() {
        EntityManager em = EntityManagerUtils.getEntityManager();
        em.getTransaction().begin();
        em.createNativeQuery("DELETE FROM order_items").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
}
