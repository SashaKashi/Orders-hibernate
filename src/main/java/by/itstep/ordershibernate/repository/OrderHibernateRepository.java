package by.itstep.ordershibernate.repository;

import by.itstep.ordershibernate.entity.CustomerEntity;
import by.itstep.ordershibernate.entity.OrderEntity;
import by.itstep.ordershibernate.utils.EntityManagerUtils;

import javax.persistence.EntityManager;
import java.util.List;

public class OrderHibernateRepository implements OrderRepository{


    @Override
    public OrderEntity findById(int id) {
        EntityManager em=EntityManagerUtils.getEntityManager();
        em.getTransaction().begin();
        OrderEntity foundOrders = em.find(OrderEntity.class, id);
        em.getTransaction().commit();
        em.close();
        return foundOrders;
    }

    @Override
    public List<OrderEntity> findAll() {
        EntityManager em = EntityManagerUtils.getEntityManager();
        em.getTransaction().begin();
        List <OrderEntity> allOrders = em.createNativeQuery("SELECT * FROM orders", OrderEntity.class).getResultList();
        em.getTransaction().commit();
        em.close();
        return allOrders;
    }

    @Override
    public OrderEntity create(OrderEntity entity) {
        EntityManager em = EntityManagerUtils.getEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
        return entity;
    }

    @Override
    public OrderEntity update(OrderEntity entity) {
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
        OrderEntity entityToDelete = em.find(OrderEntity.class,id);
        em.remove(entityToDelete);
        em.getTransaction().commit();
        em.close();
    }


    @Override
    public void deleteAll() {
        EntityManager em = EntityManagerUtils.getEntityManager();
        em.getTransaction().begin();
        em.createNativeQuery("DELETE FROM orders").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
}
