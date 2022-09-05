package by.itstep.ordershibernate.repository;

import by.itstep.ordershibernate.entity.CustomerEntity;
import by.itstep.ordershibernate.entity.OrderEntity;
import by.itstep.ordershibernate.entity.OrderStatusEntity;
import by.itstep.ordershibernate.entity.ShipperEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class OrderRepositoryTest {

    private OrderRepository orderRepository = new OrderHibernateRepository();
    private CustomerRepository customerRepository = new CustomerHibernateRepository();
    private ShipperRepository shipperRepository = new ShipperHibernateRepository();
    private OrderStatusRepository orderStatusRepository = new OrderStatusHibernateRepository();


    @BeforeEach
    public void setUp(){
        orderRepository=new OrderHibernateRepository();
        orderRepository.deleteAll();
    }

    @AfterEach
    public void clean(){
        orderRepository.deleteAll();
        customerRepository.deleteAll();
        shipperRepository.deleteAll();
        orderStatusRepository.deleteAll();
    }

    @Test
    public void create_happyPath(){
        //given
        OrderEntity orderToCreate = prepareOrder();
        //when
        OrderEntity createdOrder = orderRepository.create(orderToCreate);
        //then
        Assertions.assertNotNull(createdOrder.getId());
        OrderEntity foundOrder = orderRepository.findById(createdOrder.getId());
        Assertions.assertEquals(orderToCreate.getComment(),foundOrder.getComment());
        Assertions.assertEquals(orderToCreate.getShippedDate(),foundOrder.getShippedDate());
        Assertions.assertEquals(orderToCreate.getShipper(),foundOrder.getShipper());
        Assertions.assertEquals(orderToCreate.getStatus(),foundOrder.getStatus());
        Assertions.assertEquals(orderToCreate.getOrderDate(),foundOrder.getOrderDate());
        Assertions.assertEquals(orderToCreate.getCustomer(),foundOrder.getCustomer());
    }

    @Test
    public void findAll_whenNoOneFound() {
        //given
        //when
        List<OrderEntity> foundOrders = orderRepository.findAll();
        //then
        Assertions.assertTrue(foundOrders.isEmpty());
    }

    @Test
    public void findAll_happyPath(){
        //given
        addOrderToDB();
        addOrderToDB();
        //when
        List<OrderEntity> foundOrders = orderRepository.findAll();
        //then
        Assertions.assertEquals(2,foundOrders.size());
    }

    @Test
    public void update_happyPath(){
        //given
        OrderEntity existingOrder = addOrderToDB();

        existingOrder.setOrderDate(LocalDate.now().plusDays(1));
        existingOrder.setComment("updated comment#"+(int)(Math.random()*100));
        existingOrder.setShippedDate(LocalDate.now().plusDays(3));

        CustomerRepositoryTest customerTest = new CustomerRepositoryTest();
        CustomerEntity customerForUpdate = customerTest.addCustomerToDB();
        existingOrder.setCustomer(customerForUpdate);
        ShipperRepositoryTest shipperTest = new ShipperRepositoryTest();
        ShipperEntity shipperForUpdate = shipperTest.addShipperToDB();
        existingOrder.setShipper(shipperForUpdate);
        OrderStatusRepositoryTest statusTest = new OrderStatusRepositoryTest();
        OrderStatusEntity statusForUpdate = statusTest.addStatusToDB();
        existingOrder.setStatus(statusForUpdate);

        //when
        OrderEntity updatedOrder = orderRepository.update(existingOrder);
        //then
        Assertions.assertEquals(existingOrder.getId(),updatedOrder.getId());
        OrderEntity foundOrder = orderRepository.findById(existingOrder.getId());
        Assertions.assertEquals(existingOrder.getCustomer(),foundOrder.getCustomer());
        Assertions.assertEquals(existingOrder.getStatus(),foundOrder.getStatus());
        Assertions.assertEquals(existingOrder.getShipper(),foundOrder.getShipper());
        Assertions.assertEquals(existingOrder.getShippedDate(),foundOrder.getShippedDate());
        Assertions.assertEquals(existingOrder.getOrderDate(),foundOrder.getOrderDate());
        Assertions.assertEquals(existingOrder.getComment(),foundOrder.getComment());
    }




    @Test
    public void delete_happyPath(){
        //given
        OrderEntity existingOrder = addOrderToDB();
        //when
        orderRepository.deleteById(existingOrder.getId());
        //then
        OrderEntity foundOrder = orderRepository.findById(existingOrder.getId());
        Assertions.assertNull(foundOrder);
    }



    private OrderEntity prepareOrder(){
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

    private OrderEntity addOrderToDB(){
        OrderEntity orderToAdd = prepareOrder();
        return orderRepository.create(orderToAdd);
    }


}
