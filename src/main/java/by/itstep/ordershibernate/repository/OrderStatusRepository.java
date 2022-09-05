package by.itstep.ordershibernate.repository;

import by.itstep.ordershibernate.entity.OrderStatusEntity;
import by.itstep.ordershibernate.entity.ProductEntity;

import java.util.List;

public interface OrderStatusRepository {

    OrderStatusEntity findById(int id);
    List<OrderStatusEntity> findAll();
    OrderStatusEntity create(OrderStatusEntity entity);
    OrderStatusEntity update(OrderStatusEntity entity);
    void deleteById(int id);
    void  deleteAll();
}
