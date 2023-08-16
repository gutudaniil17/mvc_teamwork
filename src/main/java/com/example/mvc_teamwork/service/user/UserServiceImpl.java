package com.example.mvc_teamwork.service.user;

import com.example.mvc_teamwork.security.entity.User;
import com.example.mvc_teamwork.security.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    public UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User saveUser(User user){
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            // User doesn't exist, proceed with creating a new user
            // Create a new user instance, set its properties, and save it
            User newUser = new User();
            newUser.setEmail(email);
            // Set other properties as needed
            User savedUser = userRepository.save(newUser);

            return Optional.of(savedUser); // Return the new user
        } else {
            // User exists, proceed with updating user information
            User existingUser = userOptional.get();
            // Update user information, e.g., set other properties, and save
            existingUser.setFirstName("Erica");
            // Update other properties as needed
            User updatedUser = userRepository.save(existingUser);

            return Optional.of(updatedUser); // Return the updated user
        }
    }
    @Override
    public Optional<Integer> isCustomerPresent(User user) {
        String email = user.getEmail();
        String firstName = user.getFirstName();
        System.out.println("Email: " + email);
        System.out.println("First Name: " + firstName);

        Optional<User> userOptional = userRepository.getUserByEmailAndFirstName(email, firstName);
        return userOptional.map(User::getId);

    }

}
