package by.itstep.ordershibernate.repository;

import by.itstep.ordershibernate.entity.CustomerEntity;
import by.itstep.ordershibernate.entity.OrderEntity;

import java.util.List;

public interface OrderRepository {

    OrderEntity findById(int id);
    List<OrderEntity> findAll();
    OrderEntity create(OrderEntity entity);
    OrderEntity update(OrderEntity entity);
    void deleteById(int id);
    void  deleteAll();
}
