package by.itstep.ordershibernate.repository;

import by.itstep.ordershibernate.entity.CustomerEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class CustomerRepositoryTest {

    private CustomerRepository customerRepository = new CustomerHibernateRepository();

    @BeforeEach
    public void setUp(){
        customerRepository = new CustomerHibernateRepository();
        customerRepository.deleteAll();
    }

    @AfterEach
    public void clean(){
        customerRepository.deleteAll();
    }

    @Test
    public void create_happyPath(){
        //given
        CustomerEntity customerToCreate = prepareCustomer();
        //when
        CustomerEntity createdCustomer = customerRepository.create(customerToCreate);
        //then
        Assertions.assertNotNull(createdCustomer.getId());
        CustomerEntity foundCustomer = customerRepository.findById(createdCustomer.getId());
        Assertions.assertEquals(customerToCreate.getName(),foundCustomer.getName());
        Assertions.assertEquals(customerToCreate.getAddress(),foundCustomer.getAddress());
        Assertions.assertEquals(customerToCreate.getCity(),foundCustomer.getCity());
        Assertions.assertEquals(customerToCreate.getLastName(),foundCustomer.getLastName());
        Assertions.assertEquals(customerToCreate.getBirthDate(),foundCustomer.getBirthDate());
        Assertions.assertEquals(customerToCreate.getPhone(),foundCustomer.getPhone());
        Assertions.assertEquals(customerToCreate.getState(),foundCustomer.getState());
        Assertions.assertEquals(customerToCreate.getPoints(),foundCustomer.getPoints());
    }

    @Test
    public void findAll_whenNoOneFound(){
        //given
        //when
        List<CustomerEntity> foundCustomers = customerRepository.findAll();
        //then
        Assertions.assertTrue(foundCustomers.isEmpty());
    }


    @Test
    public void findAll_happyPath(){
        //given
        addCustomerToDB();
        addCustomerToDB();
        //then
        List<CustomerEntity> foundCustomers = customerRepository.findAll();
        //then
        Assertions.assertEquals(2,foundCustomers.size());
    }

    @Test
    public void update_happyPath(){
        //given
        CustomerEntity existingCustomer = addCustomerToDB();
        existingCustomer.setName("updated");
        existingCustomer.setLastName("updated");
        existingCustomer.setBirthDate(LocalDate.now().plusDays(1));
        existingCustomer.setAddress("updated");
        existingCustomer.setCity("updated");
        existingCustomer.setPoints(1001);
        existingCustomer.setPhone("updated");
        existingCustomer.setState("up");
        //when
        CustomerEntity updatedCustomer = customerRepository.update(existingCustomer);
        //then
        Assertions.assertEquals(existingCustomer.getId(),updatedCustomer.getId());
        CustomerEntity foundCustomer = customerRepository.findById(existingCustomer.getId());
        Assertions.assertEquals(existingCustomer.getName(),foundCustomer.getName());
        Assertions.assertEquals(existingCustomer.getLastName(),foundCustomer.getLastName());
        Assertions.assertEquals(existingCustomer.getBirthDate(),foundCustomer.getBirthDate());
        Assertions.assertEquals(existingCustomer.getPhone(),foundCustomer.getPhone());
        Assertions.assertEquals(existingCustomer.getAddress(),foundCustomer.getAddress());
        Assertions.assertEquals(existingCustomer.getCity(),foundCustomer.getCity());
        Assertions.assertEquals(existingCustomer.getState(),foundCustomer.getState());
        Assertions.assertEquals(existingCustomer.getPoints(),foundCustomer.getPoints());
    }

    @Test
    public void delete_happyPath(){
        //given
        CustomerEntity existingCustomer = addCustomerToDB();
        //when
        customerRepository.deleteById(existingCustomer.getId());
        //then
        CustomerEntity foundCustomer = customerRepository.findById(existingCustomer.getId());
        Assertions.assertNull(foundCustomer);
    }


    private CustomerEntity prepareCustomer(){
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

    public CustomerEntity addCustomerToDB(){
        CustomerEntity customerToAdd = prepareCustomer();
        return customerRepository.create(customerToAdd);
    }

}
