package com.example.mvc_teamwork.shoppingCart;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.mvc_teamwork.entity.ShoppingCart;
import com.example.mvc_teamwork.repository.ShoppingCartRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Optional;
/*@DataJpaTest is a Spring Boot annotation that configures the test to work with JPA repositories.
@AutoConfigureTestDatabase configures the type of test database. In this case, it uses H2 in-memory database.
@DirtiesContext indicates that the Spring application context should be considered dirty and will be reset after each test.*/
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ShoppingCartRepositoryTest {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Test
    @DirtiesContext
    public void testSaveAndFindShoppingCart() {
        ShoppingCart cartItem = new ShoppingCart();
        cartItem.setProductId(1);
        cartItem.setProductName("Product A");
        cartItem.setQuantity(3);
        cartItem.setAmount(30.0f);

        shoppingCartRepository.save(cartItem);

        List<ShoppingCart> savedItems = shoppingCartRepository.findAll();
        assertThat(savedItems).hasSize(1);

        Optional<ShoppingCart> foundItem = shoppingCartRepository.findById(savedItems.get(0).getId());
        assertThat(foundItem).isPresent();
        assertThat(foundItem.get().getProductName()).isEqualTo(cartItem.getProductName());
    }

    @Test
    @DirtiesContext
    public void testDeleteShoppingCart() {
        ShoppingCart cartItem = new ShoppingCart();
        cartItem.setProductId(1);
        cartItem.setProductName("Product A");
        cartItem.setQuantity(3);
        cartItem.setAmount(30.0f);

        ShoppingCart savedCartItem = shoppingCartRepository.save(cartItem);
        int cartItemId = savedCartItem.getId();

        shoppingCartRepository.deleteById(cartItemId);

        Optional<ShoppingCart> deletedItem = shoppingCartRepository.findById(cartItemId);
        assertThat(deletedItem).isEmpty();
    }
}
