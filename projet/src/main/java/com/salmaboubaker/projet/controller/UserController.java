package com.salmaboubaker.projet.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.salmaboubaker.projet.entities.User;
import com.salmaboubaker.projet.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")


public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {

        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @JsonIgnoreProperties
    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        try {
            // Authenticate user and retrieve the user object with ID from the database
            User loggedInUser = userService.loginUser(email, password);

            if (loggedInUser != null) {
                // Modify the User entity to include a method to get the user ID
                String userId = String.valueOf(loggedInUser.getId()); // Replace with your method to get the user ID

                // Parse the String user ID to Long
                Long userIdLong = Long.parseLong(userId);

                // Set the user ID (Long) in the response body
                loggedInUser.setId(userIdLong);

                return ResponseEntity.ok(loggedInUser);
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }




}
