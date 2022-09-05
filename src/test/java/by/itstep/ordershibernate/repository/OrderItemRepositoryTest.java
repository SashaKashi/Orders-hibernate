package by.itstep.ordershibernate.repository;

import by.itstep.ordershibernate.entity.OrderEntity;
import by.itstep.ordershibernate.entity.OrderItemEntity;
import by.itstep.ordershibernate.entity.ProductEntity;
import by.itstep.ordershibernate.utils.DatabaseCleaner;
import by.itstep.ordershibernate.utils.EntityUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderItemRepositoryTest {

    private ProductRepository productRepository;
    private OrderItemRepository orderItemRepository;
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private OrderStatusRepository orderStatusRepository;
    private ShipperRepository shipperRepository;
    private DatabaseCleaner cleaner;

    @BeforeEach
    private void setUp(){
        productRepository = new ProductHibernateRepository();
        orderItemRepository = new OrderItemHibernateRepository();
        orderRepository = new OrderHibernateRepository();
        customerRepository = new CustomerHibernateRepository();
        orderStatusRepository = new OrderStatusHibernateRepository();
        shipperRepository = new ShipperHibernateRepository();
        cleaner = new DatabaseCleaner();
        cleaner.clean();
    }

    @AfterEach
    private void shutDown(){
        cleaner.clean();
    }

    @Test
    public void create_happyPath(){
        //given
        OrderItemEntity orderItemToCreate = EntityUtils.prepareOrderItem();
        //when
        OrderItemEntity createdOrderItem = orderItemRepository.create(orderItemToCreate);
        //then
        Assertions.assertNotNull(createdOrderItem.getId());
        OrderItemEntity foundOrderItem = orderItemRepository.findById(createdOrderItem.getId());
        Assertions.assertEquals(orderItemToCreate.getOrder().getId(),foundOrderItem.getOrder().getId());
        Assertions.assertEquals(orderItemToCreate.getProduct().getId(),foundOrderItem.getProduct().getId());
    }







}
