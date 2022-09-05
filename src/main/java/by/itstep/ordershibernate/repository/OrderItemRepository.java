package by.itstep.ordershibernate.repository;

import by.itstep.ordershibernate.entity.CustomerEntity;
import by.itstep.ordershibernate.entity.OrderItemEntity;

import java.util.List;

public interface OrderItemRepository {

    OrderItemEntity findById(int id);
    List<OrderItemEntity> findAll();
    OrderItemEntity create(OrderItemEntity entity);
    OrderItemEntity update(OrderItemEntity entity);
    void deleteById(int id);
    void  deleteAll();
}
