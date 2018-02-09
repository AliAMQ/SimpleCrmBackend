package com.practice1.service;

import com.practice1.model.Authority;
import com.practice1.model.User;
import com.practice1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.jws.soap.SOAPBinding;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Alireza on 2/8/2018.
 */

@Component("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        String username = s.toLowerCase();
        Optional<User> userFromDatabase = Optional.of(userRepository.findByUsername(username));
        //User userFromDatabase = userRepository.findByUsername(username);
        return userFromDatabase.map(user -> {
            Set<Authority> authorities = new HashSet<>();
            authorities.add(user.getAuthority());
            List<GrantedAuthority> grantedAuthorities = authorities.stream()
                    .map(authority -> new SimpleGrantedAuthority(authority.getTitle()))
                    .collect(Collectors.toList());
            return new org.springframework.security.core.userdetails.User(username, user.getPassword(), grantedAuthorities);
        }).orElseThrow(() -> new UsernameNotFoundException("User " + username + " was not found in the " + "database"));
    }

}
