package com.example.mvc_teamwork.user;

import com.example.mvc_teamwork.security.entity.User;
import com.example.mvc_teamwork.security.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DirtiesContext
    public void testFindByEmail_SaveUser_isPresent(){
        User user = new User();
        user.setEmail("test@mail.com");

        User savedUser = userRepository.save(user);
        Optional<User> foundUser = userRepository.findByEmail("test@mail.com");

        Assertions.assertThat(foundUser).isPresent();
    }

    @Test
    @DirtiesContext
    public void testFindByEmail_InvalidEmail_isEmpty(){
        String email = "unexciting";

        Optional<User> foundUser = userRepository.findByEmail(email);

        Assertions.assertThat(foundUser).isEmpty();
    }
}
