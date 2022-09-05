package by.itstep.ordershibernate.repository;

import by.itstep.ordershibernate.entity.OrderStatusEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class OrderStatusRepositoryTest {

    private OrderStatusRepository orderStatusRepository = new OrderStatusHibernateRepository();

    @BeforeEach
    public void setUp(){
        orderStatusRepository = new OrderStatusHibernateRepository();
        orderStatusRepository.deleteAll();
    }

    @AfterEach
    public void  clean(){
        orderStatusRepository.deleteAll();
    }

    @Test
    public void create_happyPath(){
        //given
        OrderStatusEntity statusToCreate = prepareStatus();
        //when
        OrderStatusEntity createdStatus = orderStatusRepository.create(statusToCreate);
        //then
        Assertions.assertNotNull(createdStatus.getId());
        OrderStatusEntity foundStatus = orderStatusRepository.findById(createdStatus.getId());
        Assertions.assertEquals(statusToCreate.getName(),foundStatus.getName());
    }

    @Test
    public void findAll_whenNoOneFound(){
        //given
        //when
        List <OrderStatusEntity> foundStatuses = orderStatusRepository.findAll();
        //then
        Assertions.assertTrue(foundStatuses.isEmpty());
    }

    @Test
    public void findAll_happyPath(){
        //given
        addStatusToDB();
        addStatusToDB();
        //then
        List <OrderStatusEntity> foundStatuses = orderStatusRepository.findAll();
        //then
        Assertions.assertEquals(2,foundStatuses.size());
    }

    @Test
    public void update_happyPath(){
        //given
        OrderStatusEntity existingStatus = addStatusToDB();
        existingStatus.setName("updated");
        //when
        OrderStatusEntity updatedStatus = orderStatusRepository.update(existingStatus);
        //then
        Assertions.assertEquals(existingStatus.getId(),updatedStatus.getId());
        OrderStatusEntity foundStatus = orderStatusRepository.findById(existingStatus.getId());
        Assertions.assertEquals(existingStatus.getName(),foundStatus.getName());
    }

    @Test
    public void delete_happyPath(){
        //given
        OrderStatusEntity existingStatus = addStatusToDB();
        //when
        orderStatusRepository.deleteById(existingStatus.getId());
        //then
        OrderStatusEntity foundStatus = orderStatusRepository.findById(existingStatus.getId());
        Assertions.assertNull(foundStatus);
    }




    private OrderStatusEntity prepareStatus(){
        OrderStatusEntity status = new OrderStatusEntity();
        status.setName("name#"+(int)(Math.random()*100));
        return status;
    }

    public OrderStatusEntity addStatusToDB(){
        OrderStatusEntity statusToAdd = prepareStatus();
        return orderStatusRepository.create(statusToAdd);
    }


}
