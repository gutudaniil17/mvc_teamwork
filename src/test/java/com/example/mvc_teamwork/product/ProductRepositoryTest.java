package com.example.mvc_teamwork.product;

import com.example.mvc_teamwork.entity.Product;
import com.example.mvc_teamwork.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Optional;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DirtiesContext
    public void testSaveAndFindAll_SaveProduct_ListSize1() {
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(100);

        productRepository.save(product);

        List<Product> products = productRepository.findAll();
        Assertions.assertThat(products).hasSize(1);
    }

    @Test
    @DirtiesContext
    public void testSaveAndFindAll_Save3Products_ListSize3() {
        Product product1 = new Product();
        Product product2 = new Product();
        Product product3 = new Product();

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        List<Product> products = productRepository.findAll();
        Assertions.assertThat(products).hasSize(3);
    }


    @Test
    @DirtiesContext
    public void testFindByName_SaveProduct_isPresentTrue() {
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(100);

        productRepository.save(product);

        Optional<Product> foundProduct = productRepository.findByName("Test Product");
        Assertions.assertThat(foundProduct).isPresent();
    }

    @Test
    @DirtiesContext
    public void testFindByName_UnsavedProduct_isEmpty() {
        Product product = new Product();
        product.setName("unexciting");

        Optional<Product> unexciting = productRepository.findByName(product.getName());

        Assertions.assertThat(unexciting).isEmpty();
    }

    @Test
    @DirtiesContext
    public void testDeleteById_SavedProductDelete_isEmpty() {
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(100);

        product = productRepository.save(product);

        productRepository.deleteById(product.getId());

        Optional<Product> deletedProduct = productRepository.findById(product.getId());
        Assertions.assertThat(deletedProduct).isEmpty();
    }
}

