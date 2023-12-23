package com.salmaboubaker.projet.services;

import com.salmaboubaker.projet.entities.User;
import com.salmaboubaker.projet.entities.UserRole;
import com.salmaboubaker.projet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {
        // Check if the email is already registered
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Email already registered");
        }

        // Set default values for other fields if needed
        user.setRole(UserRole.USER);
        user.setIsActive(true);

        // Hash the password before saving
        user.setPassword(user.getPassword());

        // Save the user
        return userRepository.save(user);
    }

    public User loginUser(String email, String password) {
        System.out.println("Attempting to login user with email: " + email);

        User user = userRepository.findByEmail(email);

        if (user == null || !passwordMatches(password, user.getPassword())) {
            System.out.println("Login failed for user with email: " + email);
            throw new RuntimeException("Invalid email or password");
        }

        System.out.println("Login successful for user with email: " + email);
        return user;
    }

    private boolean passwordMatches(String rawPassword, String hashedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }




    // Generate a random session token (you may replace this with a more secure token generation method)
    private String generateSessionToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] tokenBytes = new byte[32]; // Adjust the length of the token as needed
        secureRandom.nextBytes(tokenBytes);
        return Base64.getEncoder().encodeToString(tokenBytes);
    }
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public String getUserIdByEmailAndPassword(String email, String password) {
        // Assuming your User entity has fields like 'email' and 'password'
        User user = userRepository.findByEmailAndPassword(email, password);

        if (user != null) {
            // Assuming the user entity has a field 'id' representing the user ID
            return String.valueOf(user.getId());
        } else {
            return null;
        }
    }
}
