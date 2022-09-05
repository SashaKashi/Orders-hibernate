package by.itstep.ordershibernate.repository;

import by.itstep.ordershibernate.entity.CustomerEntity;
import by.itstep.ordershibernate.entity.OrderStatusEntity;
import by.itstep.ordershibernate.utils.EntityManagerUtils;

import javax.persistence.EntityManager;
import java.util.List;

public class CustomerHibernateRepository implements CustomerRepository{


    @Override
    public CustomerEntity findById(int id) {
        EntityManager em=EntityManagerUtils.getEntityManager();
        em.getTransaction().begin();
        CustomerEntity foundCustomers = em.find(CustomerEntity.class, id);
        em.getTransaction().commit();
        em.close();
        return foundCustomers;
    }

    @Override
    public List<CustomerEntity> findAll() {
        EntityManager em = EntityManagerUtils.getEntityManager();
        em.getTransaction().begin();
        List <CustomerEntity> allCustomers = em.createNativeQuery("SELECT * FROM customers", CustomerEntity.class).getResultList();
        em.getTransaction().commit();
        em.close();
        return allCustomers;
    }

    @Override
    public CustomerEntity create(CustomerEntity entity) {
        EntityManager em = EntityManagerUtils.getEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
        return entity;
    }

    @Override
    public CustomerEntity update(CustomerEntity entity) {
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
        CustomerEntity entityToDelete = em.find(CustomerEntity.class,id);
        em.remove(entityToDelete);
        em.getTransaction().commit();
        em.close();
    }


    @Override
    public void deleteAll() {
        EntityManager em = EntityManagerUtils.getEntityManager();
        em.getTransaction().begin();
        em.createNativeQuery("DELETE FROM customers").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
}
