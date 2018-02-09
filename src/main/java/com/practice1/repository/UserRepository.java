package com.practice1.repository;

import com.practice1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Alireza on 2/6/2018.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
