package by.itstep.ordershibernate.repository;

import by.itstep.ordershibernate.entity.OrderStatusEntity;
import by.itstep.ordershibernate.entity.ShipperEntity;

import java.util.List;

public interface ShipperRepository {

    ShipperEntity findById(int id);
    List<ShipperEntity> findAll();
    ShipperEntity create(ShipperEntity entity);
    ShipperEntity update(ShipperEntity entity);
    void deleteById(int id);
    void  deleteAll();
}
