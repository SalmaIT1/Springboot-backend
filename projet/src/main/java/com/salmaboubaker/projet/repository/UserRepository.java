package com.salmaboubaker.projet.repository;


import com.salmaboubaker.projet.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);


    User findByEmailAndPassword(String email, String password);
}

