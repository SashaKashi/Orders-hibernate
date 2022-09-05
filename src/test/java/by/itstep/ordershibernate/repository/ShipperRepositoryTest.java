package by.itstep.ordershibernate.repository;

import by.itstep.ordershibernate.entity.ShipperEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ShipperRepositoryTest {

    private ShipperRepository shipperRepository = new ShipperHibernateRepository();

    @BeforeEach
    public void setUp(){
        shipperRepository = new ShipperHibernateRepository();
        shipperRepository.deleteAll();
    }

    @AfterEach
    public void clean(){
        shipperRepository.deleteAll();
    }

    @Test
    public void create_happyPath(){
        //given
        ShipperEntity shipperToCreate = prepareShipper();
        //when
        ShipperEntity createdShipper = shipperRepository.create(shipperToCreate);
        //then
        Assertions.assertNotNull(createdShipper.getId());
        ShipperEntity foundShipper = shipperRepository.findById(createdShipper.getId());
        Assertions.assertEquals(shipperToCreate.getName(),foundShipper.getName());
    }

    @Test
    public void findAll_whenNoOneFound(){
        //given
        //when
        List <ShipperEntity> foundShippers = shipperRepository.findAll();
        //then
        Assertions.assertTrue(foundShippers.isEmpty());
    }

    @Test
    public void findAll_happyPath(){
        //given
        addShipperToDB();
        addShipperToDB();
        //then
        List <ShipperEntity> foundShippers = shipperRepository.findAll();
        //then
        Assertions.assertEquals(2,foundShippers.size());
    }

    @Test
    public void update_happyPath(){
        //given
        ShipperEntity existingShipper = addShipperToDB();
        existingShipper.setName("updated");
        //when
        ShipperEntity updatedShipper = shipperRepository.update(existingShipper);
        //then
        Assertions.assertEquals(existingShipper.getId(),updatedShipper.getId());
        ShipperEntity foundShipper = shipperRepository.findById(existingShipper.getId());
        Assertions.assertEquals(existingShipper.getName(),foundShipper.getName());
    }

    @Test
    public void delete_happyPath(){
        //given
        ShipperEntity existingShipper = addShipperToDB();
        //when
        shipperRepository.deleteById(existingShipper.getId());
        //then
        ShipperEntity foundShipper = shipperRepository.findById(existingShipper.getId());
        Assertions.assertNull(foundShipper);
    }




    private ShipperEntity prepareShipper(){
        ShipperEntity shipper = new ShipperEntity();
        shipper.setName("name#"+(int)(Math.random()*100));
        return shipper;
    }

    public ShipperEntity addShipperToDB(){
        ShipperEntity shipperToAdd = prepareShipper();
        return shipperRepository.create(shipperToAdd);
    }


}
