package by.itstep.ordershibernate.repository;

import by.itstep.ordershibernate.entity.ProductEntity;

import java.util.List;

public interface ProductRepository {
    ProductEntity findById(int id);
    List <ProductEntity> findAll();
    ProductEntity create(ProductEntity entity);
    ProductEntity update(ProductEntity entity);
    void deleteById(int id);
    void  deleteAll();
}
