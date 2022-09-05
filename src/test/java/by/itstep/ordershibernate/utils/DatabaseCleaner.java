package by.itstep.ordershibernate.utils;

import by.itstep.ordershibernate.repository.*;

public class DatabaseCleaner {

    private ProductRepository productRepository;
    private OrderItemRepository orderItemRepository;
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private OrderStatusRepository orderStatusRepository;
    private ShipperRepository shipperRepository;

    public void clean(){
        productRepository = new ProductHibernateRepository();
        orderItemRepository = new OrderItemHibernateRepository();
        orderRepository = new OrderHibernateRepository();
        customerRepository = new CustomerHibernateRepository();
        orderStatusRepository = new OrderStatusHibernateRepository();
        shipperRepository = new ShipperHibernateRepository();

        orderItemRepository.deleteAll();
        productRepository.deleteAll();
        orderRepository.deleteAll();
        customerRepository.deleteAll();
        shipperRepository.deleteAll();
        orderStatusRepository.deleteAll();
    }
}
