package by.itstep.ordershibernate.repository;

import by.itstep.ordershibernate.entity.ProductEntity;
import by.itstep.ordershibernate.utils.EntityManagerUtils;

import javax.persistence.EntityManager;
import java.util.List;

public class ProductHibernateRepository implements ProductRepository{


    @Override
    public ProductEntity findById(int id) {
        EntityManager em = EntityManagerUtils.getEntityManager();
        em.getTransaction().begin();
        ProductEntity foundProduct = em.find(ProductEntity.class, id);
        em.getTransaction().commit();
        em.close();
        return foundProduct;
    }

    @Override
    public List<ProductEntity> findAll() {
        EntityManager em = EntityManagerUtils.getEntityManager();
        em.getTransaction().begin();
        List < ProductEntity> allProducts = em.createNativeQuery("SELECT * FROM products", ProductEntity.class).getResultList();
        em.getTransaction().commit();
        em.close();
        return allProducts;
    }

    @Override
    public ProductEntity create(ProductEntity entity) {
        EntityManager em = EntityManagerUtils.getEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
        return entity;
    }

    @Override
    public ProductEntity update(ProductEntity entity) {
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
        ProductEntity entityToDelete = em.find(ProductEntity.class, id);
        em.remove(entityToDelete);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void deleteAll() {
        EntityManager em = EntityManagerUtils.getEntityManager();
        em.getTransaction().begin();
        em.createNativeQuery("DELETE FROM products").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
}
