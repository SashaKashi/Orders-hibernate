package by.itstep.ordershibernate.repository;

import by.itstep.ordershibernate.entity.ProductEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProductRepositoryTest {


    private ProductRepository productRepository = new ProductHibernateRepository();

    @BeforeEach
    public void setUp(){
        productRepository=new ProductHibernateRepository();
        productRepository.deleteAll();
    }

    @AfterEach
    public void clean(){
        productRepository.deleteAll();
    }

    @Test
    public void create_happyPath(){
        //given
        ProductEntity productToCreate = prepareProduct();
        //when
        ProductEntity createdProduct = productRepository.create(productToCreate);
        //then
        Assertions.assertNotNull(createdProduct.getId());
        ProductEntity foundProduct = productRepository.findById(createdProduct.getId());
        Assertions.assertEquals(productToCreate.getName(),foundProduct.getName());
        Assertions.assertEquals(productToCreate.getQuantity_in_stock(),foundProduct.getQuantity_in_stock());
        Assertions.assertEquals(productToCreate.getUnit_price(),foundProduct.getUnit_price());
    }

    @Test
    public void findAll_whenNoOneFound(){
        //given
        //when
        List<ProductEntity> foundProducts = productRepository.findAll();
        //then
        Assertions.assertTrue(foundProducts.isEmpty());
    }

    @Test
    public void findAll_happyPath(){
        //given
        addProductToDB();
        addProductToDB();
        //when
        List<ProductEntity> foundProducts = productRepository.findAll();
        //then
        Assertions.assertEquals(2,foundProducts.size());
    }

    @Test
    public void update_happyPath(){
        //given
        ProductEntity existingProduct = addProductToDB();
        existingProduct.setName("updated");
        existingProduct.setQuantity_in_stock(100);
        existingProduct.setUnit_price(10.5);
        //when
        ProductEntity updatedProduct = productRepository.update(existingProduct);
        //then
        Assertions.assertEquals(existingProduct.getId(), updatedProduct.getId());
        ProductEntity foundProduct = productRepository.findById(existingProduct.getId());
        Assertions.assertEquals(existingProduct.getName(),foundProduct.getName());
        Assertions.assertEquals(existingProduct.getQuantity_in_stock(),foundProduct.getQuantity_in_stock());
        Assertions.assertEquals(existingProduct.getUnit_price(),foundProduct.getUnit_price());
    }


    @Test
    public void delete_happyPath(){
        //given
        ProductEntity existingProduct = addProductToDB();
        //when
        productRepository.deleteById(existingProduct.getId());
        //then
        ProductEntity foundProduct = productRepository.findById(existingProduct.getId());
        Assertions.assertNull(foundProduct);
    }





    private ProductEntity prepareProduct(){
        ProductEntity product = new ProductEntity();
        product.setName("name#"+(int)(Math.random()*100));
        product.setUnit_price((int)(Math.random()*100));
        product.setQuantity_in_stock((int)(Math.random()*100));
        return product;
    }

    private ProductEntity addProductToDB(){
        ProductEntity productToAdd = prepareProduct();
        return productRepository.create(productToAdd);
    }


}
