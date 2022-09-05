package by.itstep.ordershibernate.utils;

import by.itstep.ordershibernate.entity.*;
import by.itstep.ordershibernate.repository.*;

import java.time.LocalDate;

public class EntityUtils {

    public static CustomerEntity prepareCustomer(){
        CustomerEntity customer = new CustomerEntity();
        customer.setName("name#"+(int)(Math.random()*100));
        customer.setLastName("lastname#"+(int)(Math.random()*100));
        customer.setBirthDate(LocalDate.now());
        customer.setPhone("1234455");
        customer.setAddress("street#"+(int)(Math.random()*100));
        customer.setCity("city#"+(int)(Math.random()*100));
        customer.setState("st");
        customer.setPoints((int)(Math.random()*1000));
        return customer;
    }


    public static OrderEntity prepareOrder(){
        OrderEntity order = new OrderEntity();
        order.setOrderDate(LocalDate.now());
        order.setComment("comment#"+(int)(Math.random()*100));
        order.setShippedDate(LocalDate.now().plusDays(2));
        CustomerRepositoryTest customerTest = new CustomerRepositoryTest();
        CustomerEntity customerForTest = customerTest.addCustomerToDB();
        order.setCustomer(customerForTest);
        ShipperRepositoryTest shipperTest = new ShipperRepositoryTest();
        ShipperEntity shipperForTest = shipperTest.addShipperToDB();
        order.setShipper(shipperForTest);
        OrderStatusRepositoryTest statusTest = new OrderStatusRepositoryTest();
        OrderStatusEntity statusForTest = statusTest.addStatusToDB();
        order.setStatus(statusForTest);
        return order;
    }

    public static OrderStatusEntity prepareStatus(){
        OrderStatusEntity status = new OrderStatusEntity();
        status.setName("name#"+(int)(Math.random()*100));
        return status;
    }

    public static ProductEntity prepareProduct(){
        ProductEntity product = new ProductEntity();
        product.setName("name#"+(int)(Math.random()*100));
        product.setUnit_price((int)(Math.random()*100));
        product.setQuantity_in_stock((int)(Math.random()*100));
        return product;
    }

    public static ShipperEntity prepareShipper(){
        ShipperEntity shipper = new ShipperEntity();
        shipper.setName("name#"+(int)(Math.random()*100));
        return shipper;
    }

    public static OrderItemEntity prepareOrderItem(){
        OrderRepository orderRepository = new OrderHibernateRepository();
        OrderEntity order = prepareOrder();
        orderRepository.create(order);
        ProductRepository productRepository = new ProductHibernateRepository();
        ProductEntity product = prepareProduct();
        productRepository.create(product);
        OrderItemRepository orderItemRepository = new OrderItemHibernateRepository();
        OrderItemEntity orderItem = new OrderItemEntity();
        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setQuantity(20);
        orderItem.setUnitPrice(10.5);
        return orderItem;
    }

}
