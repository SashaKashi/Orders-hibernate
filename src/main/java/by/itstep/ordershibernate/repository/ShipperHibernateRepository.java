package by.itstep.ordershibernate.repository;

import by.itstep.ordershibernate.entity.ProductEntity;
import by.itstep.ordershibernate.entity.ShipperEntity;
import by.itstep.ordershibernate.utils.EntityManagerUtils;

import javax.persistence.EntityManager;
import java.util.List;

public class ShipperHibernateRepository implements ShipperRepository{


    @Override
    public ShipperEntity findById(int id) {
        EntityManager em=EntityManagerUtils.getEntityManager();
        em.getTransaction().begin();
        ShipperEntity foundShipper = em.find(ShipperEntity.class, id);
        em.getTransaction().commit();
        em.close();
        return foundShipper;
    }

    @Override
    public List<ShipperEntity> findAll() {
        EntityManager em = EntityManagerUtils.getEntityManager();
        em.getTransaction().begin();
        List <ShipperEntity> allShippers = em.createNativeQuery("SELECT * FROM shippers", ShipperEntity.class).getResultList();
        em.getTransaction().commit();
        em.close();
        return allShippers;
    }

    @Override
    public ShipperEntity create(ShipperEntity entity) {
        EntityManager em = EntityManagerUtils.getEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
        return entity;
    }

    @Override
    public ShipperEntity update(ShipperEntity entity) {
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
        ShipperEntity entityToDelete = em.find(ShipperEntity.class,id);
        em.remove(entityToDelete);
        em.getTransaction().commit();
        em.close();
    }


    @Override
    public void deleteAll() {
        EntityManager em = EntityManagerUtils.getEntityManager();
        em.getTransaction().begin();
        em.createNativeQuery("DELETE FROM shippers").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
}
