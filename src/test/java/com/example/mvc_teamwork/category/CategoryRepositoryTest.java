package com.example.mvc_teamwork.category;

import com.example.mvc_teamwork.entity.Category;
import com.example.mvc_teamwork.entity.Product;
import com.example.mvc_teamwork.repository.CategoryRepository;
import com.example.mvc_teamwork.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    @Test
    @DirtiesContext
    public void testSaveFindAll_SaveProduct_ListSize1() {
        Category category = new Category();

        categoryRepository.save(category);

        List<Category> categories = categoryRepository.findAll();
        Assertions.assertThat(categories).hasSize(1);
    }

    @Test
    @DirtiesContext
    public void testSaveFindAll_Save3Products_ListSize3() {
        Category category1 = new Category();
        Category category2 = new Category();
        Category category3 = new Category();

        categoryRepository.save(category1);
        categoryRepository.save(category2);
        categoryRepository.save(category3);

        List<Category> categories = categoryRepository.findAll();
        Assertions.assertThat(categories).hasSize(3);
    }

    @Test
    @DirtiesContext
    public void testFindById_SaveProduct_isPresent() {
        Category category = new Category();

        categoryRepository.save(category);

        Optional<Category> saved = categoryRepository.findById(1);
        Assertions.assertThat(saved).isPresent();
    }

    @Test
    @DirtiesContext
    public void testFindById_InvalidId_isEmpty() {
        int id = -1;

        Optional<Category> category = categoryRepository.findById(id);

        Assertions.assertThat(category).isEmpty();
    }

    @Test
    @DirtiesContext
    public void testFindByName_SaveProduct_isPresent() {
        Category category = new Category();
        category.setName("Test Category");

        categoryRepository.save(category);

        Optional<Category> foundCategory = categoryRepository.findByName("Test Category");
        Assertions.assertThat(foundCategory).isPresent();
    }

    @Test
    @DirtiesContext
    public void testFindByName_UnexcitingName_isEmpty() {
        String name = "unexciting";

        Optional<Category> foundCategory = categoryRepository.findByName(name);

        Assertions.assertThat(foundCategory).isEmpty();
    }

    @Test
    @DirtiesContext
    public void testDeleteById_SavedCategoryDelete_isEmpty() {
        Category category = new Category();

        category = categoryRepository.save(category);
        categoryRepository.deleteById(category.getId());

        Optional<Category> foundCategory = categoryRepository.findById(category.getId());
        Assertions.assertThat(foundCategory).isEmpty();
    }

    @Test
    @DirtiesContext
    public void testDeleteBy_SavedProductToCategory_isEmpty() {
        Product product = new Product();
        Category category = new Category();
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        category.setProducts(productList);

        Category savedCategory = categoryRepository.save(category);
        List<Product> savedProductList = savedCategory.getProducts();
        categoryRepository.deleteById(savedCategory.getId());
        List<Product> allProducts = productRepository.findAll();

        Assertions.assertThat(savedProductList).isNotEmpty();
        Assertions.assertThat(allProducts).isEmpty();
    }

    @Test
    @DirtiesContext
    public void testUpdateCategoryNameById() {
        Category category = new Category();
        category.setName("First name");

        Category savedCategory = categoryRepository.save(category);
        categoryRepository.updateCategoryNameById("Second name", savedCategory.getId());
        Optional<Category> modifiedCategory = categoryRepository.findByName("Second name");

        Assertions.assertThat(modifiedCategory).isPresent();
    }
}
