package com.practice1.service;

import com.practice1.model.Authority;
import com.practice1.model.User;
import com.practice1.repository.AuthorityRepository;
import com.practice1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.PostConstruct;
import java.util.Collection;

/**
 * Created by Alireza on 2/6/2018.
 */

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initializeUsers(){

        try{
            Authority authority = new Authority();
            authority.setTitle("ROLE_MASTERADMIN");
            authorityRepository.save(authority);

            User user = new User();
            user.setUsername("alireza");
            user.setPassword(passwordEncoder.encode("admin"));
            user.setFirstName("Alireza");
            user.setLastName("Akbari");
            user.setAuthority(authority);
            userRepository.save(user);

        }
        catch(Exception e){
            //The database exists and the users are already initialized
        }
    }

    public ResponseEntity<?> addUser(@RequestBody @Validated User user){
        User user1 = new User();
        user1.setAuthority(user.getAuthority());
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setPassword(user.getPassword());
        user1.setUsername(user.getUsername());
        userRepository.save(user1);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<?> updateUser(@RequestBody @Validated User user){
        User user1 = userRepository.findOne(user.getId());
        user1.setAuthority(user.getAuthority());
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setPassword(user.getPassword());
        user1.setUsername(user.getUsername());
        userRepository.save(user1);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> deleteUser(Long id){
        userRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    public User getOneUser(Long id){
        return userRepository.findOne(id);
    }

    public Collection<User> getAllUsers(){
        return userRepository.findAll();
    }

}
