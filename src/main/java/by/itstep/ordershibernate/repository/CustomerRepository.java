package by.itstep.ordershibernate.repository;

import by.itstep.ordershibernate.entity.CustomerEntity;
import by.itstep.ordershibernate.entity.OrderStatusEntity;

import java.util.List;

public interface CustomerRepository {

    CustomerEntity findById(int id);
    List<CustomerEntity> findAll();
    CustomerEntity create(CustomerEntity entity);
    CustomerEntity update(CustomerEntity entity);
    void deleteById(int id);
    void  deleteAll();
}
